package com.zksg.lib_api.beans;

public class DeviceOptiontem extends HomeItem{


    private boolean isOpen;//控制开关


    private Type type;
    public DeviceOptiontem(Integer iconId, String value, String title,Type type,boolean isOpen) {
        super(iconId, value, title);
        this.isOpen=isOpen;
        this.type=type;
    }


    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }


   public enum Type{
        NONE,
        ENABLE
    }

    public Type getType() {
        return type;
    }



}
