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
            Log.d("----item-click-->",item);


            //检查输入的数值=余额, 直接=余额



            String oldItem= mSendCoinActivityViewmodel.numberText.getValue();
            if(position==9){//处理输入多余的.问题

                if(oldItem!=null&&oldItem.contains("."))return;
                if(TextUtils.isEmpty(oldItem) ||oldItem.length()==0){//不让输入第一个数字是.的判断
                   return;
                }

            }

            if(position==10){

                if(!TextUtils.isEmpty(oldItem)&&oldItem.length()==1){//去掉多余的0
                    if(item.equals("0")){
                        return;
                    }
                }

            }

            if(position!=11){
                if(oldItem==null){
                    oldItem="0";
                }
                String newItem=oldItem+item;

                mSendCoinActivityViewmodel.numberText.postValue(newItem);
                vibrate();



            }else{
                Log.d("----item-click_remove-->",item);
                if(oldItem==null||oldItem.length()==0)return;

                String newItem=oldItem.substring(0,oldItem.length()-1);
                mSendCoinActivityViewmodel.numberText.postValue(newItem);
            }


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

}
