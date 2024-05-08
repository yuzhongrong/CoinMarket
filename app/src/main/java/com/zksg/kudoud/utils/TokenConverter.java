package com.zksg.kudoud.utils;

import com.netease.lib_network.entitys.JupToken;
import com.zksg.kudoud.entitys.UiWalletToken;

import java.util.ArrayList;
import java.util.List;

public class TokenConverter {

    public static List<UiWalletToken> convertJubTokensToUiWalletTokens(List<JupToken> jubTokens) {
        List<UiWalletToken> uiWalletTokens = new ArrayList<>();
        for (JupToken jubToken : jubTokens) {
            String mint = jubToken.getAddress();
            String balance = "0"; // Set default balance as 0 or get it from somewhere
            String decimal = String.valueOf(jubToken.getDecimals());
            String price = "0"; // Set default price as 0 or get it from somewhere
            String symbol = jubToken.getSymbol();
            String imageUrl = jubToken.getLogoURI();
            String name = jubToken.getName();
            int resId = 0; // Set default resId as 0 or get it from somewhere
            UiWalletToken uiWalletToken = new UiWalletToken(mint, balance, decimal, price, symbol, name, imageUrl, resId);
            uiWalletTokens.add(uiWalletToken);
        }
        return uiWalletTokens;
    }

    public static boolean  FilterJubTokens(List<UiWalletToken> hottokens,List<UiWalletToken> localtokens){
        if(localtokens==null||hottokens==null)return false;
        return hottokens.removeAll(localtokens);

    }

}
