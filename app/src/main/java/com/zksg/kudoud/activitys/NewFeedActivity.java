package com.zksg.kudoud.activitys;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.contrarywind.view.WheelView;
import com.kunminx.architecture.ui.page.BaseActivity;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopupext.listener.CommonPickerListener;
import com.lxj.xpopupext.listener.TimePickerListener;
import com.zksg.kudoud.BR;
import com.zksg.kudoud.R;
import com.zksg.kudoud.adapters.FeedTipAdapter;
import com.zksg.kudoud.dialogs.CommonWheelviewDialog;
import com.zksg.kudoud.state.CreateFeedViewModel;
import com.zksg.kudoud.widgets.CommonPickerPopup;
import com.zksg.kudoud.widgets.TimePickerPopup;
import com.zksg.lib_api.baby.FeedTip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NewFeedActivity extends BaseActivity {
    CreateFeedViewModel mCreateFeedViewModel;

    @Override
    protected void initViewModel() {
        mCreateFeedViewModel=getActivityScopeViewModel(CreateFeedViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_feed_create, BR.vm,mCreateFeedViewModel)
                .addBindingParam(BR.click,new ClickProxy());
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public class ClickProxy {

        public void showTimerData(){
//            Calendar date = Calendar.getInstance();
//            date.set(2000, 5,1);
//            Calendar date2 = Calendar.getInstance();
//            date2.set(2020, 5,1);
            Calendar date3 = Calendar.getInstance();
            date3.set(2000,2,1,2,0);
            Calendar date4 = Calendar.getInstance();
            date4.set(2022,2,1,2,0);
            TimePickerPopup popup = new TimePickerPopup(NewFeedActivity.this)
//                        .setDefaultDate(date)  //设置默认选中日期
//                        .setYearRange(1990, 1999) //设置年份范围
                        .setDateRange(date3, date4) //设置日期范围
                    .setMode(TimePickerPopup.Mode.HM)
                    .setTimePickerListener(new TimePickerListener() {
                        @Override
                        public void onTimeChanged(Date date) {
                            //时间改变
                        }
                        @Override
                        public void onTimeConfirm(Date date, View view) {
                            //点击确认时间
                            Toast.makeText(NewFeedActivity.this, "选择的时间："+date.toLocaleString(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancel() {

                        }
                    });

            new XPopup.Builder(NewFeedActivity.this)
                    .asCustom(popup)
                    .show();
        }

        public void showRules(){
            ArrayList<String> list = new ArrayList();
            list.add(getString(R.string.str_one));
            list.add(getString(R.string.str_two));
            list.add(getString(R.string.str_three));
            list.add(getString(R.string.str_four));
            list.add(getString(R.string.str_five));

            list.add(getString(R.string.str_only));
            list.add(getString(R.string.str_everyday));
            list.add(getString(R.string.str_one2five));
            CommonWheelviewDialog popup = new CommonWheelviewDialog(NewFeedActivity.this,list);
            popup.setCommonPickerListener(new CommonPickerListener() {
                @Override
                public void onItemSelected(int index, String data) {

                }

                @Override
                public void onCancel() {

                }
            });
            new XPopup.Builder(NewFeedActivity.this)
                    .asCustom(popup)
                    .show();



        }

    }
}
