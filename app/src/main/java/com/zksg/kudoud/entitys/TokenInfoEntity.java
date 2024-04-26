package com.zksg.kudoud.entitys;

import java.io.Serializable;

public class TokenInfoEntity implements Serializable {
    private String address;
    private String logourl;
    private String symbol;
    private int resId;


    public TokenInfoEntity(String contract, String logourl,int resId, String symbol) {
        this.address = contract;
        this.logourl = logourl;
        this.symbol = symbol;
        this.resId=resId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogurl() {
        return logourl;
    }

    public void setLogurl(String logurl) {
        this.logourl = logurl;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

}
