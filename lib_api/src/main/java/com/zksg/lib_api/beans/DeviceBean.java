package com.zksg.lib_api.beans;

public class DeviceBean {
    private String  mac;
    private String name;
    private Integer icon;

    public DeviceBean(String mac, String name, Integer icon) {
        this.mac = mac;
        this.name = name;
        this.icon = icon;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }
}
