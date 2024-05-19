package com.zksg.kudoud.callback;

public interface WalletCreateFingPrintCallback {
    void walletCreateComplete(int success); //keyAlias="SolanaWallet_"+address
    void walletCreateFingPrintFail(int errcode);
}
