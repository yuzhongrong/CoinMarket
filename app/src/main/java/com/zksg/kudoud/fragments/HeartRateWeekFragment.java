package com.zksg.kudoud.fragments;

import android.util.Log;

import com.kunminx.architecture.ui.page.BaseFragment;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.zksg.kudoud.BR;
import com.zksg.kudoud.R;
import com.zksg.kudoud.state.HeartRateDayFragmentViewModel;
import com.zksg.kudoud.state.HeartRateWeekFragmentViewModel;

public class HeartRateWeekFragment extends BaseFragment {

    /**TODO:Tip 4个HeartRatefragment day,week month,year 用同一个HeartRateFragmentViewModel和同一个布局
     *
     */
    public HeartRateWeekFragmentViewModel mHeartRateWeekFragmentViewModel;

    @Override
    protected void initViewModel() {
        mHeartRateWeekFragmentViewModel=getFragmentScopeViewModel(HeartRateWeekFragmentViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_heart_rate_week, BR.vm,mHeartRateWeekFragmentViewModel);
    }

    @Override
    protected void loadInitData() {

        Log.d("---xxx->loadInitData", "loadInitData: ");


    }











}
