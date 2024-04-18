package com.zksg.kudoud.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.kunminx.architecture.ui.page.BaseFragment;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.zksg.kudoud.BR;
import com.zksg.kudoud.R;
import com.zksg.kudoud.activitys.CoinsDetailActivity;
import com.zksg.kudoud.state.Chart5KLineFragmentViewModel;
import com.zksg.kudoud.state.Chart6HLineFragmentViewModel;
import com.zksg.kudoud.state.CoinsDetailViewModel;

public class Chart6HFragment extends BaseFragment {
    private int mType;//日K：1；周K：7；月K：30
    private boolean land;//是否横屏
    public Chart6HLineFragmentViewModel mChart6HLineFragmentViewModel;
    private CoinsDetailViewModel shareViewModel=null;


    public static Chart6HFragment newInstance(int type, boolean land){
        Chart6HFragment fragment = new Chart6HFragment();
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
            mChart6HLineFragmentViewModel.mPairsDTO.set(pairsDTO);
        });

        mChart6HLineFragmentViewModel.mType.set(mType);

    }

    @Override
    protected void initViewModel() {
        mChart6HLineFragmentViewModel=getFragmentScopeViewModel(Chart6HLineFragmentViewModel.class);

    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_6hkline, BR.vm,mChart6HLineFragmentViewModel);

    }
}
