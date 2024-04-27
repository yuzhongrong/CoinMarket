package com.zksg.kudoud.activitys;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.paymennt.crypto.bip32.Network;
import com.tencent.mmkv.MMKV;
import com.zksg.kudoud.BR;
import com.zksg.kudoud.R;
import com.zksg.kudoud.adapters.MemePoolListdapter;
import com.zksg.kudoud.adapters.WalletListdapter;
import com.zksg.kudoud.adapters.WalletNetworkListdapter;
import com.zksg.kudoud.contants.CoinType;
import com.zksg.kudoud.entitys.SelectWalletEntity;
import com.zksg.kudoud.entitys.WalletNetworkEntity;
import com.zksg.kudoud.state.CoinsDetailViewModel;
import com.zksg.kudoud.state.SharedViewModel;
import com.zksg.kudoud.state.WalletManagerActivityViewModel;
import com.zksg.kudoud.utils.IntentUtils;
import com.zksg.kudoud.utils.MapUtils;
import com.zksg.kudoud.utils.WalletUtils;
import com.zksg.kudoud.utils.manager.SimpleWallet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WalleManagertActivity extends BaseDialogActivity {

    WalletManagerActivityViewModel mWalletManagerActivityViewModel;
    SharedViewModel mSharedViewModel=null;
    @Override
    protected void initViewModel() {
        mWalletManagerActivityViewModel=getActivityScopeViewModel(WalletManagerActivityViewModel.class);
        mSharedViewModel=getApplicationScopeViewModel(SharedViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_wallet_manager, BR.vm,mWalletManagerActivityViewModel)
                .addBindingParam(BR.click,new WalleManagertActivity.ClickProxy())
                .addBindingParam(BR.adapter,new WalletNetworkListdapter(this))
                .addBindingParam(BR.walltlistadapter,new WalletListdapter(this))
                .addBindingParam(BR.vm_share,mSharedViewModel);



    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InitData();

    }

    @Override
    protected void onResume() {
        super.onResume();

        //动态更新当前选中网络钱包列表
        String network=MMKV.mmkvWithID("currentWallet").decodeString("netwrokgroup", CoinType.SOLANA.getKey());
        List<SimpleWallet> simpleWallets= WalletUtils.getAllLocalSimpleWalletByNetwork(network);
        if(simpleWallets==null)return;
        mWalletManagerActivityViewModel.rightDatas.postValue(simpleWallets);
    }


    /** mmkv 钱包存储结构
     *  MMKV.defaultMMKV()--存储所有钱包key=(keyAlias), value= (json MyWallet)
     *
     *                |-----solana ,eth,btc ...
     *  MMKV.mmkvWithID

     */
    private void InitData(){
        List<WalletNetworkEntity> leftDatas=new ArrayList<>();
        //目前只支持solana网络
        leftDatas.add(new WalletNetworkEntity(R.mipmap.ic_solana_common, 501));
        mWalletManagerActivityViewModel.leftDatas.postValue(leftDatas);

    }


    public SharedViewModel getSharedViewModel(){
        return mSharedViewModel;
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
