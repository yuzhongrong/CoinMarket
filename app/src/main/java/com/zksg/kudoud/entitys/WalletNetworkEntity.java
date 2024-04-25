package com.zksg.kudoud.entitys;

public class WalletNetworkEntity {
    private int imgId;
    private int code;//网络标识码

    public WalletNetworkEntity(int img, int code) {
        this.imgId = img;
        this.code=code;
    }

    public int getImg() {
        return imgId;
    }

    public void setImg(int img) {
        this.imgId = img;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
