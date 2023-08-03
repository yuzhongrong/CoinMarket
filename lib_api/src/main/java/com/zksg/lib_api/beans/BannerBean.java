package com.zksg.lib_api.beans;

import com.google.gson.Gson;

public class BannerBean {
    private String type;
    private String content;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BannerContent getBannerContent() {
        return new Gson().fromJson(content,BannerContent.class);
    }

}



