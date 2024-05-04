package com.zksg.kudoud.entitys;

import com.netease.lib_network.entitys.NewWalletToken;

public class UiWalletToken extends NewWalletToken {
    private int resId;
    private boolean isShow=false;


    public UiWalletToken(String mint, String balance, String decimal, String price, String symbol, String imageUrl,int resId) {
        super(mint, balance, decimal, price, symbol, imageUrl);
        this.resId=resId;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }
}
