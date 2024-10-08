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

import java.lang.foreign.*;
import java.util.function.*;

import static java.lang.foreign.ValueLayout.*;
import static java.lang.foreign.MemoryLayout.PathElement.*;

/**
 * {@snippet lang=c :
 * struct {
 *     sdrplay_api_RspDuoModeCbEventIdT modeChangeType;
 * }
 * }
 */
public class sdrplay_api_RspDuoModeCbParamT {

    sdrplay_api_RspDuoModeCbParamT() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        sdrplay_api_h.C_INT.withName("modeChangeType")
    ).withName("$anon$43:9");

    /**
     * The layout of this struct
     */
    public static final GroupLayout layout() {
        return $LAYOUT;
    }

    private static final OfInt modeChangeType$LAYOUT = (OfInt)$LAYOUT.select(groupElement("modeChangeType"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * sdrplay_api_RspDuoModeCbEventIdT modeChangeType
     * }
     */
    public static final OfInt modeChangeType$layout() {
        return modeChangeType$LAYOUT;
    }

    private static final long modeChangeType$OFFSET = 0;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * sdrplay_api_RspDuoModeCbEventIdT modeChangeType
     * }
     */
    public static final long modeChangeType$offset() {
        return modeChangeType$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * sdrplay_api_RspDuoModeCbEventIdT modeChangeType
     * }
     */
    public static int modeChangeType(MemorySegment struct) {
        return struct.get(modeChangeType$LAYOUT, modeChangeType$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * sdrplay_api_RspDuoModeCbEventIdT modeChangeType
     * }
     */
    public static void modeChangeType(MemorySegment struct, int fieldValue) {
        struct.set(modeChangeType$LAYOUT, modeChangeType$OFFSET, fieldValue);
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

