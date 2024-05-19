package com.netease.lib_network.entitys;

public class BroadcastRequest {
    private String signedTransaction;

    public BroadcastRequest(String signedTransaction) {
        this.signedTransaction = signedTransaction;
    }

    public String getSignedTransaction() {
        return signedTransaction;
    }

    public void setSignedTransaction(String signedTransaction) {
        this.signedTransaction = signedTransaction;
    }
}
