package com.zksg.kudoud.activitys;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.kunminx.architecture.ui.page.BaseActivity;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.lxj.xpopup.XPopup;
import com.zksg.kudoud.BR;
import com.zksg.kudoud.R;
import com.zksg.kudoud.adapters.SimpleFragmentPagerAdapter;
import com.zksg.kudoud.databinding.ActivityKlineDetailBinding;
import com.zksg.kudoud.dialogs.ResetFactoryDialog;
import com.zksg.kudoud.fragments.ChartKLineFragment;
import com.zksg.kudoud.state.AboutViewModel;
import com.zksg.kudoud.state.KlineDetailViewModel;

public class KlineDetailActivity extends BaseActivity {
    KlineDetailViewModel mMKlineDetailViewModel;

    @Override
    protected void initViewModel() {
        mMKlineDetailViewModel=getActivityScopeViewModel(KlineDetailViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_kline_detail, BR.vm,mMKlineDetailViewModel);

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InitData((ActivityKlineDetailBinding)getBinding());
    }

    public class ClickProxy {
        public void showFactoryReset(){
            new XPopup.Builder(KlineDetailActivity.this).asCustom(new ResetFactoryDialog(KlineDetailActivity.this)).show();
        }
    }


    private void InitData(ActivityKlineDetailBinding binding){
        Fragment[] fragments = {ChartKLineFragment.newInstance(1, false), ChartKLineFragment.newInstance(7, false),
                ChartKLineFragment.newInstance(30, false)};
        String[] titles = {"日K", "周K", "月K"};
        SimpleFragmentPagerAdapter adapter=new SimpleFragmentPagerAdapter(getSupportFragmentManager(), fragments, titles);
        mMKlineDetailViewModel.tabAdapter.set(adapter);
        mMKlineDetailViewModel.viewpager.set(binding.viewPager);
        //由于无法拿到k线数据 所以先展示基本数据 等有办法拿到k线数据了 再加上去 更新版本

    }
}
