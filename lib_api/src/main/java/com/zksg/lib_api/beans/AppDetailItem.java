package com.zksg.lib_api.beans;

public class AppDetailItem {
    private String url;
    private String value;
    private String title;

    public AppDetailItem(String url, String value, String title) {
        this.url = url;
        this.value = value;
        this.title = title;
    }

    public String getIconId() {
        return url;
    }

    public AppDetailItem setIconId(String iconId) {
        this.url = iconId;
        return this;
    }

    public String getValue() {
        return value;
    }

    public AppDetailItem setValue(String value) {
        this.value = value;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public AppDetailItem setTitle(String title) {
        this.title = title;
        return this;
    }
}
