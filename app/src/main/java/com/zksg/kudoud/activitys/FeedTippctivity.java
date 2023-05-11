package com.zksg.kudoud.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import com.kunminx.architecture.ui.page.BaseActivity;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.zksg.kudoud.BR;
import com.zksg.kudoud.R;
import com.zksg.kudoud.adapters.FeedTipAdapter;
import com.zksg.kudoud.state.FeedTipViewModel;
import com.zksg.lib_api.baby.FeedTip;
import java.util.Arrays;
import java.util.List;

public class FeedTippctivity extends BaseActivity {
    FeedTipViewModel mFeedTipViewModel;

    @Override
    protected void initViewModel() {
        mFeedTipViewModel=getActivityScopeViewModel(FeedTipViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_feed_baby, BR.vm,mFeedTipViewModel)
                .addBindingParam(BR.click,new ClickProxy())
                .addBindingParam(BR.adapter,new FeedTipAdapter(this));
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<FeedTip> datas= Arrays.asList(
                new FeedTip("1","8:00",1),
                new FeedTip("2","8:00",2),
                new FeedTip("3","8:00",4),
                new FeedTip("4","8:00",3),
                new FeedTip("5","8:00",1));
        new Handler().postDelayed(()->{
            mFeedTipViewModel.datas.set(datas);
        },280);

    }

    public class ClickProxy {
        public void startAddTimer(){
            startActivity(new Intent(FeedTippctivity.this,NewFeedActivity.class));
        }

    }
}
