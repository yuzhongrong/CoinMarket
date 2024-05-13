package com.zksg.kudoud.adapters;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.kunminx.architecture.ui.adapter.SimpleDataBindingAdapter;
import com.zksg.kudoud.R;
import com.zksg.kudoud.activitys.SendCoinActivity;
import com.zksg.kudoud.databinding.ItemSendcoinNumberBinding;
import com.zksg.kudoud.state.SendCoinActivityViewmodel;
import com.zksg.kudoud.utils.DiffUtils;

import java.math.BigDecimal;

public class SendCoinNumberdapter extends SimpleDataBindingAdapter<String, ItemSendcoinNumberBinding> {


    private Context mContex;
    private SendCoinActivityViewmodel mSendCoinActivityViewmodel;

    public SendCoinNumberdapter(Context context) {
        super(context, R.layout.item_sendcoin_number, DiffUtils.getInstance().getSendCoinNumberCallback());
        this.mContex=context;
        mSendCoinActivityViewmodel=((SendCoinActivity)mContex).getMSendCoinActivityViewmodel();
        setOnItemClickListener((item, position) -> {
            String postvalue=filterNumber(item);
            Log.d("----item-click-filter-->",postvalue);
            mSendCoinActivityViewmodel.numberText.postValue(postvalue);
            vibrate();

        });
    }
    @Override
    protected void onBindItem(ItemSendcoinNumberBinding binding, String item, RecyclerView.ViewHolder holder) {
        binding.setNumber(item);
    }

    // 执行震动效果的方法
    private void vibrate() {
        Vibrator vibrator = (Vibrator)mContex.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            // 检查设备是否支持震动
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // Android 8.0 及以上版本使用新的震动效果API
                vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                // 旧版本的 Android 使用旧的震动效果API
                vibrator.vibrate(50);
            }
        }
    }


    private String filterNumber(String content){
        String value=mSendCoinActivityViewmodel.numberText.getValue();
        String result="0";
        //分2种  第一 . 第二 数字
        if(content.equals("\u232B")){
            if(value.length()==1&&value.equals("0")){
                result= value.substring(0,value.length());
            }else if(value.length()==1&&!value.equals("0")){
                result="0";
            }
            else{
                result= value.substring(0,value.length()-1);
            }

        }
        else if(value.length()==1&&content.equals(".")){
            result=value+content;
        }else if(value.equals("0")&&content.equals("0")){
            result=value;
        }else if(value.equals("0")&&!content.equals("0")){
            result=content;
        }
        else if(value.contains(".")&&content.equals(".")){//不重复.输入
            result=value;
        }else if(value.length()>1&&value.contains(".")){
            result=value+content;
        } else{
            result=value+content;
        }

        return result;
    }

}
