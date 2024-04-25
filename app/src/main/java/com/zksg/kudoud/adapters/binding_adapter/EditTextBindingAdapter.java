package com.zksg.kudoud.adapters.binding_adapter;

import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.hjq.shape.view.ShapeButton;
import com.netease.lib_network.entitys.DexScreenTokenInfo;
import com.zksg.kudoud.R;
import com.zksg.kudoud.entitys.Base2QuoEntity;
import com.zksg.kudoud.utils.DateUtils;
import com.zksg.kudoud.utils.DigitUtils;
import com.zksg.kudoud.widgets.CircularProgressBar;
import com.zksg.kudoud.widgets.SettingBar;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class EditTextBindingAdapter {

    @BindingAdapter(value = {"edit_pwd_handle"},requireAll = false)
    public static void edit_pwd_handle(EditText editText, boolean value) {
           if(editText==null)return;
           if(value){
               editText.setTransformationMethod(null);
           }else{
               editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
           }

    }



  



}
