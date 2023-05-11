package com.zksg.kudoud.activitys;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.kunminx.architecture.ui.page.BaseActivity;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.zksg.kudoud.BR;
import com.zksg.kudoud.R;
import com.zksg.kudoud.state.AtmosphereLightViewModel;
import com.zksg.kudoud.state.StarLightViewModel;

public class AtmosphereLightctivity extends BaseActivity {
    AtmosphereLightViewModel mAtmosphereLightViewModel;

    @Override
    protected void initViewModel() {
        mAtmosphereLightViewModel=getActivityScopeViewModel(AtmosphereLightViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_atmosphere_light, BR.vm,mAtmosphereLightViewModel);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public class ClickProxy {


    }
}
