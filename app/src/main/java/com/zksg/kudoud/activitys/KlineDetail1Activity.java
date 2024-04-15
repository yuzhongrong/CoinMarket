package com.zksg.kudoud.activitys;

import android.os.Bundle;

import androidx.annotation.Nullable;
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
import com.zksg.kudoud.state.KlineDetailViewModel;

public class KlineDetail1Activity extends BaseActivity {
    KlineDetailViewModel mMKlineDetailViewModel;

    @Override
    protected void initViewModel() {
        mMKlineDetailViewModel=getActivityScopeViewModel(KlineDetailViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_dashboard_detail, BR.vm,mMKlineDetailViewModel);

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public class ClickProxy {
        public void showFactoryReset(){
            new XPopup.Builder(KlineDetail1Activity.this).asCustom(new ResetFactoryDialog(KlineDetail1Activity.this)).show();
        }
    }


    private void InitData(ActivityKlineDetailBinding binding){

        //由于无法拿到k线数据 所以先展示基本数据 等有办法拿到k线数据了 再加上去 更新版本

    }
}
