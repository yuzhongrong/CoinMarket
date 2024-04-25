package com.zksg.kudoud.activitys;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.kunminx.architecture.ui.page.BaseActivity;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.lxj.xpopup.XPopup;
import com.zksg.kudoud.BR;
import com.zksg.kudoud.R;
import com.zksg.kudoud.dialogs.ResetFactoryDialog;
import com.zksg.kudoud.state.AboutViewModel;
import com.zksg.kudoud.state.CreateWalletActivityViewmodel;

public class CreateWalletActivity extends BaseActivity {
    CreateWalletActivityViewmodel mCreateWalletActivityViewmodel;

    @Override
    protected void initViewModel() {
        mCreateWalletActivityViewmodel=getActivityScopeViewModel(CreateWalletActivityViewmodel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_create_wallet, BR.vm,mCreateWalletActivityViewmodel)
                .addBindingParam(BR.click,new ClickProxy());
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public class ClickProxy {
        public void close(){
            CreateWalletActivity.this.finish();
        }
    }
}
