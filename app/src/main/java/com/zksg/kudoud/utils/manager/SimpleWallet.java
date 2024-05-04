package com.zksg.kudoud.utils.manager;

import com.netease.lib_network.entitys.NewWalletToken;
import com.zksg.kudoud.entitys.UiWalletToken;

import java.io.Serializable;
import java.util.List;

public class SimpleWallet implements Serializable {
    private String name;
    private String address;
    private String keyAlias;
    private String network;
    private boolean isbackup=false;
    private String usd;
    private UiWalletToken defaultsol;


    public SimpleWallet(String keyAlias, String group, String name, String address, UiWalletToken defaultsol) {
        this.name = name;
        this.address = address;

        this.keyAlias=keyAlias;
        this.network=group;
        this.defaultsol=defaultsol;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getKeyAlias() {
        return keyAlias;
    }

    public void setKeyAlias(String keyAlias) {
        this.keyAlias = keyAlias;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public boolean isIsbackup() {
        return isbackup;
    }

    public void setIsbackup(boolean isbackup) {
        this.isbackup = isbackup;
    }
    public String getUsd() {
        return usd;
    }

    public void setUsd(String usd) {
        this.usd = usd;
    }
    public UiWalletToken getDefaultsol() {
        return defaultsol;
    }

    public void setDefaultsol(UiWalletToken defaultsol) {
        this.defaultsol = defaultsol;
    }



}
