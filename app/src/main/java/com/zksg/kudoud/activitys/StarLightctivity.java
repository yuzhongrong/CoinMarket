package com.zksg.kudoud.activitys;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.kunminx.architecture.ui.page.BaseActivity;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.zksg.kudoud.BR;
import com.zksg.kudoud.R;
import com.zksg.kudoud.state.SmallLightViewModel;
import com.zksg.kudoud.state.StarLightViewModel;

public class StarLightctivity extends BaseActivity {
    StarLightViewModel mStarLightViewModel;

    @Override
    protected void initViewModel() {
        mStarLightViewModel=getActivityScopeViewModel(StarLightViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_start_light, BR.vm,mStarLightViewModel);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public class ClickProxy {


    }
}
