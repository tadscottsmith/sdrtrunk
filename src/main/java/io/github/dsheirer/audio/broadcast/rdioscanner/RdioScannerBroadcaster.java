/*
 * *****************************************************************************
 * Copyright (C) 2014-2022 Dennis Sheirer
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 * ****************************************************************************
 */

package io.github.dsheirer.audio.broadcast.rdioscanner;

import com.google.common.net.HttpHeaders;
import io.github.dsheirer.alias.AliasModel;
import io.github.dsheirer.audio.broadcast.AbstractAudioBroadcaster;
import io.github.dsheirer.audio.broadcast.AudioRecording;
import io.github.dsheirer.audio.broadcast.BroadcastEvent;
import io.github.dsheirer.audio.broadcast.BroadcastState;
import io.github.dsheirer.audio.convert.InputAudioFormat;
import io.github.dsheirer.audio.convert.MP3Setting;
import io.github.dsheirer.gui.playlist.radioreference.RadioReferenceDecoder;
import io.github.dsheirer.identifier.Form;
import io.github.dsheirer.identifier.Identifier;
import io.github.dsheirer.identifier.IdentifierClass;
import io.github.dsheirer.identifier.MutableIdentifierCollection;
import io.github.dsheirer.identifier.Role;
import io.github.dsheirer.identifier.configuration.ConfigurationLongIdentifier;
import io.github.dsheirer.identifier.patch.PatchGroup;
import io.github.dsheirer.identifier.patch.PatchGroupIdentifier;
import io.github.dsheirer.identifier.radio.RadioIdentifier;
import io.github.dsheirer.identifier.talkgroup.TalkgroupIdentifier;
import io.github.dsheirer.util.ThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CompletionException;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


/**
 * Audio broadcaster to push completed audio recordings to the RdioScanner call push API.
 *
 * Note: this is not the same as the RdioScanner Feeds (ie streaming) service
 */
public class RdioScannerBroadcaster extends AbstractAudioBroadcaster<RdioScannerConfiguration>
{
    private final static Logger mLog = LoggerFactory.getLogger(RdioScannerBroadcaster.class);

