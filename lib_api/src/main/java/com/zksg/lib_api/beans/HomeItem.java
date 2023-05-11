package com.zksg.lib_api.beans;

public class HomeItem {
    private Integer iconId;
    private String value;
    private String title;

    public HomeItem(Integer iconId, String value, String title) {
        this.iconId = iconId;
        this.value = value;
        this.title = title;
    }

    public Integer getIconId() {
        return iconId;
    }

    public HomeItem setIconId(Integer iconId) {
        this.iconId = iconId;
        return this;
    }

    public String getValue() {
        return value;
    }

    public HomeItem setValue(String value) {
        this.value = value;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public HomeItem setTitle(String title) {
        this.title = title;
        return this;
    }
}
