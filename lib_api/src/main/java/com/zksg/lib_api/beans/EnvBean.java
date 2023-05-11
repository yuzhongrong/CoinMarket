package com.zksg.lib_api.beans;

public class EnvBean {
    private String name;
    private int icon;
    private boolean open;
    private Type type;



    public EnvBean(String name, int icon, boolean open,Type type) {
        this.name = name;
        this.icon = icon;
        this.open = open;
        this.type=type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type{
        NONE,
        ADD,
    }
}
