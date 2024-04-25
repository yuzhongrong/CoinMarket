package com.zksg.kudoud.utils.manager;

import com.zksg.kudoud.entitys.TokenInfoEntity;

import java.util.List;

public class MySolanaWallet {
    private String name;
    private String address;
    private List<TokenInfoEntity> tokens;
    private String keyAlias;



    private byte[] encryptData; //对solanawallet加密后的数据

    public MySolanaWallet(String keyAlias,String name, String address, List<TokenInfoEntity> tokens, byte[] encryptData) {
        this.name = name;
        this.address = address;
        this.tokens = tokens;
        this.encryptData = encryptData;
        this.keyAlias=keyAlias;
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

    public byte[] getEncryptData() {
        return encryptData;
    }

    public void setEncryptData(byte[] encryptData) {
        this.encryptData = encryptData;
    }

    public String getKeyAlias() {
        return keyAlias;
    }

    public void setKeyAlias(String keyAlias) {
        this.keyAlias = keyAlias;
    }
}
