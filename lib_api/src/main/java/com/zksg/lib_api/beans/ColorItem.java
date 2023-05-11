package com.zksg.lib_api.beans;

public class ColorItem {
    private boolean isSelected;
    private Object color;


    private Type type;

    public ColorItem(Object color,boolean isSelected,Type type) {
        this.isSelected = isSelected;
        this.color = color;
        this.type=type;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Object getColor() {
        return color;
    }

    public void setColor(Object color) {
        this.color = color;
    }


    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


    public enum Type{
        NONE,
        ADD
    }
}
