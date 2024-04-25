package com.zksg.kudoud.utils;

import org.bitcoinj.crypto.MnemonicCode;
import org.bitcoinj.crypto.MnemonicException;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;

public class MnemonicUtils {

    public static String generateMnemonic() throws MnemonicException.MnemonicLengthException, IOException {
        SecureRandom random = new SecureRandom();
        byte[] entropy = new byte[16]; // 16 bytes entropy for 12-word mnemonic
        random.nextBytes(entropy);

        MnemonicCode mnemonicCode = new MnemonicCode();
        List<String> words = mnemonicCode.toMnemonic(entropy);
        return String.join(" ", words);
    }



}
