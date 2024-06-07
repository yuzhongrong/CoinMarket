package com.zksg.kudoud.adapters.binding_adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.hjq.shape.view.ShapeButton;
import com.netease.lib_network.entitys.DexScreenTokenInfo;
import com.zksg.kudoud.R;
import com.zksg.kudoud.entitys.Base2QuoEntity;
import com.zksg.kudoud.utils.DateUtils;
import com.zksg.kudoud.utils.DigitUtils;
import com.zksg.kudoud.widgets.SettingBar;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class ViewVisibleBindingAdapter {

//    @BindingAdapter(value = {"meme_layout_show"},requireAll = false)
//    public static void meme_layout_show(SkeletonLayout view, boolean value) {
//           if(view==null)return;
//           if(!value){
//               view.showSkeleton();
//           }else{
//               view.showOriginal();
//           }
//    }

        @BindingAdapter(value = {"meme_layout_show"},requireAll = false)
    public static void meme_layout_show(View view, boolean value) {
           if(view==null)return;
           if(value){//处理有钱包情况
               if(view.getId()==R.id.wallet_no){
                   view.setVisibility(View.GONE);
               }else{
                   view.setVisibility(View.VISIBLE);
               }

           }else{//处理没有钱包情况
               if(view.getId()==R.id.wallet){
                   view.setVisibility(View.GONE);
               }else if(view.getId()==R.id.wallet_no){
                   view.setVisibility(View.VISIBLE);
               }

           }
    }


    @BindingAdapter(value = {"img_eye_show_hide"},requireAll = false)
    public static void img_eye_show_hide(ImageView view, boolean value) {
            if(value){
                view.setImageResource(R.mipmap.ic_eye_show);
            }else{
                view.setImageResource(R.mipmap.ic_eye_hide);
            }
    }

    @BindingAdapter(value = {"common_show_hide"},requireAll = false)
    public static void common_show_hide(View view, boolean value) {
        if(value){
            view.setVisibility(View.VISIBLE);
        }else{
            view.setVisibility(View.GONE);
        }
    }





}
