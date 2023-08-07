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

import java.lang.foreign.FunctionDescriptor;
import java.lang.invoke.MethodHandle;

final class constants$2 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$2() {}
    static final FunctionDescriptor sdrplay_api_UnlockDeviceApi_t$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT);
    static final FunctionDescriptor sdrplay_api_UnlockDeviceApi_t_UP$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT);
    static final MethodHandle sdrplay_api_UnlockDeviceApi_t_UP$MH = RuntimeHelper.upcallHandle(sdrplay_api_UnlockDeviceApi_t.class, "apply", constants$2.sdrplay_api_UnlockDeviceApi_t_UP$FUNC);
    static final FunctionDescriptor sdrplay_api_UnlockDeviceApi_t_DOWN$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT);
    static final MethodHandle sdrplay_api_UnlockDeviceApi_t_DOWN$MH = RuntimeHelper.downcallHandle(
        constants$2.sdrplay_api_UnlockDeviceApi_t_DOWN$FUNC
    );
    static final FunctionDescriptor sdrplay_api_GetDevices_t$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final FunctionDescriptor sdrplay_api_GetDevices_t_UP$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle sdrplay_api_GetDevices_t_UP$MH = RuntimeHelper.upcallHandle(sdrplay_api_GetDevices_t.class, "apply", constants$2.sdrplay_api_GetDevices_t_UP$FUNC);
    static final FunctionDescriptor sdrplay_api_GetDevices_t_DOWN$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle sdrplay_api_GetDevices_t_DOWN$MH = RuntimeHelper.downcallHandle(
        constants$2.sdrplay_api_GetDevices_t_DOWN$FUNC
    );
    static final FunctionDescriptor sdrplay_api_SelectDevice_t$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final FunctionDescriptor sdrplay_api_SelectDevice_t_UP$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle sdrplay_api_SelectDevice_t_UP$MH = RuntimeHelper.upcallHandle(sdrplay_api_SelectDevice_t.class, "apply", constants$2.sdrplay_api_SelectDevice_t_UP$FUNC);
    static final FunctionDescriptor sdrplay_api_SelectDevice_t_DOWN$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle sdrplay_api_SelectDevice_t_DOWN$MH = RuntimeHelper.downcallHandle(
        constants$2.sdrplay_api_SelectDevice_t_DOWN$FUNC
    );
}


