/*
 * *****************************************************************************
 * Copyright (C) 2014-2023 Dennis Sheirer
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

package io.github.dsheirer.module.decode.dmr.message.data.block;

import io.github.dsheirer.bits.CorrectedBinaryMessage;
import io.github.dsheirer.module.decode.dmr.message.CACH;
import io.github.dsheirer.module.decode.dmr.message.data.SlotType;
import io.github.dsheirer.module.decode.dmr.sync.DMRSyncPattern;

/**
 * Rate 1/2 BPTC 196 Data Block
 */
public class DataBlock1_2Rate extends DataBlock
{
    private static final int[] CRC = new int[]{7, 8, 9, 10, 11, 12, 13, 14, 15};
    private static final int CONFIRMED_PAYLOAD_START = 16;
    private static final int UNCONFIRMED_PAYLOAD_START = 0;
    private static final int PAYLOAD_END = 96;

    /**
     * Constructs an instance.
     *
     * @param syncPattern either BASE_STATION_DATA or MOBILE_STATION_DATA
     * @param message containing extracted 196-bit payload.
     * @param cach for the DMR burst
     * @param slotType for this data message
     * @param timestamp message was received
     * @param timeslot for the DMR burst
     */
    public DataBlock1_2Rate(DMRSyncPattern syncPattern, CorrectedBinaryMessage message, CACH cach, SlotType slotType, long timestamp, int timeslot)
    {
        super(syncPattern, message, cach, slotType, timestamp, timeslot);

        //TODO: check CRC for data block serial number
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("CC:").append(getSlotType().getColorCode());
        if(hasRAS())
        {
            sb.append(" RAS:").append(getBPTCReservedBits());
        }
        if(!isValid())
        {
            sb.append(" [CRC ERROR]");
        }
        sb.append(" RATE 1/2 DATA CONFIRMED:").append(getConfirmedPayload().toHexString());
        sb.append(" UNCONFIRMED:").append(getUnConfirmedPayload().toHexString());
        return sb.toString();
    }

    @Override
    public CorrectedBinaryMessage getConfirmedPayload()
    {
        return getMessage().getSubMessage(CONFIRMED_PAYLOAD_START, PAYLOAD_END);
    }

    @Override
    public CorrectedBinaryMessage getUnConfirmedPayload()
    {
        return getMessage().getSubMessage(UNCONFIRMED_PAYLOAD_START, PAYLOAD_END);
    }
}


