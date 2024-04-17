package com.zksg.kudoud.adapters.binding_adapter;

import android.graphics.Color;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.hjq.shape.view.ShapeButton;
import com.netease.lib_network.entitys.DexScreenTokenInfo;
import com.zksg.kudoud.R;
import com.zksg.kudoud.utils.DateUtils;
import com.zksg.kudoud.utils.DigitUtils;
import com.zksg.kudoud.widgets.SettingBar;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TextViewBindingAdapter {

    @BindingAdapter(value = {"meme_vol_tv"},requireAll = false)
    public static void memeVolTv(TextView tv,Object value) {
           if(value==null)return;
            String flat=tv.getContext().getString(R.string.str_vol_daller);
            tv.setText(flat+DigitUtils.formatAmount((double)value));


    }

    //计算代币发行数
    @BindingAdapter(value = {"meme_suplly_tv"},requireAll = false)
    public static void memesupllyTv(TextView tv, DexScreenTokenInfo.PairsDTO value) {
        if(value==null)return;
        BigDecimal fdv=new BigDecimal(value.getFdv());
        BigDecimal priceusdValue = new BigDecimal(value.getPriceUsd());
        double supply=fdv.divide(priceusdValue,RoundingMode.HALF_UP).doubleValue();
        tv.setText(DigitUtils.formatAmount(supply));
    }

    @BindingAdapter(value = {"meme_suplly_tip_tv"},requireAll = false)
    public static void memesuplly_tipTv(TextView tv,DexScreenTokenInfo.PairsDTO value) {
        if(tv==null)return;
        tv.setText(DigitUtils.formatAmountTip());
    }


    //24小时涨跌幅
    @BindingAdapter(value = {"meme_24hprice_change_tv"},requireAll = false)
    public static void meme24hprice_changeTv(TextView tv,DexScreenTokenInfo.PairsDTO value) {
        if(tv==null||value==null)return;
        double price_24h=value.getPriceChange().getH24();
        if(price_24h>=0.0){
            tv.setTextColor(tv.getContext().getColor(R.color.c_1bc89e));
            tv.setText("+"+price_24h+tv.getContext().getString(R.string.str_precent));
        }else{
            tv.setTextColor(tv.getContext().getColor(R.color.c_f71816));
            tv.setText(price_24h+tv.getContext().getString(R.string.str_precent));
        }

    }


    //设置quo 单位
    @BindingAdapter(value = {"meme_base_tag_tv"},requireAll = false)
    public static void memebaseTv(TextView tv,DexScreenTokenInfo.PairsDTO value) {
        if(tv==null||value==null)return;
        tv.setText("("+value.getBaseToken().getSymbol()+")");

    }

    //设置quo 单位
    @BindingAdapter(value = {"meme_quo_tag_tv"},requireAll = false)
    public static void memequoTv(TextView tv,DexScreenTokenInfo.PairsDTO value) {
        if(tv==null||value==null)return;
        tv.setText("("+value.getQuoteToken().getSymbol()+")");

    }


    //计算mc
    @BindingAdapter(value = {"meme_mc_tv"},requireAll = false)
    public static void mememcTv(TextView tv, DexScreenTokenInfo.PairsDTO value) {
        if(value==null)return;
        double fdv=new BigDecimal(value.getFdv()).doubleValue();
        String dollar=tv.getContext().getString(R.string.str_daller);
        tv.setText(dollar+DigitUtils.formatAmount(fdv));
    }


    //计算流动性
    @BindingAdapter(value = {"meme_liq_tv"},requireAll = false)
    public static void memeliqTv(TextView tv, DexScreenTokenInfo.PairsDTO value) {
        if(value==null)return;
        String dollar=tv.getContext().getString(R.string.str_daller);
        tv.setText(dollar+DigitUtils.formatAmount(value.getLiquidity().getUsd()));
    }

    @BindingAdapter(value = {"meme_create_tv"},requireAll = false)
    public static void timestampToDateString(TextView tv, DexScreenTokenInfo.PairsDTO value) {
        if(value==null)return;
        String dollar=tv.getContext().getString(R.string.str_daller);
        String result=DateUtils.timestampToDateString(value.getPairCreatedAt());
        tv.setText(result);
    }






    //计算筹码集中度
    @BindingAdapter(value = {"meme_collect_tv"},requireAll = false)
    public static void memecollectTv(ProgressBar tv, DexScreenTokenInfo.PairsDTO value) {
        if(value==null)return;
        BigDecimal fdv=new BigDecimal(value.getFdv());
        BigDecimal priceusdValue=new BigDecimal(value.getPriceUsd());
        BigDecimal result= fdv.divide(priceusdValue,BigDecimal.ROUND_HALF_UP);
        BigDecimal liqnumber=new BigDecimal(value.getLiquidity().getBase());
        BigDecimal collect =liqnumber.divide(result, 2, RoundingMode.HALF_UP);
        int ok=collect.multiply(new BigDecimal(100)).intValue();
        tv.setProgress(ok);
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
