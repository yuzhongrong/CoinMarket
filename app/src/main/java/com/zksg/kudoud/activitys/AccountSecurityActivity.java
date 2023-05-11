package com.zksg.kudoud.activitys;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.kunminx.architecture.ui.page.BaseActivity;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.lxj.xpopup.XPopup;
import com.zksg.kudoud.BR;
import com.zksg.kudoud.R;
import com.zksg.kudoud.dialogs.ChangeEmailDialog;
import com.zksg.kudoud.dialogs.ChangePwdDialog;
import com.zksg.kudoud.dialogs.DelBabyDialog;
import com.zksg.kudoud.state.AccountSecurityViewModel;
import com.zksg.kudoud.state.MoreViewModel;

public class AccountSecurityActivity extends BaseActivity {
    AccountSecurityViewModel mMAccountSecurityViewModel;

    @Override
    protected void initViewModel() {
        mMAccountSecurityViewModel=getActivityScopeViewModel(AccountSecurityViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_secuirty, BR.vm,mMAccountSecurityViewModel)
                .addBindingParam(BR.click,new ClickProxy());
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public class ClickProxy {
        public void showChangePwd(){
            new XPopup.Builder(AccountSecurityActivity.this).asCustom(new ChangePwdDialog(AccountSecurityActivity.this)).show();
        }

        public void showChangeEmail(){
            new XPopup.Builder(AccountSecurityActivity.this).asCustom(new ChangeEmailDialog(AccountSecurityActivity.this)).show();
        }
    }
}
