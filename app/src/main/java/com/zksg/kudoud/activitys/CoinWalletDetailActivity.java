package com.zksg.kudoud.activitys;

import static com.zksg.kudoud.wallet.constants.Constants.TOKEN_SOL_CONTRACT;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.kunminx.architecture.ui.page.BaseActivity;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.lxj.xpopup.XPopup;
import com.zksg.kudoud.BR;
import com.zksg.kudoud.R;
import com.zksg.kudoud.adapters.ExSwapListdapter;
import com.zksg.kudoud.adapters.TransDetailHistorysrdapter;
import com.zksg.kudoud.adapters.TransHistorysrdapter;
import com.zksg.kudoud.dialogs.ReceiverCoinDialog;
import com.zksg.kudoud.dialogs.ResetFactoryDialog;
import com.zksg.kudoud.entitys.UiWalletToken;
import com.zksg.kudoud.state.AboutViewModel;
import com.zksg.kudoud.state.CoinWalletDetailActivityViewModel;
import com.zksg.kudoud.state.SharedViewModel;
import com.zksg.kudoud.utils.IntentUtils;
import com.zksg.lib_api.beans.UpgradeBean;

public class CoinWalletDetailActivity extends BaseDialogActivity {
    UiWalletToken sol;
    CoinWalletDetailActivityViewModel mCoinWalletDetailActivityViewModel;
    UiWalletToken item;
    String wallet="";
    private SharedViewModel mSharedViewModel;

    @Override
    protected void initViewModel() {
        mSharedViewModel =
                getApplicationScopeViewModel(SharedViewModel.class);
        mCoinWalletDetailActivityViewModel=getActivityScopeViewModel(CoinWalletDetailActivityViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_wallet_coin_detail, BR.vm,mCoinWalletDetailActivityViewModel)
                .addBindingParam(BR.click,new ClickProxy())
                .addBindingParam(BR.historysAdaper,new TransDetailHistorysrdapter(this));
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }



    private void initData(){
        mCoinWalletDetailActivityViewModel.loadingVisible.observe(this, it -> {
            if(it)showDialog(); else dismissDialog();
        });

        mCoinWalletDetailActivityViewModel.historys.observe(this,it -> {
            if(it!=null&&it.size()==0){
                mCoinWalletDetailActivityViewModel.rv_empter.set(true);
            }else{
                mCoinWalletDetailActivityViewModel.rv_empter.set(false);
            }
        });
        mSharedViewModel.fristPageClose.observe(this, aBoolean -> {
            this.finish();
        });
        item= (UiWalletToken) getIntent().getExtras().get("token");
        sol= (UiWalletToken) getIntent().getExtras().get("sol");
        wallet= getIntent().getStringExtra("wallet");
        mCoinWalletDetailActivityViewModel.walletAddress.set(wallet);
        if(item!=null){
            mCoinWalletDetailActivityViewModel.currentToken.set(item);
        }

        //获取sol历史记录
        if(wallet.equals(TOKEN_SOL_CONTRACT)){
            mCoinWalletDetailActivityViewModel.getSolHistorys(wallet);
        }else{ //获取spl历史记录
            mCoinWalletDetailActivityViewModel.getSplHistorys(wallet,item.getMint(),"");
        }

    }


    public CoinWalletDetailActivityViewModel getViewModel(){
        return mCoinWalletDetailActivityViewModel;
    }

    public class ClickProxy {
       public void close(){
            finish();
        }

        public void startSendIntent(){
            IntentUtils.openIntent(CoinWalletDetailActivity.this,new Intent(CoinWalletDetailActivity.this,SendCoinActivity.class).putExtra("token",item).putExtra("sol",sol));
        }

        public void start2ReceiverDialog(){
            new XPopup.Builder(CoinWalletDetailActivity.this)
                    .dismissOnTouchOutside(true)
                    .dismissOnBackPressed(true)
                    .asCustom(new ReceiverCoinDialog(CoinWalletDetailActivity.this,item,wallet))
            .show();

        }

    }
}
