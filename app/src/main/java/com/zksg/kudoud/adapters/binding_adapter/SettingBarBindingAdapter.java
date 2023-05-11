package com.zksg.kudoud.adapters.binding_adapter;

import androidx.databinding.BindingAdapter;

import com.zksg.kudoud.widgets.SettingBar;

public class SettingBarBindingAdapter {

    @BindingAdapter(value = {"bindSettingBar"},requireAll = false)
    public static void bindSettingBar(SettingBar bar,String left_txt_id) {
        bar.setLeftText(left_txt_id);
    }

    @BindingAdapter(value = {"bindSettingBar_right"},requireAll = false)
    public static void bindSettingBar_Right(SettingBar bar,String right_txt_id) {
        bar.setRightText(right_txt_id);
    }

}
