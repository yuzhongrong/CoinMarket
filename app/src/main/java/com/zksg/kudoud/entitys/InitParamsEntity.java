package com.zksg.kudoud.entitys;

public class InitParamsEntity {

    private boolean land;
    private int type;

    public InitParamsEntity(int type,boolean land) {
        this.land = land;
        this.type = type;
    }


    public boolean isLand() {
        return land;
    }

    public void setLand(boolean land) {
        this.land = land;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
