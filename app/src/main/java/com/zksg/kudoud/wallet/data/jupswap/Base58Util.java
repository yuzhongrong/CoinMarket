package com.zksg.kudoud.wallet.data.jupswap;
import org.bitcoinj.core.Base58;
public class Base58Util {

    public static byte[] decode(String input) {
        return Base58.decode(input);
    }

    public static String encode(byte[] input) {
        return Base58.encode(input);
    }
}
