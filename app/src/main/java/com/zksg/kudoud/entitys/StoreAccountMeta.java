package com.zksg.kudoud.entitys;

import com.google.gson.annotations.SerializedName;

public class StoreAccountMeta {
    @SerializedName("pubkey")
    private String pubkey;
    @SerializedName("isSigner")
    private boolean isSigner;
    @SerializedName("isWritable")
    private boolean isWritable;

    public String getPubkey() {
        return pubkey;
    }

    public void setPubkey(String pubkey) {
        this.pubkey = pubkey;
    }

    public boolean isIsSigner() {
        return isSigner;
    }

    public void setIsSigner(boolean isSigner) {
        this.isSigner = isSigner;
    }

    public boolean isIsWritable() {
        return isWritable;
    }

    public void setIsWritable(boolean isWritable) {
        this.isWritable = isWritable;
    }
}
