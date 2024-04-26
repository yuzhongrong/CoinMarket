package com.zksg.kudoud.callback;

public interface WalletCreateCallback {
    void walletCreateComplete(String keyAlias); //keyAlias="SolanaWallet_"+address
    void walletCreateFail(Exception err);
}
