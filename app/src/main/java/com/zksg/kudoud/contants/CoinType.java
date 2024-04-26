package com.zksg.kudoud.contants;

import com.zksg.kudoud.R;

public enum CoinType {

    SOLANA("SOLANA",501);
    private final String key;
    private final int value;

    CoinType(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }
}