    private static final String ENCODING_TYPE_MP3 = "mp3";
    private static final String MULTIPART_TYPE = "multipart";
    private static final String DEFAULT_SUBTYPE = "form-data";
    private static final String MULTIPART_FORM_DATA = MULTIPART_TYPE + "/" + DEFAULT_SUBTYPE;
    private Queue<AudioRecording> mAudioRecordingQueue = new LinkedTransferQueue<>();
    private ScheduledFuture<?> mAudioRecordingProcessorFuture;
    private HttpClient mHttpClient = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)
        .followRedirects(HttpClient.Redirect.NORMAL)
        .connectTimeout(Duration.ofSeconds(20))
        .build();
    private long mLastConnectionAttempt;
    private long mConnectionAttemptInterval = 5000; //Every 5 seconds

    /**
     * Constructs an instance of the broadcaster
     * @param config to use
     * @param aliasModel for access to aliases
     */
    public RdioScannerBroadcaster(RdioScannerConfiguration config, InputAudioFormat inputAudioFormat,
                                       MP3Setting mp3Setting, AliasModel aliasModel)
    {
        super(config);
    }

    /**
     * Starts the audio recording processor thread
     */
    @Override
    public void start()
    {
        setBroadcastState(BroadcastState.CONNECTING);
        String response = testConnection(getBroadcastConfiguration());
        mLastConnectionAttempt = System.currentTimeMillis();

        /**
         * Rdio Scanner API does not currently expose a test method.
         */
        if(response != null && response.toLowerCase().startsWith("incomplete call data: no talkgroup"))
        {
            setBroadcastState(BroadcastState.CONNECTED);
        }
        else
        {
            mLog.error("Error connecting to Rdio Scanner server on startup [" + response + "]");
            setBroadcastState(BroadcastState.ERROR);
        }

        if(mAudioRecordingProcessorFuture == null)
        {
            mAudioRecordingProcessorFuture = ThreadPool.SCHEDULED.scheduleAtFixedRate(new AudioRecordingProcessor(),
                0, 500, TimeUnit.MILLISECONDS);
        }
    }

    /**
     * Stops the audio recording processor thread
     */
    @Override
    public void stop()
    {
        if(mAudioRecordingProcessorFuture != null)
        {
            mAudioRecordingProcessorFuture.cancel(true);
            mAudioRecordingProcessorFuture = null;
            dispose();
            setBroadcastState(BroadcastState.DISCONNECTED);
        }
    }

    /**
     * Prepares for disposal
     */
    @Override
    public void dispose()
    {
        AudioRecording audioRecording = mAudioRecordingQueue.poll();

        while(audioRecording != null)
        {
            audioRecording.removePendingReplay();
            audioRecording = mAudioRecordingQueue.poll();
        }
    }

    /**
     * Indicates if this broadcaster continues to have successful connections to and transactions with the remote
     * server.  If there is a connectivity or other issue, the broadcast state is set to temporary error and
     * the audio processor thread will persistently invoke this method to attempt a reconnect.
     */
    private boolean connected()
    {
        if(getBroadcastState() != BroadcastState.CONNECTED &&
            (System.currentTimeMillis() - mLastConnectionAttempt > mConnectionAttemptInterval))
        {
            setBroadcastState(BroadcastState.CONNECTING);

            String response = testConnection(getBroadcastConfiguration());
            mLastConnectionAttempt = System.currentTimeMillis();

            if(response != null && response.toLowerCase().startsWith("incomplete call data: no talkgroup"))
            {
                setBroadcastState(BroadcastState.CONNECTED);
            }
            else
            {
                setBroadcastState(BroadcastState.ERROR);
            }
        }

        return getBroadcastState() == BroadcastState.CONNECTED;
    }

    @Override
    public int getAudioQueueSize()
    {
        return mAudioRecordingQueue.size();
    }

    @Override
    public void receive(AudioRecording audioRecording)
    {
        mAudioRecordingQueue.offer(audioRecording);
        broadcast(new BroadcastEvent(this, BroadcastEvent.Event.BROADCASTER_QUEUE_CHANGE));
    }

    /**
     * Indicates if the audio recording is non-null and not too old, meaning that the age of the recording has not
     * exceeded the max age value indicated in the broadcast configuration.  Audio recordings that are too old will be
     * deleted to ensure that the in-memory queue size doesn't blow up.
     * @param audioRecording to test
     * @return true if the recording is valid
     */
    private boolean isValid(AudioRecording audioRecording)
    {
        return audioRecording != null && System.currentTimeMillis() - audioRecording.getStartTime() <=
            getBroadcastConfiguration().getMaximumRecordingAge();
    }

    /**
     * Processes any enqueued audio recordings.  The RdioScanner calls API uses a two-step process that includes
     * requesting an upload URL and then uploading the audio recording to that URL.  This method employs asynchronous
     * interaction with the server, so multiple audio recording uploads can occur simultaneously.
     */
    private void processRecordingQueue()
    {

        
        while(connected() && !mAudioRecordingQueue.isEmpty())
        {
            final AudioRecording audioRecording = mAudioRecordingQueue.poll();
            broadcast(new BroadcastEvent(this, BroadcastEvent.Event.BROADCASTER_QUEUE_CHANGE));

            if(isValid(audioRecording) && audioRecording.getRecordingLength() > 0)
            {
                float durationSeconds = (float)(audioRecording.getRecordingLength() / 1E3f);
                long timestampSeconds = (int)(audioRecording.getStartTime() / 1E3);
                String talkgroup = getTo(audioRecording);
                String radioId = getFrom(audioRecording);
                Long frequency = getFrequency(audioRecording);

                try
                {
                    byte[] audioBytes = null;

                    try
                    {
                        audioBytes = Files.readAllBytes(audioRecording.getPath());
                    }
                    catch(IOException e)
                    {
                        mLog.error("Rdio Scanner API - audio recording file not found - ignoring upload");
                    }

                    if(audioBytes != null)
                    {

                        RdioScannerBuilder bodyBuilder = new RdioScannerBuilder();
                            bodyBuilder.addPart(FormField.KEY, getBroadcastConfiguration().getApiKey())
                            .addPart(FormField.SYSTEM, getBroadcastConfiguration().getSystemID())
                            .addFile(audioBytes)
                            .addPart(FormField.AUDIO_NAME, "@" + audioRecording.getPath())
                            .addPart(FormField.AUDIO_TYPE, "audio/mpeg")
                            //.addPart(FormField.CALL_DURATION, durationSeconds)
                            .addPart(FormField.DATE_TIME, timestampSeconds)
                            .addPart(FormField.TALKGROUP_ID, talkgroup)
                            .addPart(FormField.SOURCE, radioId)
                            .addPart(FormField.FREQUENCY, frequency);
                            //.addPart(FormField.ENCODING, ENCODING_TYPE_MP3);

                        HttpRequest fileRequest = HttpRequest.newBuilder()
                            .uri(URI.create(getBroadcastConfiguration().getHost()))
                            .header(HttpHeaders.CONTENT_TYPE, MULTIPART_FORM_DATA + "; boundary=" + bodyBuilder.getBoundary())
                            .header(HttpHeaders.USER_AGENT, "sdrtrunk")
                            .header(HttpHeaders.CONTENT_TYPE, "audio/mpeg")
                            .POST(bodyBuilder.build())
                            .build();

                        mHttpClient.sendAsync(fileRequest, HttpResponse.BodyHandlers.ofString())
                            .whenComplete((fileResponse, throwable1) -> {
                                if(throwable1 != null || fileResponse.statusCode() != 200)
                                {
                                    if(throwable1 instanceof IOException || throwable1 instanceof CompletionException)
                                    {
                                        //We get socket reset exceptions occasionally when the remote server doesn't
                                        //fully read our request and immediately responds.
                                        setBroadcastState(BroadcastState.TEMPORARY_BROADCAST_ERROR);
                                        mLog.error("Rdio Scanner API file upload fail [" +
                                            fileResponse.statusCode() + "] response [" +
                                            fileResponse.body() + "]");
                                    }
                                    else
                                    {
                                        setBroadcastState(BroadcastState.TEMPORARY_BROADCAST_ERROR);
                                        mLog.error("Rdio Scanner API file upload fail [" +
                                            fileResponse.statusCode() + "] response [" +
                                            fileResponse.body() + "]");
                                    }

                                    incrementErrorAudioCount();
                                    broadcast(new BroadcastEvent(RdioScannerBroadcaster.this,
                                        BroadcastEvent.Event.BROADCASTER_ERROR_COUNT_CHANGE));
                                }
                                else
                                {
                                    String fileResponseString = fileResponse.body();

                                    if(fileResponseString.contains("Call imported successfully."))
                                    {
                                        incrementStreamedAudioCount();
                                        broadcast(new BroadcastEvent(RdioScannerBroadcaster.this,
                                            BroadcastEvent.Event.BROADCASTER_STREAMED_COUNT_CHANGE)); 
                                        audioRecording.removePendingReplay(); 
                                    }
                                    else if(fileResponseString.contains("duplicate call rejected"))
                                    {
                                        //Rdio Server is telling us to skip audio upload - someone already uploaded it
                                        audioRecording.removePendingReplay();
                                    }
                                    else
                                    {
                                        setBroadcastState(BroadcastState.TEMPORARY_BROADCAST_ERROR);
                                        mLog.error("Rdio Scanner API file upload fail [" +
                                            fileResponse.statusCode() + "] response [" +
                                            fileResponse.body() + "]");
                                    }


                                }
                         
                            });
                    }
                    else
                    {
                        //Register an error for the file not found exception
                        mLog.error("Rdio Scanner API - upload file not found [" +
                            audioRecording.getPath().toString() + "]");
                        incrementErrorAudioCount();
                        broadcast(new BroadcastEvent(RdioScannerBroadcaster.this,
                            BroadcastEvent.Event.BROADCASTER_ERROR_COUNT_CHANGE));
                        audioRecording.removePendingReplay();
                    }
                }
                catch(Exception e)
                {
                    mLog.error("Unknown Error", e);
                    setBroadcastState(BroadcastState.ERROR);
                    incrementErrorAudioCount();
                    broadcast(new BroadcastEvent(this, BroadcastEvent.Event.BROADCASTER_ERROR_COUNT_CHANGE));
                    audioRecording.removePendingReplay();
                }
            }
        }

        //If we're not connected and there are recordings in the queue, check the recording at the head of the queue
        // and start age-off once the recordings become too old.  The recordings should be time ordered in the queue.
        AudioRecording audioRecording = mAudioRecordingQueue.peek();

        while(audioRecording != null)
        {
            if(isValid(audioRecording))
            {
                return;
            }
            else
            {
                //Remove the recording from the queue, remove a replay, and peek at the next recording in the queue
                mAudioRecordingQueue.poll();
                audioRecording.removePendingReplay();
                incrementAgedOffAudioCount();
                broadcast(new BroadcastEvent(this, BroadcastEvent.Event.BROADCASTER_AGED_OFF_COUNT_CHANGE));
                audioRecording = mAudioRecordingQueue.peek();
            }
        }
    }

    /**
     * Creates a frequency value from the audio recording identifier collection.
     */
    private static Long getFrequency(AudioRecording audioRecording)
    {
        Identifier identifier = audioRecording.getIdentifierCollection().getIdentifier(IdentifierClass.CONFIGURATION,
            Form.CHANNEL_FREQUENCY, Role.ANY);

        if(identifier instanceof ConfigurationLongIdentifier)
        {
            Long value = ((ConfigurationLongIdentifier)identifier).getValue();

            if(value != null)
            {
                return value;
            }
        }

        return new Long(0);
    }

    /**
     * Creates a formatted string with the FROM identifier or uses a default of zero(0)
     */
    private static String getFrom(AudioRecording audioRecording)
    {
        for(Identifier identifier: audioRecording.getIdentifierCollection().getIdentifiers(Role.FROM))
        {
            if(identifier instanceof RadioIdentifier)
            {
                return ((RadioIdentifier)identifier).getValue().toString();
            }
        }

        return "0";
    }

    /**
     * Creates a formatted string with the TO identifiers or uses a default of zero (0)
     * 
     */
    private static String getTo(AudioRecording audioRecording)
    {
        Identifier identifier = audioRecording.getIdentifierCollection().getToIdentifier();

        if(identifier instanceof PatchGroupIdentifier patchGroupIdentifier)
        {
            return patchGroupIdentifier.getValue().getPatchGroup().getValue().toString();
        }
        else if(identifier instanceof TalkgroupIdentifier talkgroupIdentifier)
        {
            return String.valueOf(RadioReferenceDecoder.convertToRadioReferenceTalkgroup(talkgroupIdentifier.getValue(),
                    talkgroupIdentifier.getProtocol()));
        }
        else if(identifier instanceof RadioIdentifier radioIdentifier)
        {
            return radioIdentifier.getValue().toString();
        }

        return "0";
    }

    /**
     * Formats a patch group
     */
    public static String format(PatchGroupIdentifier patchGroupIdentifier)
    {
        PatchGroup patchGroup = patchGroupIdentifier.getValue();

        StringBuilder sb = new StringBuilder();
        sb.append(patchGroup.getPatchGroup().getValue().toString());
        for(TalkgroupIdentifier patched: patchGroup.getPatchedGroupIdentifiers())
        {
            sb.append(",").append(patched.getValue());
        }

        return sb.toString();
    }

    /**
     * Tests both the connection and configuration against the RdioScanner Call API service
     * @param configuration containing API key and system id
     * @return error string or null if test is successful
     */
    public static String testConnection(RdioScannerConfiguration configuration)
    {
        HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .followRedirects(HttpClient.Redirect.NORMAL)
            .connectTimeout(Duration.ofSeconds(20))
            .build();

        RdioScannerBuilder bodyBuilder = new RdioScannerBuilder();
        bodyBuilder.addPart(FormField.KEY, configuration.getApiKey())
            .addPart(FormField.SYSTEM, configuration.getSystemID())
            .addPart(FormField.TEST, 1);

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(configuration.getHost()))
            .header(HttpHeaders.CONTENT_TYPE, MULTIPART_FORM_DATA + "; boundary=" + bodyBuilder.getBoundary())
            .header(HttpHeaders.USER_AGENT, "sdrtrunk")
            .header(HttpHeaders.ACCEPT, "*/*")
            .POST(bodyBuilder.build())
            .build();

        HttpResponse.BodyHandler<String> responseHandler = HttpResponse.BodyHandlers.ofString();

        try
        {
            HttpResponse<String> response = httpClient.send(request, responseHandler);
            String responseBody = response.body();
            return (responseBody != null ? responseBody : "(no response)") + " Status Code:" + response.statusCode();
        }
        catch(Exception e)
        {
            return e.getLocalizedMessage();
        }
    }

    public class AudioRecordingProcessor implements Runnable
    {
        @Override
        public void run()
        {
            processRecordingQueue();
        }
    }

    public static void main(String[] args)
    {
        mLog.debug("Starting ...");

        RdioScannerConfiguration config = new RdioScannerConfiguration();
        config.setHost("https://api.RdioScanner.com/call-upload-dev");
        config.setApiKey("c33aae37-8572-11ea-bd8b-0ecc8ab9ccec");
        config.setSystemID(11);

        String response = testConnection(config);

        if(response == null)
        {
            mLog.debug("Test Successful!");
        }
        else
        {
            if(response.contains("1 Invalid-API-Key"))
            {
                mLog.error("Invalid API Key");
            }
            else if(response.contains("1 API-Key-Access-Denied"))
            {
                mLog.error("System ID not valid for API Key");
            }
            else
            {
                mLog.debug("Response: " + response);
            }
        }

        mLog.debug("Finished!");
    }
}
