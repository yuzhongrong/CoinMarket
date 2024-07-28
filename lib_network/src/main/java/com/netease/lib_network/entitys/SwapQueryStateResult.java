package com.netease.lib_network.entitys;

import com.google.gson.annotations.SerializedName;

public class SwapQueryStateResult {


    @SerializedName("id")
    private int id;
    @SerializedName("tx")
    private String tx;
    @SerializedName("state")
    private String state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTx() {
        return tx;
    }

    public void setTx(String tx) {
        this.tx = tx;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
