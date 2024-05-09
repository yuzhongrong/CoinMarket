package com.zksg.kudoud.callback;

import com.netease.lib_network.entitys.NewWalletToken;

import java.util.List;

public interface WalletUpdateTokensBalanceCallback {
    void updateWalletTokensBalance(List<NewWalletToken> datas);

}
