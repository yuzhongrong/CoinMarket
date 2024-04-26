package com.zksg.kudoud.entitys;

import java.io.Serializable;

public class SelectWalletEntity implements Serializable {
    private String netwrokgroup;
    private String keyAlias;

    public SelectWalletEntity(String netwrokgroup, String keyAlias) {
        this.netwrokgroup = netwrokgroup;
        this.keyAlias = keyAlias;

    }


    public String getNetwrokgroup() {
        return netwrokgroup;
    }

    public void setNetwrokgroup(String netwrokgroup) {
        this.netwrokgroup = netwrokgroup;
    }

    public String getKeyAlias() {
        return keyAlias;
    }

    public void setKeyAlias(String keyAlias) {
        this.keyAlias = keyAlias;
    }
}
