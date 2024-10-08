/*
 * *****************************************************************************
 * Copyright (C) 2014-2024 Dennis Sheirer
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

// Generated by jextract

package io.github.dsheirer.source.tuner.sdrplay.api.v3_08;

import java.lang.foreign.AddressLayout;
import java.lang.foreign.Arena;
import java.lang.foreign.GroupLayout;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.util.function.Consumer;

import static java.lang.foreign.MemoryLayout.PathElement.groupElement;

/**
 * {@snippet lang=c :
 * struct {
 *     sdrplay_api_StreamCallback_t StreamACbFn;
 *     sdrplay_api_StreamCallback_t StreamBCbFn;
 *     sdrplay_api_EventCallback_t EventCbFn;
 * }
 * }
 */
public class sdrplay_api_CallbackFnsT {

    sdrplay_api_CallbackFnsT() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        sdrplay_api_h.C_POINTER.withName("StreamACbFn"),
        sdrplay_api_h.C_POINTER.withName("StreamBCbFn"),
        sdrplay_api_h.C_POINTER.withName("EventCbFn")
    ).withName("$anon$71:9");

    /**
     * The layout of this struct
     */
    public static final GroupLayout layout() {
        return $LAYOUT;
    }

    private static final AddressLayout StreamACbFn$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("StreamACbFn"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * sdrplay_api_StreamCallback_t StreamACbFn
     * }
     */
    public static final AddressLayout StreamACbFn$layout() {
        return StreamACbFn$LAYOUT;
    }

    private static final long StreamACbFn$OFFSET = 0;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * sdrplay_api_StreamCallback_t StreamACbFn
     * }
     */
    public static final long StreamACbFn$offset() {
        return StreamACbFn$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * sdrplay_api_StreamCallback_t StreamACbFn
     * }
     */
    public static MemorySegment StreamACbFn(MemorySegment struct) {
        return struct.get(StreamACbFn$LAYOUT, StreamACbFn$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * sdrplay_api_StreamCallback_t StreamACbFn
     * }
     */
    public static void StreamACbFn(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(StreamACbFn$LAYOUT, StreamACbFn$OFFSET, fieldValue);
    }

    private static final AddressLayout StreamBCbFn$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("StreamBCbFn"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * sdrplay_api_StreamCallback_t StreamBCbFn
     * }
     */
    public static final AddressLayout StreamBCbFn$layout() {
        return StreamBCbFn$LAYOUT;
    }

    private static final long StreamBCbFn$OFFSET = 8;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * sdrplay_api_StreamCallback_t StreamBCbFn
     * }
     */
    public static final long StreamBCbFn$offset() {
        return StreamBCbFn$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * sdrplay_api_StreamCallback_t StreamBCbFn
     * }
     */
    public static MemorySegment StreamBCbFn(MemorySegment struct) {
        return struct.get(StreamBCbFn$LAYOUT, StreamBCbFn$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * sdrplay_api_StreamCallback_t StreamBCbFn
     * }
     */
    public static void StreamBCbFn(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(StreamBCbFn$LAYOUT, StreamBCbFn$OFFSET, fieldValue);
    }

    private static final AddressLayout EventCbFn$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("EventCbFn"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * sdrplay_api_EventCallback_t EventCbFn
     * }
     */
    public static final AddressLayout EventCbFn$layout() {
        return EventCbFn$LAYOUT;
    }

    private static final long EventCbFn$OFFSET = 16;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * sdrplay_api_EventCallback_t EventCbFn
     * }
     */
    public static final long EventCbFn$offset() {
        return EventCbFn$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * sdrplay_api_EventCallback_t EventCbFn
     * }
     */
    public static MemorySegment EventCbFn(MemorySegment struct) {
        return struct.get(EventCbFn$LAYOUT, EventCbFn$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * sdrplay_api_EventCallback_t EventCbFn
     * }
     */
    public static void EventCbFn(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(EventCbFn$LAYOUT, EventCbFn$OFFSET, fieldValue);
    }

    /**
     * Obtains a slice of {@code arrayParam} which selects the array element at {@code index}.
     * The returned segment has address {@code arrayParam.address() + index * layout().byteSize()}
     */
    public static MemorySegment asSlice(MemorySegment array, long index) {
        return array.asSlice(layout().byteSize() * index);
    }

    /**
     * The size (in bytes) of this struct
     */
    public static long sizeof() { return layout().byteSize(); }

    /**
     * Allocate a segment of size {@code layout().byteSize()} using {@code allocator}
     */
    public static MemorySegment allocate(SegmentAllocator allocator) {
        return allocator.allocate(layout());
    }

    /**
     * Allocate an array of size {@code elementCount} using {@code allocator}.
     * The returned segment has size {@code elementCount * layout().byteSize()}.
     */
    public static MemorySegment allocateArray(long elementCount, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout()));
    }

    /**
     * Reinterprets {@code addr} using target {@code arena} and {@code cleanupAction} (if any).
     * The returned segment has size {@code layout().byteSize()}
     */
    public static MemorySegment reinterpret(MemorySegment addr, Arena arena, Consumer<MemorySegment> cleanup) {
        return reinterpret(addr, 1, arena, cleanup);
    }

    /**
     * Reinterprets {@code addr} using target {@code arena} and {@code cleanupAction} (if any).
     * The returned segment has size {@code elementCount * layout().byteSize()}
     */
    public static MemorySegment reinterpret(MemorySegment addr, long elementCount, Arena arena, Consumer<MemorySegment> cleanup) {
        return addr.reinterpret(layout().byteSize() * elementCount, arena, cleanup);
    }
}

