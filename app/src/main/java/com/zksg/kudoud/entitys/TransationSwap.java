package com.zksg.kudoud.entitys;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransationSwap {


    @SerializedName("recentBlockhash")
    private String recentBlockhash;
    @SerializedName("feePayer")
    private String feePayer;
    @SerializedName("nonceInfo")
    private Object nonceInfo;
    @SerializedName("instructions")
    private List<InstructionsDTO> instructions;
    @SerializedName("signers")
    private List<String> signers;

    public String getRecentBlockhash() {
        return recentBlockhash;
    }

    public void setRecentBlockhash(String recentBlockhash) {
        this.recentBlockhash = recentBlockhash;
    }

    public String getFeePayer() {
        return feePayer;
    }

    public void setFeePayer(String feePayer) {
        this.feePayer = feePayer;
    }

    public Object getNonceInfo() {
        return nonceInfo;
    }

    public void setNonceInfo(Object nonceInfo) {
        this.nonceInfo = nonceInfo;
    }

    public List<InstructionsDTO> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<InstructionsDTO> instructions) {
        this.instructions = instructions;
    }

    public List<String> getSigners() {
        return signers;
    }

    public void setSigners(List<String> signers) {
        this.signers = signers;
    }

    public static class InstructionsDTO {
        @SerializedName("keys")
        private List<StoreAccountMeta> keys;
        @SerializedName("programId")
        private String programId;
        @SerializedName("data")
        private byte[] data;

        public List<StoreAccountMeta> getKeys() {
            return keys;
        }

        public void setKeys(List<StoreAccountMeta> keys) {
            this.keys = keys;
        }

        public String getProgramId() {
            return programId;
        }

        public void setProgramId(String programId) {
            this.programId = programId;
        }

        public byte[] getData() {
            return data;
        }

        public void setData(byte[] data) {
            this.data = data;
        }
    }
}
