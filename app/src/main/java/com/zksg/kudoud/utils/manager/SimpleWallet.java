package com.zksg.kudoud.utils.manager;

import com.zksg.kudoud.entitys.TokenInfoEntity;

import java.io.Serializable;
import java.util.List;

public class SimpleWallet implements Serializable {
    private String name;
    private String address;
    private List<TokenInfoEntity> tokens;
    private String keyAlias;
    private String network;
    private boolean isbackup=false;




    public SimpleWallet(String keyAlias, String group, String name, String address, List<TokenInfoEntity> tokens) {
        this.name = name;
        this.address = address;
        this.tokens = tokens;
        this.keyAlias=keyAlias;
        this.network=group;
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

    public List<TokenInfoEntity> getTokens() {
        return tokens;
    }

    public void setTokens(List<TokenInfoEntity> tokens) {
        this.tokens = tokens;
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

}
