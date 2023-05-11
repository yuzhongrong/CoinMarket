package com.zksg.kudoud.fragments;

import android.util.Log;

import com.kunminx.architecture.ui.page.BaseFragment;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.zksg.kudoud.BR;
import com.zksg.kudoud.R;
import com.zksg.kudoud.state.WeightWeekFragmentViewModel;

public class WeightWeekFragment extends BaseFragment {

    public WeightWeekFragmentViewModel mWeightWeekFragmentViewModel;



    @Override
    protected void initViewModel() {
        mWeightWeekFragmentViewModel=getFragmentScopeViewModel(WeightWeekFragmentViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_weight_datas_week, BR.vm,mWeightWeekFragmentViewModel);
    }

    @Override
    protected void loadInitData() {

        Log.d("---xxx->loadInitData", "loadInitData: ");


    }











}
