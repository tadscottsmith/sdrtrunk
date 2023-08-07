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

import java.lang.invoke.VarHandle;
import java.lang.foreign.*;

/**
 * {@snippet :
 * struct {
 *     sdrplay_api_Bw_MHzT bwType;
 *     sdrplay_api_If_kHzT ifType;
 *     sdrplay_api_LoModeT loMode;
 *     sdrplay_api_GainT gain;
 *     sdrplay_api_RfFreqT rfFreq;
 *     sdrplay_api_DcOffsetTunerT dcOffsetTuner;
 * };
 * }
 */
public class sdrplay_api_TunerParamsT {

    static final StructLayout $struct$LAYOUT = MemoryLayout.structLayout(
        Constants$root.C_INT$LAYOUT.withName("bwType"),
        Constants$root.C_INT$LAYOUT.withName("ifType"),
        Constants$root.C_INT$LAYOUT.withName("loMode"),
        MemoryLayout.structLayout(
            Constants$root.C_INT$LAYOUT.withName("gRdB"),
            Constants$root.C_CHAR$LAYOUT.withName("LNAstate"),
            Constants$root.C_CHAR$LAYOUT.withName("syncUpdate"),
            MemoryLayout.paddingLayout(16),
            Constants$root.C_INT$LAYOUT.withName("minGr"),
            MemoryLayout.structLayout(
                Constants$root.C_FLOAT$LAYOUT.withName("curr"),
                Constants$root.C_FLOAT$LAYOUT.withName("max"),
                Constants$root.C_FLOAT$LAYOUT.withName("min")
            ).withName("gainVals")
        ).withName("gain"),
        MemoryLayout.paddingLayout(32),
        MemoryLayout.structLayout(
            Constants$root.C_DOUBLE$LAYOUT.withName("rfHz"),
            Constants$root.C_CHAR$LAYOUT.withName("syncUpdate"),
            MemoryLayout.paddingLayout(56)
        ).withName("rfFreq"),
        MemoryLayout.structLayout(
            Constants$root.C_CHAR$LAYOUT.withName("dcCal"),
            Constants$root.C_CHAR$LAYOUT.withName("speedUp"),
            MemoryLayout.paddingLayout(16),
            Constants$root.C_INT$LAYOUT.withName("trackTime"),
            Constants$root.C_INT$LAYOUT.withName("refreshRateTime")
        ).withName("dcOffsetTuner"),
        MemoryLayout.paddingLayout(32)
    );
    public static MemoryLayout $LAYOUT() {
        return sdrplay_api_TunerParamsT.$struct$LAYOUT;
    }
    static final VarHandle bwType$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("bwType"));
    public static VarHandle bwType$VH() {
        return sdrplay_api_TunerParamsT.bwType$VH;
    }
    /**
     * Getter for field:
     * {@snippet :
     * sdrplay_api_Bw_MHzT bwType;
     * }
     */
    public static int bwType$get(MemorySegment seg) {
        return (int)sdrplay_api_TunerParamsT.bwType$VH.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * sdrplay_api_Bw_MHzT bwType;
     * }
     */
    public static void bwType$set(MemorySegment seg, int x) {
        sdrplay_api_TunerParamsT.bwType$VH.set(seg, x);
    }
    public static int bwType$get(MemorySegment seg, long index) {
        return (int)sdrplay_api_TunerParamsT.bwType$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void bwType$set(MemorySegment seg, long index, int x) {
        sdrplay_api_TunerParamsT.bwType$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle ifType$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("ifType"));
    public static VarHandle ifType$VH() {
        return sdrplay_api_TunerParamsT.ifType$VH;
    }
    /**
     * Getter for field:
     * {@snippet :
     * sdrplay_api_If_kHzT ifType;
     * }
     */
    public static int ifType$get(MemorySegment seg) {
        return (int)sdrplay_api_TunerParamsT.ifType$VH.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * sdrplay_api_If_kHzT ifType;
     * }
     */
    public static void ifType$set(MemorySegment seg, int x) {
        sdrplay_api_TunerParamsT.ifType$VH.set(seg, x);
    }
    public static int ifType$get(MemorySegment seg, long index) {
        return (int)sdrplay_api_TunerParamsT.ifType$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void ifType$set(MemorySegment seg, long index, int x) {
        sdrplay_api_TunerParamsT.ifType$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle loMode$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("loMode"));
    public static VarHandle loMode$VH() {
        return sdrplay_api_TunerParamsT.loMode$VH;
    }
    /**
     * Getter for field:
     * {@snippet :
     * sdrplay_api_LoModeT loMode;
     * }
     */
    public static int loMode$get(MemorySegment seg) {
        return (int)sdrplay_api_TunerParamsT.loMode$VH.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * sdrplay_api_LoModeT loMode;
     * }
     */
    public static void loMode$set(MemorySegment seg, int x) {
        sdrplay_api_TunerParamsT.loMode$VH.set(seg, x);
    }
    public static int loMode$get(MemorySegment seg, long index) {
        return (int)sdrplay_api_TunerParamsT.loMode$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void loMode$set(MemorySegment seg, long index, int x) {
        sdrplay_api_TunerParamsT.loMode$VH.set(seg.asSlice(index*sizeof()), x);
    }
    public static MemorySegment gain$slice(MemorySegment seg) {
        return seg.asSlice(12, 24);
    }
    public static MemorySegment rfFreq$slice(MemorySegment seg) {
        return seg.asSlice(40, 16);
    }
    public static MemorySegment dcOffsetTuner$slice(MemorySegment seg) {
        return seg.asSlice(56, 12);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(long len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemorySegment addr, SegmentScope scope) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, scope); }
}


