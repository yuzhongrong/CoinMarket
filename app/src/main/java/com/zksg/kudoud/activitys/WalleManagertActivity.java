package com.zksg.kudoud.activitys;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.paymennt.crypto.CoinType;
import com.paymennt.crypto.bip32.Network;
import com.zksg.kudoud.BR;
import com.zksg.kudoud.R;
import com.zksg.kudoud.adapters.MemePoolListdapter;
import com.zksg.kudoud.adapters.WalletNetworkListdapter;
import com.zksg.kudoud.entitys.WalletNetworkEntity;
import com.zksg.kudoud.state.CoinsDetailViewModel;
import com.zksg.kudoud.state.WalletManagerActivityViewModel;
import com.zksg.kudoud.utils.IntentUtils;

import java.util.ArrayList;
import java.util.List;

public class WalleManagertActivity extends BaseDialogActivity {

    WalletManagerActivityViewModel mWalletManagerActivityViewModel;
    @Override
    protected void initViewModel() {
        mWalletManagerActivityViewModel=getActivityScopeViewModel(WalletManagerActivityViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_wallet_manager, BR.vm,mWalletManagerActivityViewModel)
                .addBindingParam(BR.click,new WalleManagertActivity.ClickProxy())
                .addBindingParam(BR.adapter,new WalletNetworkListdapter(this));
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<WalletNetworkEntity> leftDatas=new ArrayList<>();
        //目前只支持solana网络
        leftDatas.add(new WalletNetworkEntity(R.mipmap.ic_solana_common, 501));
        mWalletManagerActivityViewModel.leftDatas.postValue(leftDatas);
    }

    public class ClickProxy {
//        IntentUtils.


        public void close(){
            WalleManagertActivity.this.finish();
        }

        public void startCreateWallet(){
            IntentUtils.openIntent(WalleManagertActivity.this,new Intent(WalleManagertActivity.this,CreateWalletActivity.class));
        }

    }

}
