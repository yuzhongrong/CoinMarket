package com.zksg.kudoud.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.kunminx.architecture.ui.page.BaseFragment;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.zksg.kudoud.BR;
import com.zksg.kudoud.R;
import com.zksg.kudoud.activitys.CoinsDetailActivity;
import com.zksg.kudoud.state.Chart1HLineFragmentViewModel;
import com.zksg.kudoud.state.Chart5KLineFragmentViewModel;
import com.zksg.kudoud.state.CoinsDetailViewModel;

public class Chart1HFragment extends BaseFragment {
    private int mType;//日K：1；周K：7；月K：30
    private boolean land;//是否横屏
    public Chart1HLineFragmentViewModel mChart1HLineFragmentViewModel;
    private CoinsDetailViewModel shareViewModel=null;


    public static Chart1HFragment newInstance(int type, boolean land){
        Chart1HFragment fragment = new Chart1HFragment();
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
        mChart1HLineFragmentViewModel.mType.set(mType);

        //获取activity的数据
        shareViewModel=((CoinsDetailActivity)requireActivity()).getSharedViewModel();
        //订阅当前流动性池子的信息
        shareViewModel.mPairsDTO.observe(this, pairsDTO -> {
            //把数据下发到xml
            mChart1HLineFragmentViewModel.mPairsDTO.set(pairsDTO);
        });



    }

    @Override
    protected void initViewModel() {
        mChart1HLineFragmentViewModel=getFragmentScopeViewModel(Chart1HLineFragmentViewModel.class);

    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_1hkline, BR.vm,mChart1HLineFragmentViewModel);

    }
}
