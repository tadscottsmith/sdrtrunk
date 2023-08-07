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
 *     char SerNo[64];
 *     unsigned char hwVer;
 *     sdrplay_api_TunerSelectT tuner;
 *     sdrplay_api_RspDuoModeT rspDuoMode;
 *     double rspDuoSampleFreq;
 *     HANDLE dev;
 * };
 * }
 */
public class sdrplay_api_DeviceT {

    static final StructLayout $struct$LAYOUT = MemoryLayout.structLayout(
        MemoryLayout.sequenceLayout(64, Constants$root.C_CHAR$LAYOUT).withName("SerNo"),
        Constants$root.C_CHAR$LAYOUT.withName("hwVer"),
        MemoryLayout.paddingLayout(24),
        Constants$root.C_INT$LAYOUT.withName("tuner"),
        Constants$root.C_INT$LAYOUT.withName("rspDuoMode"),
        MemoryLayout.paddingLayout(32),
        Constants$root.C_DOUBLE$LAYOUT.withName("rspDuoSampleFreq"),
        Constants$root.C_POINTER$LAYOUT.withName("dev")
    );
    public static MemoryLayout $LAYOUT() {
        return sdrplay_api_DeviceT.$struct$LAYOUT;
    }
    public static MemorySegment SerNo$slice(MemorySegment seg) {
        return seg.asSlice(0, 64);
    }
    static final VarHandle hwVer$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("hwVer"));
    public static VarHandle hwVer$VH() {
        return sdrplay_api_DeviceT.hwVer$VH;
    }
    /**
     * Getter for field:
     * {@snippet :
     * unsigned char hwVer;
     * }
     */
    public static byte hwVer$get(MemorySegment seg) {
        return (byte)sdrplay_api_DeviceT.hwVer$VH.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * unsigned char hwVer;
     * }
     */
    public static void hwVer$set(MemorySegment seg, byte x) {
        sdrplay_api_DeviceT.hwVer$VH.set(seg, x);
    }
    public static byte hwVer$get(MemorySegment seg, long index) {
        return (byte)sdrplay_api_DeviceT.hwVer$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void hwVer$set(MemorySegment seg, long index, byte x) {
        sdrplay_api_DeviceT.hwVer$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle tuner$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("tuner"));
    public static VarHandle tuner$VH() {
        return sdrplay_api_DeviceT.tuner$VH;
    }
    /**
     * Getter for field:
     * {@snippet :
     * sdrplay_api_TunerSelectT tuner;
     * }
     */
    public static int tuner$get(MemorySegment seg) {
        return (int)sdrplay_api_DeviceT.tuner$VH.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * sdrplay_api_TunerSelectT tuner;
     * }
     */
    public static void tuner$set(MemorySegment seg, int x) {
        sdrplay_api_DeviceT.tuner$VH.set(seg, x);
    }
    public static int tuner$get(MemorySegment seg, long index) {
        return (int)sdrplay_api_DeviceT.tuner$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void tuner$set(MemorySegment seg, long index, int x) {
        sdrplay_api_DeviceT.tuner$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle rspDuoMode$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("rspDuoMode"));
    public static VarHandle rspDuoMode$VH() {
        return sdrplay_api_DeviceT.rspDuoMode$VH;
    }
    /**
     * Getter for field:
     * {@snippet :
     * sdrplay_api_RspDuoModeT rspDuoMode;
     * }
     */
    public static int rspDuoMode$get(MemorySegment seg) {
        return (int)sdrplay_api_DeviceT.rspDuoMode$VH.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * sdrplay_api_RspDuoModeT rspDuoMode;
     * }
     */
    public static void rspDuoMode$set(MemorySegment seg, int x) {
        sdrplay_api_DeviceT.rspDuoMode$VH.set(seg, x);
    }
    public static int rspDuoMode$get(MemorySegment seg, long index) {
        return (int)sdrplay_api_DeviceT.rspDuoMode$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void rspDuoMode$set(MemorySegment seg, long index, int x) {
        sdrplay_api_DeviceT.rspDuoMode$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle rspDuoSampleFreq$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("rspDuoSampleFreq"));
    public static VarHandle rspDuoSampleFreq$VH() {
        return sdrplay_api_DeviceT.rspDuoSampleFreq$VH;
    }
    /**
     * Getter for field:
     * {@snippet :
     * double rspDuoSampleFreq;
     * }
     */
    public static double rspDuoSampleFreq$get(MemorySegment seg) {
        return (double)sdrplay_api_DeviceT.rspDuoSampleFreq$VH.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * double rspDuoSampleFreq;
     * }
     */
    public static void rspDuoSampleFreq$set(MemorySegment seg, double x) {
        sdrplay_api_DeviceT.rspDuoSampleFreq$VH.set(seg, x);
    }
    public static double rspDuoSampleFreq$get(MemorySegment seg, long index) {
        return (double)sdrplay_api_DeviceT.rspDuoSampleFreq$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void rspDuoSampleFreq$set(MemorySegment seg, long index, double x) {
        sdrplay_api_DeviceT.rspDuoSampleFreq$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle dev$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("dev"));
    public static VarHandle dev$VH() {
        return sdrplay_api_DeviceT.dev$VH;
    }
    /**
     * Getter for field:
     * {@snippet :
     * HANDLE dev;
     * }
     */
    public static MemorySegment dev$get(MemorySegment seg) {
        return (java.lang.foreign.MemorySegment)sdrplay_api_DeviceT.dev$VH.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * HANDLE dev;
     * }
     */
    public static void dev$set(MemorySegment seg, MemorySegment x) {
        sdrplay_api_DeviceT.dev$VH.set(seg, x);
    }
    public static MemorySegment dev$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemorySegment)sdrplay_api_DeviceT.dev$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void dev$set(MemorySegment seg, long index, MemorySegment x) {
        sdrplay_api_DeviceT.dev$VH.set(seg.asSlice(index*sizeof()), x);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(long len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemorySegment addr, SegmentScope scope) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, scope); }
}


