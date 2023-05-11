package com.zksg.kudoud.activitys;

import android.view.View;
import android.widget.Toast;

import com.kunminx.architecture.ui.page.BaseActivity;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopupext.listener.CommonPickerListener;
import com.lxj.xpopupext.listener.TimePickerListener;
import com.lxj.xpopupext.popup.TimePickerPopup;
import com.zksg.kudoud.BR;
import com.zksg.kudoud.R;
import com.zksg.kudoud.dialogs.CommonWheelviewDialog;
import com.zksg.kudoud.state.BabyDetailViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BabyDetailActivity extends BaseActivity {
    private BabyDetailViewModel mBabyDetailViewModel;
    @Override
    protected void initViewModel() {
        mBabyDetailViewModel=getActivityScopeViewModel(BabyDetailViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_baby_detail, BR.vm,mBabyDetailViewModel)
                .addBindingParam(BR.click,new ClickProxy());
    }



    public class ClickProxy{

        public void showname(){


        }
        public void showSex(){


        }
        public void showGender(){
            ArrayList<String> list = new ArrayList();
            list.add(getString(R.string.str_man));
            list.add(getString(R.string.str_woman));
            CommonWheelviewDialog popup = new CommonWheelviewDialog(BabyDetailActivity.this,list);
            popup.setCommonPickerListener(new CommonPickerListener() {
                @Override
                public void onItemSelected(int index, String data) {
                    mBabyDetailViewModel.gender.set(data);
                }

                @Override
                public void onCancel() {

                }
            });
            new XPopup.Builder(BabyDetailActivity.this)
                    .asCustom(popup)
                    .show();
        }

        public void showBirthday(){
            Calendar date = Calendar.getInstance();
            date.set(2000, 5,1);
            Calendar date2 = Calendar.getInstance();
            date2.set(2020, 5,1);
            TimePickerPopup popup = new TimePickerPopup(BabyDetailActivity.this)
                        .setDefaultDate(date2)  //设置默认选中日期
                        .setYearRange(1990, 1999) //设置年份范围
                        .setDateRange(date, date2) //设置日期范围
                    .setTimePickerListener(new TimePickerListener() {
                        @Override
                        public void onTimeChanged(Date date) {
                            //时间改变
                        }
                        @Override
                        public void onTimeConfirm(Date date, View view) {
                            //点击确认时间
//                            Toast.makeText(BabyDetailActivity.this, "选择的时间："+date.toLocaleString(), Toast.LENGTH_SHORT).show();
                              mBabyDetailViewModel.birthday.set(date.toLocaleString());

                        }

                        @Override
                        public void onCancel() {

                        }
                    });

            new XPopup.Builder(BabyDetailActivity.this)
                    .asCustom(popup)
                    .show();
        }


        public void showWeight(){
            ArrayList<String> list = new ArrayList();
            for(int i=0;i<200;i++){
                list.add(i+"");
            }
            CommonWheelviewDialog popup = new CommonWheelviewDialog(BabyDetailActivity.this,list);
            popup.setCommonPickerListener(new CommonPickerListener() {
                @Override
                public void onItemSelected(int index, String data) {
                    mBabyDetailViewModel.gender.set(data);
                }

                @Override
                public void onCancel() {

                }
            });
            new XPopup.Builder(BabyDetailActivity.this)
                    .asCustom(popup)
                    .show();

        }

        public void showHigh(){

            ArrayList<String> list = new ArrayList();
            for(int i=0;i<100;i++){
                list.add(i+"");
            }
            CommonWheelviewDialog popup = new CommonWheelviewDialog(BabyDetailActivity.this,list);
            popup.setCommonPickerListener(new CommonPickerListener() {
                @Override
                public void onItemSelected(int index, String data) {
                    mBabyDetailViewModel.gender.set(data);
                }

                @Override
                public void onCancel() {

                }
            });
            new XPopup.Builder(BabyDetailActivity.this)
                    .asCustom(popup)
                    .show();

        }


    }


}
