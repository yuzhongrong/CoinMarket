package com.zksg.kudoud.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.kunminx.architecture.ui.page.BaseActivity;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopupext.listener.CommonPickerListener;
import com.lxj.xpopupext.popup.CommonPickerPopup;
import com.zksg.kudoud.BR;
import com.zksg.kudoud.R;
import com.zksg.kudoud.adapters.BabyAdapter;
import com.zksg.kudoud.dialogs.CommonWheelviewDialog;
import com.zksg.kudoud.state.DeviceSettingViewModel;
import com.zksg.kudoud.widgets.ColorWheelView;
import com.zksg.lib_api.baby.BabyInfo;

import java.util.ArrayList;
import java.util.Arrays;

public class DeviceSettingActivity extends BaseActivity {
    DeviceSettingViewModel mDeviceSettingViewModel;

    @Override
    protected void initViewModel() {
        mDeviceSettingViewModel=getActivityScopeViewModel(DeviceSettingViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_device_setting, BR.vm,mDeviceSettingViewModel)
                .addBindingParam(BR.click,new ClickProxy())
                .addBindingParam(BR.adapter,new BabyAdapter(this));
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(()->{
            mDeviceSettingViewModel.datas.set(Arrays.asList(
                    new BabyInfo("1","测试","5","242342421423","8","50",""),
                    new BabyInfo("2","测试1","5","242342421423","8","50",""),
                    new BabyInfo("3","测试2","5","242342421423","8","50","")));

        },280);

    }

    public class ClickProxy {

        public void  showLanguageDialog() {
            ArrayList<String> list = new ArrayList();
            list.add(getString(R.string.str_chinese));
            list.add(getString(R.string.str_eng));
            CommonWheelviewDialog popup = new CommonWheelviewDialog(DeviceSettingActivity.this,list);
            popup.setCommonPickerListener(new CommonPickerListener() {
                @Override
                public void onItemSelected(int index, String data) {
                    mDeviceSettingViewModel.deviceLanguage.set(data);
                }

                @Override
                public void onCancel() {

                }
            });
            new XPopup.Builder(DeviceSettingActivity.this)
                    .asCustom(popup)
                    .show();
        }



       public void  showBabyDetail(){
           startActivity(new Intent(DeviceSettingActivity.this,BabyDetailActivity.class));
       }

    }
}
