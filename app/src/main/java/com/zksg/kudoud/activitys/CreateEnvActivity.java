package com.zksg.kudoud.activitys;

import android.content.Intent;

import com.kunminx.architecture.ui.page.BaseActivity;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopupext.listener.CommonPickerListener;
import com.zksg.kudoud.BR;
import com.zksg.kudoud.R;
import com.zksg.kudoud.dialogs.CommonWheelviewDialog;
import com.zksg.kudoud.dialogs.ControlShakeDialog;
import com.zksg.kudoud.dialogs.EnvNameDialog;
import com.zksg.kudoud.state.CreateEnvViewModel;
import com.zksg.kudoud.state.DeviceSettingViewModel;
import com.zksg.kudoud.state.SharedViewModel;
import com.zksg.lib_api.beans.EnvBean;

import java.util.ArrayList;

public class CreateEnvActivity extends BaseActivity {
    CreateEnvViewModel mCreateEnvViewModel;
    SharedViewModel mSharedViewModel;

    @Override
    protected void initViewModel() {
        mCreateEnvViewModel=getActivityScopeViewModel(CreateEnvViewModel.class);
        mSharedViewModel=getApplicationScopeViewModel(SharedViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_create_environment, BR.vm,mCreateEnvViewModel)
                .addBindingParam(BR.click,new ClickProxy());
    }



    public class ClickProxy {

        public void  startSettingShake() {
            startActivity(new Intent(CreateEnvActivity.this,ShakeSettingctivity.class));
        }

        public void  startSettingMusic() {

            startActivity(new Intent(CreateEnvActivity.this,MusicSettingctivity.class));

        }

        public void  startSettingSmallLight() {

            startActivity(new Intent(CreateEnvActivity.this,SmallLightctivity.class));

        }

        public void  startSettingStarLight() {
            startActivity(new Intent(CreateEnvActivity.this,StarLightctivity.class));
        }
        public void  startSettingEnvLight() {
            startActivity(new Intent(CreateEnvActivity.this,AtmosphereLightctivity.class));

        }

        public void create(){
            new XPopup.Builder(CreateEnvActivity.this)
                    .autoDismiss(true)
                    .asCustom(new EnvNameDialog(CreateEnvActivity.this).setOnSaveListener(new EnvNameDialog.OnSaveListener() {
                        @Override
                        public void onSave(String title) {//click the save
//                            mSharedViewModel.requestToAddOneEnv(new EnvBean(title,0,false, EnvBean.Type.NONE));

                        }
                    }))
                    .show();
        }

        public void selectDevice(){
            startActivity(new Intent(CreateEnvActivity.this,SearchDeviceActivity.class));
        }

        public void selectBaby(){
            startActivity(new Intent(CreateEnvActivity.this,BabyDetailActivity.class));
        }
    }
}
