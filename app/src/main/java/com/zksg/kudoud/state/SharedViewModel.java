package com.zksg.kudoud.state;


import androidx.lifecycle.ViewModel;

import com.kunminx.architecture.domain.message.MutableResult;
import com.kunminx.architecture.ui.callback.ProtectedUnPeekLiveData;
import com.kunminx.architecture.ui.callback.UnPeekLiveData;
import com.kunminx.architecture.ui.state.State;
import com.zksg.kudoud.contants.CoinType;
import com.zksg.kudoud.entitys.SelectWalletEntity;
import com.zksg.kudoud.entitys.UiWalletToken;
import com.zksg.kudoud.state.load.BaseLoadingViewModel;
import com.zksg.kudoud.utils.manager.SimpleWallet;
import com.zksg.lib_api.beans.EnvBean;

import java.util.List;


public class SharedViewModel extends ViewModel {
    private final UnPeekLiveData<EnvBean> addOneEnvNotify = new UnPeekLiveData<>();

    public final UnPeekLiveData<Boolean> addTokenNotify=new UnPeekLiveData<>();

    public final UnPeekLiveData<Boolean> tokenlistUpdateNotify=new UnPeekLiveData<>();

    public final UnPeekLiveData<UiWalletToken> toExchangePage=new UnPeekLiveData<>();

    public ProtectedUnPeekLiveData<UiWalletToken> getToExchangePageNotify() {
        return toExchangePage;
    }
    public void requestToExchangePageNotify(UiWalletToken value) {
        toExchangePage.setValue(value);
    }


    public ProtectedUnPeekLiveData<Boolean> getTokenListUpdateNotify() {
        return tokenlistUpdateNotify;
    }
    public void requestTokenListUpdateNotify(Boolean value) {
        tokenlistUpdateNotify.setValue(value);
    }


    private final UnPeekLiveData<SelectWalletEntity> selectWallet=new UnPeekLiveData<>(new SelectWalletEntity(CoinType.SOLANA.getKey(),""));

     public ProtectedUnPeekLiveData<EnvBean> getOneEnvNotify() {
         return addOneEnvNotify;
     }


    public ProtectedUnPeekLiveData<Boolean> getAddTokenNotify() {
        return addTokenNotify;
    }


    public void requestAddToken(Boolean value) {
        addTokenNotify.setValue(value);
    }




    public final UnPeekLiveData<Boolean> fristPageClose=new UnPeekLiveData<>();





     public void requestToAddOneEnv(EnvBean env) {
         addOneEnvNotify.setValue(env);
     }


     //当前选中钱包是哪个网络network 的哪个钱包 currentWalletkeyAlias

     public void requestSelectWallet(SelectWalletEntity wallet) {
         selectWallet.clean();
         selectWallet.setValue(wallet);
     }
     public ProtectedUnPeekLiveData<SelectWalletEntity> getOneSelectWallet() {
         return selectWallet;
     }


     //选中哪个钱包 默认是solana网络
//     public MutableResult<SelectWalletEntity> selectWallet=new MutableResult<>(new SelectWalletEntity(CoinType.SOLANA.getKey(),""));



}
