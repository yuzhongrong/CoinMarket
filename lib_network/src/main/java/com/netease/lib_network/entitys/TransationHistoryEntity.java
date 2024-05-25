package com.netease.lib_network.entitys;

import com.google.gson.annotations.SerializedName;

public class TransationHistoryEntity {




        @SerializedName("sender")
        private String sender;
        @SerializedName("receiver")
        private String receiver;
        @SerializedName("amount")
        private String amount;
        @SerializedName("signature")
        private String signature;
        @SerializedName("blockTime")
        private long blockTime;
        @SerializedName("isSolTransfer")
        private boolean isSolTransfer;
        @SerializedName("mint")
        private String mint="";

        @SerializedName("decimals")
        private int decimals;
        @SerializedName("symbol")
        private String symbol;
        @SerializedName("logoURI")
        private String logoURI;

    public int getDecimals() {
        return decimals;
    }

    public void setDecimals(int decimals) {
        this.decimals = decimals;
    }

    public String getLogoURI() {
        return logoURI;
    }

    public void setLogoURI(String logoURI) {
        this.logoURI = logoURI;
    }

    //下面这2组数据是从其他地方拿到的 不是链上原生数据




        public String getSender() {
            return sender;
        }

        public void setSender(String sender) {
            this.sender = sender;
        }

        public String getReceiver() {
            return receiver;
        }

        public void setReceiver(String receiver) {
            this.receiver = receiver;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public long getBlockTime() {
            return blockTime;
        }

        public void setBlockTime(int blockTime) {
            this.blockTime = blockTime;
        }

        public boolean isIsSolTransfer() {
            return isSolTransfer;
        }

        public void setIsSolTransfer(boolean isSolTransfer) {
            this.isSolTransfer = isSolTransfer;
        }

        public String getMint() {
            return mint;
        }

        public void setMint(String mint) {
            this.mint = mint;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }



}
