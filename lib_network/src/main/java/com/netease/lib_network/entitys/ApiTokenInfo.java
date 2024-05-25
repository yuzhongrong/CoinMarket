package com.netease.lib_network.entitys;

import com.google.gson.annotations.SerializedName;

public class ApiTokenInfo {
    @SerializedName("id")
    private String id;
    @SerializedName("mintSymbol")
    private String mintSymbol;
    @SerializedName("vsToken")
    private String vsToken;
    @SerializedName("vsTokenSymbol")
    private String vsTokenSymbol;
    @SerializedName("price")
    private double price;
    @SerializedName("balance")
    private String balance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMintSymbol() {
        return mintSymbol;
    }

    public void setMintSymbol(String mintSymbol) {
        this.mintSymbol = mintSymbol;
    }

    public String getVsToken() {
        return vsToken;
    }

    public void setVsToken(String vsToken) {
        this.vsToken = vsToken;
    }

    public String getVsTokenSymbol() {
        return vsTokenSymbol;
    }

    public void setVsTokenSymbol(String vsTokenSymbol) {
        this.vsTokenSymbol = vsTokenSymbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }


}
