package com.zksg.kudoud.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.kunminx.architecture.ui.page.BaseFragment;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.zksg.kudoud.BR;
import com.zksg.kudoud.R;
import com.zksg.kudoud.entitys.InitParamsEntity;
import com.zksg.kudoud.state.Chart5KLineFragmentViewModel;

public class Chart5MFragment extends BaseFragment {
    private int mType;//日K：1；周K：7；月K：30
    private boolean land;//是否横屏
    public Chart5KLineFragmentViewModel mChart5KLineFragmentViewModel;


    public static Chart5MFragment newInstance(int type, boolean land){
        Chart5MFragment fragment = new Chart5MFragment();
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
        mChart5KLineFragmentViewModel=getFragmentScopeViewModel(Chart5KLineFragmentViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_5mkline, BR.vm,mChart5KLineFragmentViewModel);

    }
}
