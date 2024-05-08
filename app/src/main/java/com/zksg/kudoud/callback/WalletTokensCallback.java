package com.zksg.kudoud.callback;

import com.netease.lib_network.entitys.JupToken;

import java.util.List;

public interface  WalletTokensCallback {

    void WalletHotCoins(List<JupToken> datas);
}
