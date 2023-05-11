package com.zksg.kudoud;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import com.kunminx.architecture.ui.page.BaseActivity;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.zksg.kudoud.adapters.FeedTipAdapter;
import com.zksg.kudoud.state.CreateFeedViewModel;

import java.util.Arrays;
import java.util.List;

public class CreateFeedTimerctivity extends BaseActivity {
    CreateFeedViewModel mCreateFeedViewModel;

    @Override
    protected void initViewModel() {
        mCreateFeedViewModel=getActivityScopeViewModel(CreateFeedViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_feed_baby, BR.vm,mCreateFeedViewModel)
                .addBindingParam(BR.click,new ClickProxy())
                .addBindingParam(BR.adapter,new FeedTipAdapter(this));
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public class ClickProxy {


    }
}
