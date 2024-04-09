package com.zksg.kudoud.adapters.binding_adapter;

import android.graphics.Color;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.hjq.shape.view.ShapeButton;
import com.zksg.kudoud.R;
import com.zksg.kudoud.utils.DigitUtils;
import com.zksg.kudoud.widgets.SettingBar;

public class TextViewBindingAdapter {

    @BindingAdapter(value = {"meme_vol_tv"},requireAll = false)
    public static void memeVolTv(TextView tv,Object value) {
           if(value==null)return;
            String flat=tv.getContext().getString(R.string.str_vol_daller);
            tv.setText(flat+DigitUtils.formatAmount((double)value));


    }

    @BindingAdapter(value = {"meme_percent_tv"},requireAll = false)
    public static void memeVolTv(ShapeButton bt, Object value) {
        if(value==null)return;
        double d_result=(double)value;
        boolean result=DigitUtils.isNegative(d_result);
        String flat=bt.getContext().getString(R.string.str_precent);

        if(result){
            bt.setText(DigitUtils.formatAmountPercentChange(d_result)+flat);
            bt.getShapeDrawableBuilder().setSolidColor(bt.getContext().getColor(R.color.c_f71816)).intoBackground();
        }else{
            bt.getShapeDrawableBuilder().setSolidColor(bt.getContext().getColor(R.color.c_1bc89e)).intoBackground();
            bt.setText("+"+DigitUtils.formatAmountPercentChange(d_result)+flat);
        }


    }

    @BindingAdapter(value = {"bindSettingBar_right"},requireAll = false)
    public static void bindSettingBar_Right(SettingBar bar,String right_txt_id) {
        bar.setRightText(right_txt_id);
    }


    @BindingAdapter(value = {"bindTextViewContent"},requireAll = false)
    public static void bindTextViewContent(TextView tv,String content) {
        if(tv==null)return;
        tv.setText(content);
    }

}
