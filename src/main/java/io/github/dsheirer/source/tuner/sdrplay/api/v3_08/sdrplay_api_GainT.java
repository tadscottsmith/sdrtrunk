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

package io.github.dsheirer.source.tuner.sdrplay.api.v3_08;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.foreign.SegmentScope;
import java.lang.foreign.StructLayout;
import java.lang.invoke.VarHandle;

/**
 * {@snippet :
 * struct {
 *     int gRdB;
 *     unsigned char LNAstate;
 *     unsigned char syncUpdate;
 *     sdrplay_api_MinGainReductionT minGr;
 *     sdrplay_api_GainValuesT gainVals;
 * };
 * }
 */
public class sdrplay_api_GainT {

    static final StructLayout $struct$LAYOUT = MemoryLayout.structLayout(
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
    );
    public static MemoryLayout $LAYOUT() {
        return sdrplay_api_GainT.$struct$LAYOUT;
    }
    static final VarHandle gRdB$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("gRdB"));
    public static VarHandle gRdB$VH() {
        return sdrplay_api_GainT.gRdB$VH;
    }
    /**
     * Getter for field:
     * {@snippet :
     * int gRdB;
     * }
     */
    public static int gRdB$get(MemorySegment seg) {
        return (int)sdrplay_api_GainT.gRdB$VH.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * int gRdB;
     * }
     */
    public static void gRdB$set(MemorySegment seg, int x) {
        sdrplay_api_GainT.gRdB$VH.set(seg, x);
    }
    public static int gRdB$get(MemorySegment seg, long index) {
        return (int)sdrplay_api_GainT.gRdB$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void gRdB$set(MemorySegment seg, long index, int x) {
        sdrplay_api_GainT.gRdB$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle LNAstate$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("LNAstate"));
    public static VarHandle LNAstate$VH() {
        return sdrplay_api_GainT.LNAstate$VH;
    }
    /**
     * Getter for field:
     * {@snippet :
     * unsigned char LNAstate;
     * }
     */
    public static byte LNAstate$get(MemorySegment seg) {
        return (byte)sdrplay_api_GainT.LNAstate$VH.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * unsigned char LNAstate;
     * }
     */
    public static void LNAstate$set(MemorySegment seg, byte x) {
        sdrplay_api_GainT.LNAstate$VH.set(seg, x);
    }
    public static byte LNAstate$get(MemorySegment seg, long index) {
        return (byte)sdrplay_api_GainT.LNAstate$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void LNAstate$set(MemorySegment seg, long index, byte x) {
        sdrplay_api_GainT.LNAstate$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle syncUpdate$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("syncUpdate"));
    public static VarHandle syncUpdate$VH() {
        return sdrplay_api_GainT.syncUpdate$VH;
    }
    /**
     * Getter for field:
     * {@snippet :
     * unsigned char syncUpdate;
     * }
     */
    public static byte syncUpdate$get(MemorySegment seg) {
        return (byte)sdrplay_api_GainT.syncUpdate$VH.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * unsigned char syncUpdate;
     * }
     */
    public static void syncUpdate$set(MemorySegment seg, byte x) {
        sdrplay_api_GainT.syncUpdate$VH.set(seg, x);
    }
    public static byte syncUpdate$get(MemorySegment seg, long index) {
        return (byte)sdrplay_api_GainT.syncUpdate$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void syncUpdate$set(MemorySegment seg, long index, byte x) {
        sdrplay_api_GainT.syncUpdate$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle minGr$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("minGr"));
    public static VarHandle minGr$VH() {
        return sdrplay_api_GainT.minGr$VH;
    }
    /**
     * Getter for field:
     * {@snippet :
     * sdrplay_api_MinGainReductionT minGr;
     * }
     */
    public static int minGr$get(MemorySegment seg) {
        return (int)sdrplay_api_GainT.minGr$VH.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * sdrplay_api_MinGainReductionT minGr;
     * }
     */
    public static void minGr$set(MemorySegment seg, int x) {
        sdrplay_api_GainT.minGr$VH.set(seg, x);
    }
    public static int minGr$get(MemorySegment seg, long index) {
        return (int)sdrplay_api_GainT.minGr$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void minGr$set(MemorySegment seg, long index, int x) {
        sdrplay_api_GainT.minGr$VH.set(seg.asSlice(index*sizeof()), x);
    }
    public static MemorySegment gainVals$slice(MemorySegment seg) {
        return seg.asSlice(12, 12);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(long len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemorySegment addr, SegmentScope scope) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, scope); }
}


