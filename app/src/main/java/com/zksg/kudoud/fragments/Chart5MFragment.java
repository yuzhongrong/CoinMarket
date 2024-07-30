package com.zksg.kudoud.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.kunminx.architecture.ui.page.BaseFragment;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.netease.lib_network.entitys.DexScreenTokenInfo1;
import com.zksg.kudoud.BR;
import com.zksg.kudoud.R;
import com.zksg.kudoud.activitys.CoinsDetailActivity;
import com.zksg.kudoud.entitys.InitParamsEntity;
import com.zksg.kudoud.state.Chart5KLineFragmentViewModel;
import com.zksg.kudoud.state.CoinsDetailViewModel;

public class Chart5MFragment extends BaseFragment {
    private int mType;//日K：1；周K：7；月K：30
    private boolean land;//是否横屏
    public Chart5KLineFragmentViewModel mChart5KLineFragmentViewModel;
    private CoinsDetailViewModel shareViewModel=null;


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


    }


    @Override
    protected void loadInitData() {
        mType = getArguments().getInt("type");

        //获取activity的数据
        shareViewModel=((CoinsDetailActivity)requireActivity()).getSharedViewModel();
        //订阅当前流动性池子的信息
        shareViewModel.mPairsDTO.observe(this, pairsDTO -> {
            //把数据下发到xml
            mChart5KLineFragmentViewModel.mPairsDTO.set(pairsDTO);
        });

        mChart5KLineFragmentViewModel.mType.set(mType);

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
