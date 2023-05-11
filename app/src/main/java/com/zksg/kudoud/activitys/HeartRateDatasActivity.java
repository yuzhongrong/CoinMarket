package com.zksg.kudoud.activitys;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.kunminx.architecture.ui.page.BaseActivity;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.zksg.kudoud.BR;
import com.zksg.kudoud.R;
import com.zksg.kudoud.adapters.DialogMusicPagerAdapter;
import com.zksg.kudoud.adapters.HeartRatePagerAdapter;
import com.zksg.kudoud.beans.CommonDataEnum;
import com.zksg.kudoud.beans.MusicChannelEnum;
import com.zksg.kudoud.state.HeartRateViewModel;
import com.zksg.kudoud.state.MusicPlaylistActivityViewModel;

public class HeartRateDatasActivity extends BaseActivity {
    private HeartRateViewModel mHeartRateViewModel;
    @Override
    protected void initViewModel() {
        mHeartRateViewModel=getActivityScopeViewModel(HeartRateViewModel.class);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_heart_rate, BR.vm,mHeartRateViewModel);
    }



    private void initData(){
        mHeartRateViewModel.indicatorTitle.set(new String[]{getString(R.string.str_day),getString(R.string.str_week),getString(R.string.str_month),getString(R.string.str_year)});
        mHeartRateViewModel.adapter.set(new HeartRatePagerAdapter(getSupportFragmentManager(),new CommonDataEnum[]{  CommonDataEnum.HEARTRATEDAY, CommonDataEnum.HEARTRATEWEEK, CommonDataEnum.HEARTRATEMONTH,CommonDataEnum.HEARTRATEYEAR}));
    }
}
