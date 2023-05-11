package com.zksg.kudoud.activitys;

import com.kunminx.architecture.ui.page.BaseActivity;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.zksg.kudoud.BR;
import com.zksg.kudoud.R;
import com.zksg.kudoud.state.CreateEnvViewModel;
import com.zksg.kudoud.state.ShakeSettingViewModel;

public class ShakeSettingctivity extends BaseActivity {
    ShakeSettingViewModel mShakeSettingViewModel;

    @Override
    protected void initViewModel() {
        mShakeSettingViewModel=getActivityScopeViewModel(ShakeSettingViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_setting_shake, BR.vm,mShakeSettingViewModel)
                .addBindingParam(BR.click,new ClickProxy());
    }



    public class ClickProxy {

        public void  startSettingShake() {

        }

        public void  startSettingMusic() {
        }

        public void  startSettingSmallLight() {
        }

        public void  startSettingStarLight() {
        }
        public void  startSettingEnvLight() {
        }

    }
}
