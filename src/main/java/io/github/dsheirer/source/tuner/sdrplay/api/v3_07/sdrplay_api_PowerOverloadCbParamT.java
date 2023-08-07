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

// Generated by jextract

package io.github.dsheirer.source.tuner.sdrplay.api.v3_07;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.foreign.SegmentScope;
import java.lang.foreign.StructLayout;
import java.lang.invoke.VarHandle;

/**
 * {@snippet :
 * struct {
 *     sdrplay_api_PowerOverloadCbEventIdT powerOverloadChangeType;
 * };
 * }
 */
public class sdrplay_api_PowerOverloadCbParamT {

    static final StructLayout $struct$LAYOUT = MemoryLayout.structLayout(
        Constants$root.C_INT$LAYOUT.withName("powerOverloadChangeType")
    );
    public static MemoryLayout $LAYOUT() {
        return sdrplay_api_PowerOverloadCbParamT.$struct$LAYOUT;
    }
    static final VarHandle powerOverloadChangeType$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("powerOverloadChangeType"));
    public static VarHandle powerOverloadChangeType$VH() {
        return sdrplay_api_PowerOverloadCbParamT.powerOverloadChangeType$VH;
    }
    /**
     * Getter for field:
     * {@snippet :
     * sdrplay_api_PowerOverloadCbEventIdT powerOverloadChangeType;
     * }
     */
    public static int powerOverloadChangeType$get(MemorySegment seg) {
        return (int)sdrplay_api_PowerOverloadCbParamT.powerOverloadChangeType$VH.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * sdrplay_api_PowerOverloadCbEventIdT powerOverloadChangeType;
     * }
     */
    public static void powerOverloadChangeType$set(MemorySegment seg, int x) {
        sdrplay_api_PowerOverloadCbParamT.powerOverloadChangeType$VH.set(seg, x);
    }
    public static int powerOverloadChangeType$get(MemorySegment seg, long index) {
        return (int)sdrplay_api_PowerOverloadCbParamT.powerOverloadChangeType$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void powerOverloadChangeType$set(MemorySegment seg, long index, int x) {
        sdrplay_api_PowerOverloadCbParamT.powerOverloadChangeType$VH.set(seg.asSlice(index*sizeof()), x);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(long len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemorySegment addr, SegmentScope scope) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, scope); }
}


