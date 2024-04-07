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

package io.github.dsheirer.module.decode.p25.phase1.message.lc.motorola;

import io.github.dsheirer.bits.CorrectedBinaryMessage;
import io.github.dsheirer.bits.IntField;
import io.github.dsheirer.identifier.Identifier;
import io.github.dsheirer.identifier.radio.RadioIdentifier;
import io.github.dsheirer.module.decode.p25.identifier.radio.APCO25RadioIdentifier;
import io.github.dsheirer.module.decode.p25.phase1.message.lc.LinkControlWord;
import java.util.ArrayList;
import java.util.List;

/**
 * Motorola Unknown Opcode 10.
 *
 * This may be a unit paging message.  When it is sent, it's sent over the control as a TSBK message and across the
 * active traffic channels as a Link Control.
 *
 * Examples:
 * Motorola Opcode 10 LC Detected: 0A9000A100006667DE
 * Motorola Opcode 10 LC Detected: 0A9000210000666750
 * Motorola Opcode 10 LC Detected: 0A90002100006667F4
 * Motorola Opcode 10 LC Detected: 0A900031000066ED45
 * Motorola Opcode 10 LC Detected: 0A900031000066ED30
 * Motorola Opcode 10 LC Detected: 0A900031000066EDA3
 * Motorola Opcode 10 LC Detected: 0A900031000066ED13
 *
 * Motorola Opcode 10 TSBK Detected: 0A9000000000A16667DE 8A75
 * Motorola Opcode 10 TSBK Detected: 0A900000000021666750 270B
 * Motorola Opcode 10 TSBK Detected: 0A9000000000216667F4 D265
 * Motorola Opcode 10 TSBK Detected: 0A90000000003166ED45 8A6B
 * Motorola Opcode 10 TSBK Detected: 0A90000000003166ED30 A459
 * Motorola Opcode 10 TSBK Detected: 8A90000000003166ED30 406D (same, just LAST BLOCK flag is set in bit 0)
 * Motorola Opcode 10 TSBK Detected: 0A90000000003166EDA3 1783
 * Motorola Opcode 10 TSBK Detected: 0A90000000003166ED13 3059
 * Motorola Opcode 10 TSBK Detected: 8A90000000003166ED13 546C (same, just LAST BLOCK flag is set in bit 0)
 *
 *
 * In both examples, it seems addressed to target radio 0x6667DE, 0x666750, etc.  Unsure of the value 0xA1, 0x21, 0x31,
 * but it's consistent across both the TSBK and LC messages when addressed to the same radio.
 */
public class LCMotorolaOpcode10 extends LinkControlWord
{
    private static final IntField UNKNOWN = IntField.length8(OCTET_3_BIT_24);
    private static final IntField TARGET_ADDRESS = IntField.length24(OCTET_6_BIT_48);
    private RadioIdentifier mTargetAddress;
    private List<Identifier> mIdentifiers;

    /**
     * Constructs a Link Control Word from the binary message sequence.
     *
     * @param message
     */
    public LCMotorolaOpcode10(CorrectedBinaryMessage message)
    {
        super(message);
    }

    /**
     * Target Address
     */
    public RadioIdentifier getTargetAddress()
    {
        if(mTargetAddress == null)
        {
            mTargetAddress = APCO25RadioIdentifier.createTo(getInt(TARGET_ADDRESS));
        }

        return mTargetAddress;
    }

    public int getUnknown()
    {
        return getInt(UNKNOWN);
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        if(!isValid())
        {
            sb.append("**CRC-FAILED** ");
        }

        if(isEncrypted())
        {
            sb.append(" ENCRYPTED");
        }
        else
        {
            sb.append("MOTOROLA UNKNOWN OPCODE 10 TO:").append(getTargetAddress());
            sb.append(" UNKNOWN:").append(getUnknown());
            sb.append(" MSG:").append(getMessage().toHexString());
        }
        return sb.toString();
    }

    /**
     * List of identifiers contained in this message
     */

    @Override
    public List<Identifier> getIdentifiers()
    {
        if(mIdentifiers == null)
        {
            mIdentifiers = new ArrayList<>();
            mIdentifiers.add(getTargetAddress());
        }

        return mIdentifiers;
    }
}
