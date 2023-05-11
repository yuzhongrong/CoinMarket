package com.zksg.kudoud.activitys;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.kunminx.architecture.ui.page.BaseActivity;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.lxj.xpopup.XPopup;
import com.zksg.kudoud.BR;
import com.zksg.kudoud.R;
import com.zksg.kudoud.dialogs.ChangeEmailDialog;
import com.zksg.kudoud.dialogs.ResetFactoryDialog;
import com.zksg.kudoud.state.AboutViewModel;
import com.zksg.kudoud.state.MoreViewModel;

public class AboutActivity extends BaseActivity {
    AboutViewModel mMAboutViewModel;

    @Override
    protected void initViewModel() {
        mMAboutViewModel=getActivityScopeViewModel(AboutViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_about, BR.vm,mMAboutViewModel)
                .addBindingParam(BR.click,new ClickProxy());
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public class ClickProxy {
        public void showFactoryReset(){
            new XPopup.Builder(AboutActivity.this).asCustom(new ResetFactoryDialog(AboutActivity.this)).show();
        }
    }
}
