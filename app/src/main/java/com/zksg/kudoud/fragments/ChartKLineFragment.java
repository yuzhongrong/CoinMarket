package com.zksg.kudoud.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.kunminx.architecture.ui.page.BaseFragment;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.zksg.kudoud.BR;
import com.zksg.kudoud.R;
import com.zksg.kudoud.entitys.InitParamsEntity;
import com.zksg.kudoud.state.ChartKLineFragmentViewModel;
import com.zksg.kudoud.state.HeartRateWeekFragmentViewModel;

public class ChartKLineFragment extends BaseFragment {
    private int mType;//日K：1；周K：7；月K：30
    private boolean land;//是否横屏
    public ChartKLineFragmentViewModel mChartKLineFragmentViewModel;


    public static ChartKLineFragment newInstance(int type,boolean land){
        ChartKLineFragment fragment = new ChartKLineFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putBoolean("landscape",land);
        fragment.setArguments(bundle);
        return fragment;

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getInt("type");
        land = getArguments().getBoolean("landscape");
    }

    @Override
    protected void initViewModel() {
        mChartKLineFragmentViewModel=getFragmentScopeViewModel(ChartKLineFragmentViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_kline, BR.vm,mChartKLineFragmentViewModel)
                .addBindingParam(BR.iEntity,new InitParamsEntity(mType,land));
    }
}
