package com.zksg.kudoud.activitys;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.kunminx.architecture.ui.page.BaseActivity;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.lxj.xpopup.XPopup;
import com.zksg.kudoud.BR;
import com.zksg.kudoud.R;
import com.zksg.kudoud.adapters.ExSwapListdapter;
import com.zksg.kudoud.dialogs.ResetFactoryDialog;
import com.zksg.kudoud.state.AboutViewModel;
import com.zksg.kudoud.state.CoinWalletDetailActivityViewModel;

public class CoinWalletDetailActivity extends BaseActivity {
    CoinWalletDetailActivityViewModel mCoinWalletDetailActivityViewModel;

    @Override
    protected void initViewModel() {
        mCoinWalletDetailActivityViewModel=getActivityScopeViewModel(CoinWalletDetailActivityViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_wallet_coin_detail, BR.vm,mCoinWalletDetailActivityViewModel)
                .addBindingParam(BR.click,new ClickProxy())
                .addBindingParam(BR.adapter,new ExSwapListdapter(this,mCoinWalletDetailActivityViewModel));
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public class ClickProxy {
       public void close(){
            finish();
        }
    }
}
