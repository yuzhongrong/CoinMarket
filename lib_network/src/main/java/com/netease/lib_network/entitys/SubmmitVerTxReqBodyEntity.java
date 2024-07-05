package com.netease.lib_network.entitys;


public class SubmmitVerTxReqBodyEntity {

    private String tx;

    private long lastValidBlockHeight;

    private String pubkey58;
    private String signature58;



    public SubmmitVerTxReqBodyEntity(String tx64, long lastValidBlockHeight,String pubkey58,String signature58) {
        this.tx = tx64;
        this.lastValidBlockHeight=lastValidBlockHeight;
        this.pubkey58=pubkey58;
        this.signature58=signature58;
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

    public String getPubkey58() {
        return pubkey58;
    }

    public void setPubkey58(String pubkey58) {
        this.pubkey58 = pubkey58;
    }

    public String getSignature58() {
        return signature58;
    }

    public void setSignature58(String signature58) {
        this.signature58 = signature58;
    }

}
