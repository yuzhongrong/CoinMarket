package com.netease.lib_network.entitys;


public class SubmmitVerTxReqBodyEntity {

    private String tx;

    private long lastValidBlockHeight;

    public SubmmitVerTxReqBodyEntity(String tx64, long lastValidBlockHeight) {
        this.tx = tx64;
        this.lastValidBlockHeight=lastValidBlockHeight;
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
