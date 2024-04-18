package com.zksg.kudoud.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.kunminx.architecture.ui.page.BaseFragment;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.zksg.kudoud.BR;
import com.zksg.kudoud.R;
import com.zksg.kudoud.activitys.CoinsDetailActivity;
import com.zksg.kudoud.state.Chart24HLineFragmentViewModel;
import com.zksg.kudoud.state.Chart5KLineFragmentViewModel;
import com.zksg.kudoud.state.CoinsDetailViewModel;

public class Chart24HFragment extends BaseFragment {
    private int mType;//日K：1；周K：7；月K：30
    private boolean land;//是否横屏
    public Chart24HLineFragmentViewModel mChart24HLineFragmentViewModel;
    private CoinsDetailViewModel shareViewModel=null;


    public static Chart24HFragment newInstance(int type, boolean land){
        Chart24HFragment fragment = new Chart24HFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
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
        mChart24HLineFragmentViewModel.mType.set(mType);

        //获取activity的数据
        shareViewModel=((CoinsDetailActivity)requireActivity()).getSharedViewModel();
        //订阅当前流动性池子的信息
        shareViewModel.mPairsDTO.observe(this, pairsDTO -> {
            //把数据下发到xml
            mChart24HLineFragmentViewModel.mPairsDTO.set(pairsDTO);
        });



    }

    @Override
    protected void initViewModel() {
        mChart24HLineFragmentViewModel=getFragmentScopeViewModel(Chart24HLineFragmentViewModel.class);

    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_24hkline, BR.vm,mChart24HLineFragmentViewModel);

    }
}
