package com.zksg.kudoud.entitys;

import com.netease.lib_network.entitys.NewWalletToken;

import java.util.Objects;

public class UiWalletToken extends NewWalletToken {
    private int resId;
    private boolean isShow=false;
    private String name; //ex: "name":"UncleGrandpa","symbol":"Unc"
    public UiWalletToken(String mint, String balance, String decimal, String price, String symbol,String name, String imageUrl, int resId) {
        super(mint, balance, decimal, price, symbol, imageUrl);
        this.resId=resId;
        this.name=name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 在 UiWalletToken 类中重写 equals() 方法 必须要重新这样子removeall的时候才能根据自定义的这个去移除不然系统会根据内存地址判断
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UiWalletToken token = (UiWalletToken) obj;
        return Objects.equals(super.getMint(), token.getMint());
    }

}
