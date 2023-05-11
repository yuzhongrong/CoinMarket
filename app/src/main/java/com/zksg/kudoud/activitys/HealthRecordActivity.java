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
import com.zksg.kudoud.state.HealRecordViewModel;

public class HealthRecordActivity extends BaseActivity {
    HealRecordViewModel mHealRecordViewModel;

    @Override
    protected void initViewModel() {
        mHealRecordViewModel=getActivityScopeViewModel(HealRecordViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_health_record, BR.vm,mHealRecordViewModel)
                .addBindingParam(BR.click,new ClickProxy());
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public class ClickProxy {

    }
}
