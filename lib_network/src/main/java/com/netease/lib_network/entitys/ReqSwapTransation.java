package com.netease.lib_network.entitys;



public class ReqSwapTransation {
    private String tx;
    private long lastValidBlockHeight;

    public ReqSwapTransation(String tx, long lastValidBlockHeight) {
        this.tx = tx;
        this.lastValidBlockHeight = lastValidBlockHeight;
    }

    public String getTx() {
        return tx;
    }

    public void setTx(String tx) {
        this.tx = tx;
    }

    public long getLastValidBlockHeight() {
        return lastValidBlockHeight;
    }

    public void setLastValidBlockHeight(long lastValidBlockHeight) {
        this.lastValidBlockHeight = lastValidBlockHeight;
    }
}
