package com.zksg.kudoud.fragments;

import android.util.Log;

import com.kunminx.architecture.ui.page.BaseFragment;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.zksg.kudoud.BR;
import com.zksg.kudoud.R;
import com.zksg.kudoud.state.HeartRateDayFragmentViewModel;

public class HeartRateDayFragment extends BaseFragment {

    public HeartRateDayFragmentViewModel mHeartRateFragmentViewModel;

    @Override
    protected void initViewModel() {
        mHeartRateFragmentViewModel=getFragmentScopeViewModel(HeartRateDayFragmentViewModel.class);
        Log.e("HeartRateDayFragment","initViewModel:"+mHeartRateFragmentViewModel);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_heart_rate_day, BR.vm,mHeartRateFragmentViewModel);
    }


    @Override
    protected void loadInitData() {

        Log.d("---xxx->loadInitData", "loadInitData: ");


    }











}
