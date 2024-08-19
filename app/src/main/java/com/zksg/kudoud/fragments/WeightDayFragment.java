package com.zksg.kudoud.fragments;

import android.util.Log;

import com.kunminx.architecture.ui.page.BaseFragment;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.zksg.kudoud.BR;
import com.zksg.kudoud.R;
import com.zksg.kudoud.state.WeightDayFragmentViewModel;

public class WeightDayFragment extends BaseFragment {

    public WeightDayFragmentViewModel mWeightDayFragmentViewModel;



    @Override
    protected void initViewModel() {
        mWeightDayFragmentViewModel=getFragmentScopeViewModel(WeightDayFragmentViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_weight_datas_day, BR.vm,mWeightDayFragmentViewModel);
    }

    @Override
    protected void loadInitData() {

        Log.d("---xxx.js->loadInitData", "loadInitData: ");


    }











}
