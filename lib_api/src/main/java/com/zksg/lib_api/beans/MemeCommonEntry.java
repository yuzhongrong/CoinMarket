package com.zksg.lib_api.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MemeCommonEntry implements Serializable {


    @SerializedName("updateUnixTime")
    private int updateUnixTime;
    @SerializedName("updateTime")
    private String updateTime;
    @SerializedName("tokens")
    private List<MemeBaseEntry> tokens;
    @SerializedName("total")
    private int total;

    public int getUpdateUnixTime() {
        return updateUnixTime;
    }

    public void setUpdateUnixTime(int updateUnixTime) {
        this.updateUnixTime = updateUnixTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<MemeBaseEntry> getTokens() {
        return tokens;
    }

    public void setTokens(List<MemeBaseEntry> tokens) {
        this.tokens = tokens;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

//    public static class TokensDTO {
//        @SerializedName("address")
//        private String address;
//        @SerializedName("decimals")
//        private int decimals;
//        @SerializedName("lastTradeUnixTime")
//        private int lastTradeUnixTime;
//        @SerializedName("liquidity")
//        private double liquidity;
//        @SerializedName("logoURI")
//        private String logoURI;
//        @SerializedName("mc")
//        private double mc;
//        @SerializedName("name")
//        private String name;
//        @SerializedName("symbol")
//        private String symbol;
//        @SerializedName("v24hChangePercent")
//        private double v24hChangePercent;
//        @SerializedName("v24hUSD")
//        private double v24hUSD;
//
//        public String getAddress() {
//            return address;
//        }
//
//        public void setAddress(String address) {
//            this.address = address;
//        }
//
//        public int getDecimals() {
//            return decimals;
//        }
//
//        public void setDecimals(int decimals) {
//            this.decimals = decimals;
//        }
//
//        public int getLastTradeUnixTime() {
//            return lastTradeUnixTime;
//        }
//
//        public void setLastTradeUnixTime(int lastTradeUnixTime) {
//            this.lastTradeUnixTime = lastTradeUnixTime;
//        }
//
//        public double getLiquidity() {
//            return liquidity;
//        }
//
//        public void setLiquidity(double liquidity) {
//            this.liquidity = liquidity;
//        }
//
//        public String getLogoURI() {
//            return logoURI;
//        }
//
//        public void setLogoURI(String logoURI) {
//            this.logoURI = logoURI;
//        }
//
//        public double getMc() {
//            return mc;
//        }
//
//        public void setMc(double mc) {
//            this.mc = mc;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public String getSymbol() {
//            return symbol;
//        }
//
//        public void setSymbol(String symbol) {
//            this.symbol = symbol;
//        }
//
//        public double getV24hChangePercent() {
//            return v24hChangePercent;
//        }
//
//        public void setV24hChangePercent(double v24hChangePercent) {
//            this.v24hChangePercent = v24hChangePercent;
//        }
//
//        public double getV24hUSD() {
//            return v24hUSD;
//        }
//
//        public void setV24hUSD(double v24hUSD) {
//            this.v24hUSD = v24hUSD;
//        }
//    }
}
