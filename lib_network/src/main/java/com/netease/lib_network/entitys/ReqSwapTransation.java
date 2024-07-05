package com.netease.lib_network.entitys;



public class ReqSwapTransation {
    private String swap64;
    private String msgserialize;
    private long lastValidBlockHeight;


    public ReqSwapTransation(String swap64,String msgserialize, long lastValidBlockHeight) {
        this.swap64=swap64;
        this.msgserialize = msgserialize;
        this.lastValidBlockHeight = lastValidBlockHeight;

    }



    public String getMsgserialize() {
        return msgserialize;
    }

    public void setMsgserialize(String msgserialize) {
        this.msgserialize = msgserialize;
    }

    public long getLastValidBlockHeight() {
        return lastValidBlockHeight;
    }

    public void setLastValidBlockHeight(long lastValidBlockHeight) {
        this.lastValidBlockHeight = lastValidBlockHeight;
    }
    public String getSwap64() {
        return swap64;
    }

    public void setSwap64(String swap64) {
        this.swap64 = swap64;
    }



}
