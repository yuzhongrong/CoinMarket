package com.zksg.kudoud.wallet.data.jupswap;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public class VarUInt {

    // 编码VarUInt
    public static byte[] getBytes(int value) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while (true) {
            if ((value & ~0x7F) == 0) {
                bos.write(value);
                return bos.toByteArray();
            } else {
                bos.write((value & 0x7F) | 0x80);
                value >>>= 7;
            }
        }
    }

    // 解码VarUInt
    public static int decode(ByteBuffer buffer) {
        int value = 0;
        int shift = 0;
        while (true) {
            byte b = buffer.get();
            value |= (b & 0x7F) << shift;
            if ((b & 0x80) == 0) break;
            shift += 7;
        }
        return value;
    }

}
