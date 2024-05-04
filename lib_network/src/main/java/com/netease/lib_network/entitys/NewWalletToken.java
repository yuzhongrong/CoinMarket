package com.netease.lib_network.entitys;

import com.netease.lib_network.R;

import java.io.Serializable;

public class NewWalletToken implements Serializable {

    private String mint;
    private String balance;
    private String decimal;
    private String price;
    private String symbol;
    private String imageUrl;

    public NewWalletToken(String mint, String balance, String decimal, String price, String symbol, String imageUrl) {
        this.mint = mint;
        this.balance = balance;
        this.decimal = decimal;
        this.price = price;
        this.symbol = symbol;
        this.imageUrl = imageUrl;

    }

    public String getMint() {
        return mint;
    }

    public void setMint(String mint) {
        this.mint = mint;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getDecimal() {
        return decimal;
    }

    public void setDecimal(String decimal) {
        this.decimal = decimal;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }



}
