package com.zksg.kudoud.activitys;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import com.kunminx.architecture.ui.page.BaseActivity;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.zksg.kudoud.BR;
import com.zksg.kudoud.R;
import com.zksg.kudoud.adapters.CycMusicAdapter;
import com.zksg.kudoud.state.MusicSettingViewModel;
import com.zksg.kudoud.state.SmallLightViewModel;
import com.zksg.lib_api.playlist.BasicMusicInfo;

import java.util.ArrayList;
import java.util.List;

public class SmallLightctivity extends BaseActivity {
    SmallLightViewModel mSmallLightViewModel;

    @Override
    protected void initViewModel() {
        mSmallLightViewModel=getActivityScopeViewModel(SmallLightViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_smalllight, BR.vm,mSmallLightViewModel);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }




    public class ClickProxy {


    }
}
