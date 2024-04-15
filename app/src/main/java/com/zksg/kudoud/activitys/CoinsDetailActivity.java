package com.zksg.kudoud.activitys;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kunminx.architecture.ui.page.BaseActivity;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.lxj.xpopup.XPopup;
import com.zksg.kudoud.BR;
import com.zksg.kudoud.R;
import com.zksg.kudoud.adapters.CommonKlineDataPagerAdapter;
import com.zksg.kudoud.adapters.SimpleFragmentPagerAdapter;
import com.zksg.kudoud.beans.Kline24ChangeChannelEnum;
import com.zksg.kudoud.dialogs.ResetFactoryDialog;
import com.zksg.kudoud.fragments.ChartKLineFragment;
import com.zksg.kudoud.state.AboutViewModel;
import com.zksg.kudoud.state.CoinsDetailViewModel;

public class CoinsDetailActivity extends BaseActivity {
    CoinsDetailViewModel mCoinsDetailViewModel;

    @Override
    protected void initViewModel() {
        mCoinsDetailViewModel=getActivityScopeViewModel(CoinsDetailViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_coins_detail, BR.vm,mCoinsDetailViewModel)
                .addBindingParam(BR.click,new ClickProxy());
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InitData();
    }


    private void InitData(){

        CommonKlineDataPagerAdapter adapter=new CommonKlineDataPagerAdapter(getSupportFragmentManager(), new Kline24ChangeChannelEnum[]{Kline24ChangeChannelEnum.K_5M,Kline24ChangeChannelEnum.K_1H,Kline24ChangeChannelEnum.K_6H,Kline24ChangeChannelEnum.K_24H});
        mCoinsDetailViewModel.tabAdapter.set(adapter);


    }

    public class ClickProxy {
        public void showFactoryReset(){
            new XPopup.Builder(CoinsDetailActivity.this).asCustom(new ResetFactoryDialog(CoinsDetailActivity.this)).show();
        }
    }



}
