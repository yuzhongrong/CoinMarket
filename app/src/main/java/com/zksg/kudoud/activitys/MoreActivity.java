package com.zksg.kudoud.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.kunminx.architecture.ui.page.BaseActivity;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopupext.listener.CommonPickerListener;
import com.lxj.xpopupext.listener.TimePickerListener;
import com.zksg.kudoud.BR;
import com.zksg.kudoud.R;
import com.zksg.kudoud.state.CreateFeedViewModel;
import com.zksg.kudoud.state.MoreViewModel;
import com.zksg.kudoud.widgets.CommonPickerPopup;
import com.zksg.kudoud.widgets.TimePickerPopup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MoreActivity extends BaseActivity {
    MoreViewModel mMoreViewModel;
    @Override
    protected void initViewModel() {
        mMoreViewModel=getActivityScopeViewModel(MoreViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_more, BR.vm,mMoreViewModel)
                .addBindingParam(BR.click,new ClickProxy());
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public class ClickProxy {

        public void start2AccountSecurity(){
            startActivity(new Intent(MoreActivity.this,AccountSecurityActivity.class));
        }
        public void start2About(){
            startActivity(new Intent(MoreActivity.this,AboutActivity.class));
        }
    }
}
