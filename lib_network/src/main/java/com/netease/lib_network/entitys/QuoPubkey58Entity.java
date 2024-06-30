package com.netease.lib_network.entitys;

public class QuoPubkey58Entity {

    private QuoEntity quote;
    private String pubkey58;

    public QuoPubkey58Entity(QuoEntity quote, String pubkey58) {
        this.quote = quote;
        this.pubkey58 = pubkey58;
    }

    public QuoEntity getQuoResponse() {
        return quote;
    }

    public void setQuoResponse(QuoEntity quoResponse) {
        this.quote = quoResponse;
    }

    public String getPubkey58() {
        return pubkey58;
    }

    public void setPubkey58(String pubkey58) {
        this.pubkey58 = pubkey58;
    }
}
