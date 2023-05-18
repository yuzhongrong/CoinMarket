package com.zksg.kudoud.activitys;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.kunminx.architecture.ui.page.BaseActivity;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.zksg.kudoud.BR;
import com.zksg.kudoud.R;
import com.zksg.kudoud.adapters.HeartRatePagerAdapter;
import com.zksg.kudoud.beans.CommonDataEnum;
import com.zksg.kudoud.state.AppDetailActivityViewModel;
import com.zksg.kudoud.state.HeartRateViewModel;

public class AppDetailActivity extends BaseActivity {
    private AppDetailActivityViewModel mAppDetailActivityViewModel;
    @Override
    protected void initViewModel() {
        mAppDetailActivityViewModel=getActivityScopeViewModel(AppDetailActivityViewModel.class);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_detail, BR.vm,mAppDetailActivityViewModel);
    }



    private void initData(){


    }
}
