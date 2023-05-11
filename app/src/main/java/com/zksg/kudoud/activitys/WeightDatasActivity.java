package com.zksg.kudoud.activitys;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.kunminx.architecture.ui.page.BaseActivity;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.zksg.kudoud.BR;
import com.zksg.kudoud.R;
import com.zksg.kudoud.adapters.HeartRatePagerAdapter;
import com.zksg.kudoud.adapters.WeightPagerAdapter;
import com.zksg.kudoud.beans.CommonDataEnum;
import com.zksg.kudoud.state.HeartRateViewModel;
import com.zksg.kudoud.state.WeightViewModel;

public class WeightDatasActivity extends BaseActivity {
    private WeightViewModel mWeightViewModel;
    @Override
    protected void initViewModel() {
        mWeightViewModel=getActivityScopeViewModel(WeightViewModel.class);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_weight, BR.vm,mWeightViewModel);
    }


    private void initData(){
        mWeightViewModel.indicatorTitle.set(new String[]{getString(R.string.str_day),getString(R.string.str_week),getString(R.string.str_month),getString(R.string.str_year)});
        mWeightViewModel.adapter.set(new WeightPagerAdapter(getSupportFragmentManager(),new CommonDataEnum[]{  CommonDataEnum.HEARTRATEDAY, CommonDataEnum.HEARTRATEWEEK, CommonDataEnum.HEARTRATEMONTH,CommonDataEnum.HEARTRATEYEAR}));
    }
}
