package com.zksg.kudoud.adapters.binding_adapter;

import static com.zksg.kudoud.utils.GsonUtil.parseJsonArray;
import static com.zksg.kudoud.wallet.constants.Constants.TOKEN_SOL_CONTRACT;
import static com.zksg.kudoud.wallet.constants.Constants.TOKEN_USDC_CONTRACT;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.hjq.shape.view.ShapeButton;
import com.hjq.shape.view.ShapeImageView;
import com.hjq.shape.view.ShapeTextView;
import com.kunminx.architecture.domain.message.MutableResult;

import com.netease.lib_network.entitys.CheckToken;
import com.netease.lib_network.entitys.CommonCategory;
import com.netease.lib_network.entitys.DexScreenTokenInfo1;
import com.netease.lib_network.entitys.DexScreenTokenInfo1;
import com.netease.lib_network.entitys.NewWalletToken;
import com.netease.lib_network.entitys.TransationHistoryEntity;
import com.zksg.kudoud.R;
import com.zksg.kudoud.activitys.CoinsDetailActivity;
import com.zksg.kudoud.adapters.TransDetailHistorysrdapter;
import com.zksg.kudoud.adapters.TransHistorysrdapter;
import com.zksg.kudoud.beans.Kline24ChangeChannelEnum;
import com.zksg.kudoud.entitys.Base2QuoEntity;
import com.zksg.kudoud.entitys.UiWalletToken;
import com.zksg.kudoud.fragments.Chart5MFragment;
import com.zksg.kudoud.state.Chart1HLineFragmentViewModel;
import com.zksg.kudoud.state.Chart5KLineFragmentViewModel;
import com.zksg.kudoud.state.Chart6HLineFragmentViewModel;
import com.zksg.kudoud.state.MeFragmentViewModel;
import com.zksg.kudoud.utils.DateUtils;
import com.zksg.kudoud.utils.DigitUtils;
import com.zksg.kudoud.utils.IntentUtils;
import com.zksg.kudoud.utils.StringUtils;
import com.zksg.kudoud.utils.TimeUtils;
import com.zksg.kudoud.widgets.CircularProgressBar;
import com.zksg.kudoud.widgets.SettingBar;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class TextViewBindingAdapter {

    @BindingAdapter(value = {"meme_vol_tv"},requireAll = false)
    public static void memeVolTv(TextView tv,Object value) {
           if(value==null)return;
            String flat=tv.getContext().getString(R.string.str_vol_daller);
            tv.setText(flat+DigitUtils.formatAmount((double)value));
    }

    @BindingAdapter(value = {"loadtextfromhtml"},requireAll = false)
    public static void loadtextfromhtml(TextView tv,String link) {
        if(TextUtils.isEmpty(link))return;
        tv.setText(Html.fromHtml(tv.getContext().getString(R.string.str_website)));
        tv.setOnClickListener(v->{ IntentUtils.openWebsite(tv.getContext(),link);});
    }


    @BindingAdapter(value = {"meme_suplly_tv"},requireAll = false)
    public static void memesupllyTv(TextView tv, DexScreenTokenInfo1.PairsDTO value) {
        if(value==null)return;
        BigDecimal fdv=new BigDecimal(value.getFdv());
        BigDecimal priceusdValue = new BigDecimal(value.getPriceUsd());
        double supply=fdv.divide(priceusdValue,RoundingMode.HALF_UP).doubleValue();
        tv.setText(DigitUtils.formatNumber(supply));
    }

    @BindingAdapter(value = {"meme_suplly_tip_tv"},requireAll = false)
    public static void memesuplly_tipTv(TextView tv,DexScreenTokenInfo1.PairsDTO value) {
        if(tv==null)return;
        tv.setText(DigitUtils.formatAmountTip());
    }


    //24小时涨跌幅
    @BindingAdapter(value = {"meme_24hprice_change_tv"},requireAll = false)
    public static void meme24hprice_changeTv(TextView tv,DexScreenTokenInfo1.PairsDTO value) {
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

    @BindingAdapter(value = {"memeprice_common_changeTv"},requireAll = false)
    public static void memeprice_common_changeTv(TextView tv,double value) {
        if(tv==null)return;
        if(value>=0.0){
            tv.setTextColor(tv.getContext().getColor(R.color.c_1bc89e));
            tv.setText("+"+value+tv.getContext().getString(R.string.str_precent));
        }else{
            tv.setTextColor(tv.getContext().getColor(R.color.c_f71816));
            tv.setText(value+tv.getContext().getString(R.string.str_precent));
        }

    }


    //设置quo 单位
    @BindingAdapter(value = {"meme_base_tag_tv"},requireAll = false)
    public static void memebaseTv(TextView tv,DexScreenTokenInfo1.PairsDTO value) {
        if(tv==null||value==null)return;
        tv.setText("("+value.getBaseToken().getSymbol()+")");

    }

    //设置quo 单位
    @BindingAdapter(value = {"meme_quo_tag_tv"},requireAll = false)
    public static void memequoTv(TextView tv,DexScreenTokenInfo1.PairsDTO value) {
        if(tv==null||value==null)return;
        tv.setText("("+value.getQuoteToken().getSymbol()+")");

    }

    //设置quo value
    @BindingAdapter(value = {"meme_quo_value_tv"},requireAll = false)
    public static void memequovalueTv(TextView tv,DexScreenTokenInfo1.PairsDTO value) {
        if(tv==null||value==null)return;
        DexScreenTokenInfo1.PairsDTO.LiquidityDTO mliq=value.getLiquidity();
        if(mliq==null)return;
        BigDecimal que=new BigDecimal(value.getLiquidity().getQuote());
        tv.setText(DigitUtils.formatAmount(que.doubleValue()));

    }

    //设置base value
    @BindingAdapter(value = {"meme_base_value_tv"},requireAll = false)
    public static void memebasevalueTv(TextView tv,DexScreenTokenInfo1.PairsDTO value) {
        if(tv==null||value==null||value.getLiquidity()==null)return;
        BigDecimal base=new BigDecimal(value.getLiquidity().getBase());
        tv.setText(DigitUtils.formatAmount(base.doubleValue()));

    }


    //计算mc
    @BindingAdapter(value = {"meme_mc_tv"},requireAll = false)
    public static void mememcTv(TextView tv,double value) {
        if(tv==null)return;
//        double fdv=new BigDecimal(value).doubleValue();
        String dollar=tv.getContext().getString(R.string.str_daller);
        tv.setText(dollar+DigitUtils.formatAmount(value));
    }


    @BindingAdapter(value = {"wallet_token_mc_tv"},requireAll = false)
    public static void wallet_token_mc_tv(TextView tv,String value) {
        if(tv==null)return;
        double wallet_token_mc=new BigDecimal(value).longValue();
        String dollar=tv.getContext().getString(R.string.str_daller);
        tv.setText(dollar+DigitUtils.formatPriceAmount(wallet_token_mc));
    }

    //计算币的价格
    @BindingAdapter(value = {"meme_price_tv"},requireAll = false)
    public static void memepriceTv(TextView tv,String value) {
        if(tv==null)return;
        double price=new BigDecimal(value).doubleValue();
        String dollar=tv.getContext().getString(R.string.str_daller);
        tv.setText(dollar+DigitUtils.formatPriceAmount(price));
    }


    @BindingAdapter(value = {"meme_price_normal_tv"},requireAll = false)
    public static void meme_price_normal_tv(TextView tv,String value) {
        if(tv==null||TextUtils.isEmpty(value))return;
        double price=new BigDecimal(value).doubleValue();
        String dollar=tv.getContext().getString(R.string.str_daller);
        tv.setText(dollar+DigitUtils.formatNumberWithCommas(price));
    }


    //展示base/quo
    @BindingAdapter(value = {"meme_base2quo_tv"},requireAll = false)
    public static void memebase2quoTv(TextView tv, DexScreenTokenInfo1.PairsDTO.LiquidityDTO value) {
        if(tv==null||value==null)return;
        BigDecimal basenumber=new BigDecimal(value.getBase());
        BigDecimal quonumber=new BigDecimal(value.getQuote());
        String result= DigitUtils.formatAmount(basenumber.doubleValue())+"/"+DigitUtils.formatAmount(quonumber.doubleValue());
        tv.setText(result);
    }

    @BindingAdapter(value = {"meme_base2quo_tv1"},requireAll = false)
    public static void meme_base2quo_tv1(TextView tv, double value) {
        if(tv==null)return;
        BigDecimal vd=new BigDecimal(value);
        String result= DigitUtils.formatAmount(vd.doubleValue());
        tv.setText(result);
    }


    //计算流动性
    @BindingAdapter(value = {"meme_liq_tv"},requireAll = false)
    public static void memeliqTv(TextView tv, DexScreenTokenInfo1.PairsDTO.LiquidityDTO value) {
        if(tv==null||value==null)return;
        String dollar=tv.getContext().getString(R.string.str_daller);
        tv.setText(dollar+DigitUtils.formatAmount(value.getUsd()));
    }

    @BindingAdapter(value = {"meme_wallet_token_price_tv"},requireAll = false)
    public static void meme_wallet_token_price_tv(TextView tv, UiWalletToken token) {
        if(tv==null||token==null)return;
        String dollar=tv.getContext().getString(R.string.str_daller);
        int decimal=6;
//        if(decimal<6){decimal=6;}
        tv.setText(dollar+DigitUtils.formatPriceWithoutScientificNotation(token.getPrice(),decimal));
    }


    @BindingAdapter(value = {"meme_common_price_tv"},requireAll = false)
    public static void meme_common_price_tv(TextView tv, String value) {
        if(tv==null||TextUtils.isEmpty(value))return;
        String dollar=tv.getContext().getString(R.string.str_daller);
        int decimal=6;
//        if(decimal<6){decimal=6;}
        tv.setText(dollar+DigitUtils.formatPriceWithoutScientificNotation(value,decimal));
    }



    @BindingAdapter(value = {"meme_wallet_token_amount_tv"},requireAll = false)
    public static void meme_wallet_token_amount_tv(TextView tv, String amount) {
        if(tv==null|| TextUtils.isEmpty(amount))return;
        tv.setText(DigitUtils.formatNumberWithCommas(new BigDecimal(amount).doubleValue(),6));
    }


    @BindingAdapter(value = {"transation_amount","transation_amount_direct"},requireAll = false)
    public static void meme_trasantion_history_token_amount_decimal_tv(TextView tv, TransationHistoryEntity item,String wallet) {
        if(tv==null||item==null||TextUtils.isEmpty(wallet))return;

        String plusorminus=item.getSender().equals(wallet)?"-":"+";
        String direct=item.getSender().equals(wallet)?tv.getContext().getString(R.string.str_send):tv.getContext().getString(R.string.str_receiver);
        if(tv.getId()==R.id.show_amount){
            if(item.isIsSolTransfer()){
                String result= DigitUtils.formatAmount(new BigDecimal(item.getAmount()).longValue(),item.getDecimals());
                tv.setText(plusorminus+DigitUtils.formatNumberWithCommas(new BigDecimal(result).doubleValue(),item.getDecimals())+" "+item.getSymbol()+"SOL");
            }else{
                tv.setText(plusorminus+item.getAmount()+" "+item.getSymbol());
            }
        }else if(tv.getId()==R.id.direct){

            tv.setText(direct);
        }

    }


    //计算所有池子总流动性
    @BindingAdapter(value = {"meme_all_liq_tv"},requireAll = false)
    public static void memeallliqTv(TextView tv, List<DexScreenTokenInfo1.PairsDTO> lists) {
        if(tv==null|lists==null||lists.size()==0)return;
        String dollar=tv.getContext().getString(R.string.str_daller);
        BigDecimal result=new BigDecimal(0.0);
        for(DexScreenTokenInfo1.PairsDTO item:lists){
            if(item.getLiquidity()==null)break;
            BigDecimal itemliq=new BigDecimal(item.getLiquidity().getUsd());
            result=result.add(itemliq);
        }

        tv.setText(dollar+DigitUtils.formatAmount(result.doubleValue()));
    }


    //计算所有池子总流动性
    @BindingAdapter(value = {"meme_all_mc_tv"},requireAll = false)
    public static void meme_all_mc_tv(TextView tv, List<DexScreenTokenInfo1.PairsDTO> lists) {
        if(tv==null|lists==null||lists.size()==0)return;
        String dollar=tv.getContext().getString(R.string.str_daller);
        BigDecimal result=new BigDecimal(0.0);
        for(DexScreenTokenInfo1.PairsDTO item:lists){
            BigDecimal itemfdv=new BigDecimal(item.getFdv());
            result=result.add(itemfdv);
        }

        tv.setText(dollar+DigitUtils.formatAmount(result.doubleValue()));
    }


    //计算所有池子总的筹码集中度
    @BindingAdapter(value = {"meme_all_collect_tv"},requireAll = false)
    public static void meme_all_collect_tv(ProgressBar pb, Base2QuoEntity datas) {
        if(pb==null|datas==null||datas.getmPairsDTO().size()==0)return;

        String base_origin=datas.getContract();
        BigDecimal base_result=new BigDecimal(0.0);
        for(int i=0;i<datas.getmPairsDTO().size();i++){
            //获取当前池子的base币个数 这里的base 有可能不是nub 代币 例如 sin/nub
            String base_address=datas.getmPairsDTO().get(i).getBaseToken().getAddress();
            //获取当前pool的liq流动性
            DexScreenTokenInfo1.PairsDTO.LiquidityDTO mliq=datas.getmPairsDTO().get(i).getLiquidity();
            if(mliq==null)break;
            if(base_address.equalsIgnoreCase(base_origin)){
               BigDecimal basenumber=new BigDecimal(mliq.getBase());
                base_result=base_result.add(basenumber);
            }else{//quo是你要统计的币的个数
                BigDecimal basenumber=new BigDecimal(mliq.getQuote());
                base_result=base_result.add(basenumber);
            }

        }

        //总的发行base个数  这里的base 有可能不是nub 代币
        BigDecimal supply_result=callculatememesupllyTv(datas.getmPairsDTO().get(0));
        BigDecimal collect =base_result.divide(supply_result, 2, RoundingMode.HALF_UP);
        int ok=collect.multiply(new BigDecimal(100)).intValue();
        pb.setProgress(ok);


    }


    //计算所有池子总的筹码集中度
    @BindingAdapter(value = {"meme_all_collect_tv1"},requireAll = false)
    public static void meme_all_collect_tv1(CircularProgressBar pb, Base2QuoEntity datas) {
        if(pb==null|datas==null||datas.getmPairsDTO().size()==0)return;

        String base_origin=datas.getContract();
        BigDecimal base_result=new BigDecimal(0.0);
        for(int i=0;i<datas.getmPairsDTO().size();i++){
            //获取当前池子的base币个数 这里的base 有可能不是nub 代币 例如 sin/nub
            String base_address=datas.getmPairsDTO().get(i).getBaseToken().getAddress();
            //获取当前pool的liq流动性
            DexScreenTokenInfo1.PairsDTO.LiquidityDTO mliq=datas.getmPairsDTO().get(i).getLiquidity();
            if(mliq==null)break;
            if(base_address.equalsIgnoreCase(base_origin)){
                BigDecimal basenumber=new BigDecimal(mliq.getBase());
                base_result=base_result.add(basenumber);
            }else{//quo是你要统计的币的个数
                BigDecimal basenumber=new BigDecimal(mliq.getQuote());
                base_result=base_result.add(basenumber);
            }

        }

        //总的发行base个数  这里的base 有可能不是nub 代币
        BigDecimal supply_result=callculatememesupllyTv(datas.getmPairsDTO().get(0));
        BigDecimal collect =base_result.divide(supply_result, 2, RoundingMode.HALF_UP);
        int ok=collect.multiply(new BigDecimal(100)).intValue();
        pb.setProgress(ok);
        pb.setText(ok+"%");


    }


    private static BigDecimal callculatememesupllyTv(DexScreenTokenInfo1.PairsDTO value) {
        if(value==null)return new BigDecimal(0.0);
        BigDecimal fdv=new BigDecimal(value.getFdv());
        BigDecimal priceusdValue = new BigDecimal(value.getPriceUsd());
       return fdv.divide(priceusdValue,RoundingMode.HALF_UP);
    }





    @BindingAdapter(value = {"meme_create_tv"},requireAll = false)
    public static void timestampToDateString(TextView tv, DexScreenTokenInfo1.PairsDTO value) {
        if(value==null)return;
        String dollar=tv.getContext().getString(R.string.str_daller);
        String result=DateUtils.timestampToDateString(value.getPairCreatedAt());
        tv.setText(result);
    }


    @BindingAdapter(value = {"transation_history_adapter","transation_history_item"},requireAll = false)
    public static void transation_history_time(TextView tv, TransHistorysrdapter adapter, TransationHistoryEntity item) {
        if(tv==null||adapter==null||item==null)return;

            int position= adapter.getCurrentList().indexOf(item);
            if(position>0){
                TransationHistoryEntity olditem=adapter.getCurrentList().get(position-1);

                String newDate=DateUtils.convertTimestampToDate(item.getBlockTime());
                String oldDate=DateUtils.convertTimestampToDate(olditem.getBlockTime());
                if(newDate.equals(oldDate)){
                    tv.setVisibility(View.GONE);
                }else{
                    tv.setVisibility(View.VISIBLE);
                    tv.setText(DateUtils.convertTimestampToDate(item.getBlockTime()));
                }
            }else{
                tv.setVisibility(View.VISIBLE);
                tv.setText(DateUtils.convertTimestampToDate(item.getBlockTime()));
            }


            }




    @BindingAdapter(value = {"transation_detail_history_adapter","transation_detail_history_item"},requireAll = false)
    public static void transation_detail_history_time(TextView tv, TransDetailHistorysrdapter adapter, TransationHistoryEntity item) {
        if(tv==null||adapter==null||item==null)return;

        int position= adapter.getCurrentList().indexOf(item);
        if(position>0){
            TransationHistoryEntity olditem=adapter.getCurrentList().get(position-1);

            String newDate=DateUtils.convertTimestampToDate(item.getBlockTime());
            String oldDate=DateUtils.convertTimestampToDate(olditem.getBlockTime());
            if(newDate.equals(oldDate)){
                tv.setVisibility(View.GONE);
            }else{
                tv.setVisibility(View.VISIBLE);
                tv.setText(DateUtils.convertTimestampToDate(item.getBlockTime()));
            }
        }else{
            tv.setVisibility(View.VISIBLE);
            tv.setText(DateUtils.convertTimestampToDate(item.getBlockTime()));
        }


    }



//        if(olditem.getBlockTime()==newitem.getBlockTime()){
//            tv.setVisibility(View.GONE);
//        }else{
//            tv.setVisibility(View.VISIBLE);
//        }


    @BindingAdapter(value = {"loadTime"},requireAll = false)
    public static void loadTime(TextView tv, long value) {
        if(tv==null)return;
        String newDate=DateUtils.convertTimestampToDate(value);
        tv.setText(newDate);

    }



    //计算5m交易数比例
    @BindingAdapter(value = {"meme_tx_number_tv"},requireAll = false)
    public static void memetxnumberTv(ProgressBar pb, DexScreenTokenInfo1.PairsDTO value) {
        if(value==null)return;
        BigDecimal buys=new BigDecimal(value.getTxns().getM5().getBuys());
        BigDecimal sells=new BigDecimal(value.getTxns().getM5().getSells());
        BigDecimal amount=buys.add(sells);
        if(amount.intValue()==0){//处理0的情况
            pb.setProgress(0);
            return;
        }
        BigDecimal buys_rate=buys.divide(amount,2, RoundingMode.HALF_UP);
        BigDecimal sells_rate= sells.divide(amount,2, RoundingMode.HALF_UP);
        int ok=buys_rate.multiply(new BigDecimal(100)).intValue();
        pb.setProgress(ok);
    }


    //计算1h交易数比例
    @BindingAdapter(value = {"meme_tx_1h_number_tv"},requireAll = false)
    public static void memetx1hnumberTv(ProgressBar pb, DexScreenTokenInfo1.PairsDTO value) {
        if(value==null)return;
        BigDecimal buys=new BigDecimal(value.getTxns().getH1().getBuys());
        BigDecimal sells=new BigDecimal(value.getTxns().getH1().getSells());
        BigDecimal amount=buys.add(sells);
        if(amount.intValue()==0){//处理0的情况
            pb.setProgress(0);
            return;
        }
        BigDecimal buys_rate=buys.divide(amount,2, RoundingMode.HALF_UP);
        BigDecimal sells_rate= sells.divide(amount,2, RoundingMode.HALF_UP);
        int ok=buys_rate.multiply(new BigDecimal(100)).intValue();
        pb.setProgress(ok);
    }


    //计算6h交易数比例
    @BindingAdapter(value = {"meme_tx_6h_number_tv"},requireAll = false)
    public static void memetx6hnumberTv(ProgressBar pb, DexScreenTokenInfo1.PairsDTO value) {
        if(value==null)return;
        BigDecimal buys=new BigDecimal(value.getTxns().getH6().getBuys());
        BigDecimal sells=new BigDecimal(value.getTxns().getH6().getSells());
        BigDecimal amount=buys.add(sells);
        if(amount.intValue()==0){//处理0的情况
            pb.setProgress(0);
            return;
        }
        BigDecimal buys_rate=buys.divide(amount,2, RoundingMode.HALF_UP);
        BigDecimal sells_rate= sells.divide(amount,2, RoundingMode.HALF_UP);
        int ok=buys_rate.multiply(new BigDecimal(100)).intValue();
        pb.setProgress(ok);
    }


    //计算24h交易数比例
    @BindingAdapter(value = {"meme_tx_24h_number_tv"},requireAll = false)
    public static void memetx24hnumberTv(ProgressBar pb, DexScreenTokenInfo1.PairsDTO value) {
        if(value==null)return;
        BigDecimal buys=new BigDecimal(value.getTxns().getH24().getBuys());
        BigDecimal sells=new BigDecimal(value.getTxns().getH24().getSells());
        BigDecimal amount=buys.add(sells);
        if(amount.intValue()==0){//处理0的情况
            pb.setProgress(0);
            return;
        }
        BigDecimal buys_rate=buys.divide(amount,2, RoundingMode.HALF_UP);
        BigDecimal sells_rate= sells.divide(amount,2, RoundingMode.HALF_UP);
        int ok=buys_rate.multiply(new BigDecimal(100)).intValue();
        pb.setProgress(ok);
    }


    @BindingAdapter(value = {"meme_percent_tv"},requireAll = false)
    public static void meme_percent_tv(ShapeTextView tv, double value) {
        if(tv==null)return;
        boolean result=DigitUtils.isNegative(value);
        String flat=tv.getContext().getString(R.string.str_precent);

        if(result){
            tv.setText(DigitUtils.formatAmountPercentChange(value)+flat);
            tv.getShapeDrawableBuilder().setSolidColor(tv.getContext().getColor(R.color.c_f71816)).intoBackground();
        }else{
            tv.getShapeDrawableBuilder().setSolidColor(tv.getContext().getColor(R.color.c_1bc89e)).intoBackground();
            tv.setText("+"+DigitUtils.formatAmountPercentChange(value)+flat);
        }


    }

    @BindingAdapter(value = {"meme_percent_trending_tv"},requireAll = false)
    public static void meme_percent_trending_tv(ShapeTextView bt, double value) {
        if(bt==null)return;
        boolean result=DigitUtils.isNegative(value);
        String flat=bt.getContext().getString(R.string.str_precent);

        if(result){
            bt.setText(DigitUtils.formatLargePercentage(value));
            bt.setTextColor(bt.getContext().getColor(R.color.c_f71816));
        }else{
            bt.setTextColor(bt.getContext().getColor(R.color.c_1bc89e));
            bt.setText("+"+DigitUtils.formatLargePercentage(value));
        }


    }


    @BindingAdapter(value = {"meme_percent_bt"},requireAll = false)
    public static void meme_percent_bt(ShapeTextView bt, double value) {
        if(bt==null)return;
        boolean result=DigitUtils.isNegative(value);
        if(result){
            bt.setText(DigitUtils.formatLargePercentage(value));
            bt.getShapeDrawableBuilder().setSolidColor(bt.getContext().getColor(R.color.c_f71816)).intoBackground();
//            bt.setTextColor(bt.getContext().getColor(R.color.c_f71816));
        }else{
//            bt.setTextColor(bt.getContext().getColor(R.color.c_1bc89e));
            bt.getShapeDrawableBuilder().setSolidColor(bt.getContext().getColor(R.color.c_1bc89e)).intoBackground();
            bt.setText("+"+DigitUtils.formatLargePercentage(value));
        }


    }

    @BindingAdapter(value = {"bindSettingBar_right"},requireAll = false)
    public static void bindSettingBar_Right(SettingBar bar,String right_txt_id) {
        bar.setRightText(right_txt_id);
    }


    @BindingAdapter(value = {"bindTextViewContent"},requireAll = false)
    public static void bindTextViewContent(TextView tv,String content) {
        if(tv==null||content==null||content.equals(""))return;
        tv.setText(content);
    }


    @BindingAdapter(value = {"memeviewpager_5m"},requireAll = false)
    public static void meme_5m(TextView tv, DexScreenTokenInfo1.PairsDTO mPairsDTO) {
        if(tv==null||mPairsDTO==null)return;
        int buys=mPairsDTO.getTxns().getM5().getBuys();
        int sells=mPairsDTO.getTxns().getM5().getSells();
        double priceChange=mPairsDTO.getPriceChange().getM5();
        String dollar=tv.getContext().getString(R.string.str_daller);
        double vol=mPairsDTO.getVolume().getM5();
        if(tv.getId()==R.id.buys){
            tv.setText(buys+"");
        }else if(tv.getId()==R.id.sells){
            tv.setText(sells+"");
        }else if(tv.getId()==R.id.vol){
            tv.setText(dollar+DigitUtils.formatAmount(vol));
        }else if(tv.getId()==R.id.pricechange){
            memeprice_common_changeTv(tv,priceChange);//公共的颜色处理
        }

    }

    @BindingAdapter(value = {"memeviewpager_1h"},requireAll = false)
    public static void meme_1h(TextView tv, DexScreenTokenInfo1.PairsDTO mPairsDTO) {
        if(tv==null||mPairsDTO==null)return;
        int buys=mPairsDTO.getTxns().getH1().getBuys();
        int sells=mPairsDTO.getTxns().getH1().getSells();
        double priceChange=mPairsDTO.getPriceChange().getH1();
        String dollar=tv.getContext().getString(R.string.str_daller);
        double vol=mPairsDTO.getVolume().getH1();
        if(tv.getId()==R.id.buys){
            tv.setText(buys+"");
        }else if(tv.getId()==R.id.sells){
            tv.setText(sells+"");
        }else if(tv.getId()==R.id.vol){
            tv.setText(dollar+DigitUtils.formatAmount(vol));
        }else if(tv.getId()==R.id.pricechange){
            memeprice_common_changeTv(tv,priceChange);//公共的颜色处理
        }

    }


    @BindingAdapter(value = {"memeviewpager_6h"},requireAll = false)
    public static void meme_6h(TextView tv, DexScreenTokenInfo1.PairsDTO mPairsDTO) {
        if(tv==null||mPairsDTO==null)return;
        int buys=mPairsDTO.getTxns().getH6().getBuys();
        int sells=mPairsDTO.getTxns().getH6().getSells();
        double priceChange=mPairsDTO.getPriceChange().getH6();
        String dollar=tv.getContext().getString(R.string.str_daller);
        double vol=mPairsDTO.getVolume().getH6();
        if(tv.getId()==R.id.buys){
            tv.setText(buys+"");
        }else if(tv.getId()==R.id.sells){
            tv.setText(sells+"");
        }else if(tv.getId()==R.id.vol){
            tv.setText(dollar+DigitUtils.formatAmount(vol));
        }else if(tv.getId()==R.id.pricechange){
            memeprice_common_changeTv(tv,priceChange);//公共的颜色处理
        }
    }


    @BindingAdapter(value = {"memeviewpager_24h"},requireAll = false)
    public static void meme24h(TextView tv, DexScreenTokenInfo1.PairsDTO mPairsDTO) {
        if(tv==null||mPairsDTO==null)return;
        int buys=mPairsDTO.getTxns().getH24().getBuys();
        int sells=mPairsDTO.getTxns().getH24().getSells();
        double priceChange=mPairsDTO.getPriceChange().getH24();
        String dollar=tv.getContext().getString(R.string.str_daller);
        double vol=mPairsDTO.getVolume().getH24();
        if(tv.getId()==R.id.buys){
            tv.setText(buys+"");
        }else if(tv.getId()==R.id.sells){
            tv.setText(sells+"");
        }else if(tv.getId()==R.id.vol){
            tv.setText(dollar+DigitUtils.formatAmount(vol));
        }else if(tv.getId()==R.id.pricechange){
            memeprice_common_changeTv(tv,priceChange);//公共的颜色处理
        }

    }

    @BindingAdapter(value = {"tvbyboolen"},requireAll = false)
    public static void tvbyboolen(TextView tv,boolean value) {
        if(tv==null)return;
        if(value){
            tv.setText(R.string.str_backuped);
            tv.setTextColor(tv.getContext().getColor(R.color.c_1bc89e));

        }else{
            tv.setText(R.string.str_no_backup);
            tv.setTextColor(tv.getContext().getColor(R.color.primaryTextGray));

        }

    }

    @BindingAdapter(value = {"calculateTokenDallor"},requireAll = false)
    public static void calculateTokenDallor(TextView tv, NewWalletToken token){
        if(tv==null||token==null)return;
        //获取balance
        BigDecimal balance=  new BigDecimal(token.getBalance());
        BigDecimal price=new BigDecimal(token.getPrice());
        BigDecimal amount=balance.multiply(price);
        String flat=tv.getContext().getString(R.string.str_vol_daller);

        tv.setText(flat+DigitUtils.formatPriceWithoutScientificNotation(amount.toPlainString(),6));

    }


    @BindingAdapter(value = {"calculateAmount"},requireAll = false)
    public static void calculateAmount(TextView tv, List<UiWalletToken> tokens){
        if(tv==null||tokens==null||tokens.size()==0)return;
        BigDecimal amount=new BigDecimal(0);
        for(UiWalletToken token:tokens){
            //获取balance
            BigDecimal balance=  new BigDecimal(token.getBalance());
            BigDecimal price=new BigDecimal(token.getPrice());
            amount=amount.add(balance.multiply(price));
        }

        String flat=tv.getContext().getString(R.string.str_vol_daller);
        tv.setText(flat+DigitUtils.formatNumberWithCommas(amount.doubleValue(),6));


    }

    @BindingAdapter(value = {"calculateTokenDallor1"},requireAll = false)
    public static void calculateTokenDallor1(TextView tv, NewWalletToken token){
        if(tv==null||token==null)return;
        //获取balance
        BigDecimal balance=  new BigDecimal(token.getBalance());
        BigDecimal price=new BigDecimal(token.getPrice());
        BigDecimal amount=balance.multiply(price);
        String flat=tv.getContext().getString(R.string.str_vol_daller);
        tv.setText(flat+DigitUtils.formatNumberWithCommas(amount.doubleValue(),Integer.parseInt(token.getDecimal())));

    }


    @BindingAdapter(value = {"closeSearchModel"},requireAll = false)
    public static void closeSearchModel(EditText tv, boolean value){
        if(tv==null)return;
        if(value==false){
            InputMethodManager imm = (InputMethodManager) tv.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(tv.getWindowToken(), 0);
            tv.clearFocus();

        }


    }


    @BindingAdapter(value = {"text_rent"},requireAll = false)
    public static void text_rent(TextView tv, String number){
        if(tv==null&&TextUtils.isEmpty(number))return;
        tv.setText("≈"+number+" sol");
    }




    @BindingAdapter(value = {"text_send_gas"},requireAll = false)
    public static void text_send_gas(TextView tv, String number){
        if(tv==null&&TextUtils.isEmpty(number))return;
        tv.setText(number+" sol");
    }



    @BindingAdapter(value = {"text_sol_show"},requireAll = false)
    public static void text_sol_show(TextView tv, String number){
        if(tv==null&&TextUtils.isEmpty(number))return;
        BigDecimal result = new BigDecimal(number).setScale(6, RoundingMode.HALF_UP);
        tv.setText(result.toPlainString()+" SOL");
    }

    @BindingAdapter(value = {"text_send_number"},requireAll = false)
    public static void text_send_number(TextView tv, String number){
        if(tv==null&&TextUtils.isEmpty(number))return;
        tv.setText(number+" SOL");
    }

    @BindingAdapter(value = {"txDirect"},requireAll = false)
    public static void txDirect(TextView tv, boolean value){
        if(tv==null)return;
        if(value){//发送者是自己
            tv.setText(tv.getContext().getString(R.string.str_send));
        }else{
            tv.setText(tv.getContext().getString(R.string.str_receiver));
        }
    }

    @BindingAdapter(value = {"history_item","direct"},requireAll = false)
    public static void history_item_direct(TextView tv, TransationHistoryEntity value,boolean direct){
        if(tv==null||value==null)return;
        if(direct){//发送者是自己
            tv.setText(tv.getContext().getString(R.string.str_to_tag)+value.getReceiver());
        }else{
            tv.setText(tv.getContext().getString(R.string.str_from_tag)+value.getSender());
        }
    }

    @BindingAdapter(value = {"exchange_show"},requireAll = false)
    public static void exchange_show(ShapeButton bt, boolean exchangeeEnable){
        if(bt==null)return;
        if(exchangeeEnable){
            bt.setText(R.string.str_swap);
            bt.setEnabled(true);


        }else{
            bt.setText(R.string.str_balance_not_value);
            bt.setEnabled(false);

        }
    }


    @BindingAdapter(value = {"meme_amount","meme_symbol"},requireAll = false)
    public static void meme_amount(TextView tv,String amount,String symbol) {
        if(tv==null||TextUtils.isEmpty(symbol))return;
        tv.setText(amount+" "+symbol);
    }

    @BindingAdapter(value = {"meme_top10_holder","meme_top10_index"},requireAll = false)
    public static void meme_top10_holder(TextView tv, CheckToken.TokenContractDTO.ContractDataDTO.TokenHoldersRankDTO value,int index) {
        if(tv==null||value==null)return;
         tv.setText(index+1+". "+value.getAddress());
    }


    @BindingAdapter(value = {"meme_top10_holder_number"},requireAll = false)
    public static void meme_top10_holder_number(TextView tv, CheckToken.TokenContractDTO.ContractDataDTO.TokenHoldersRankDTO value) {
        if(tv==null||value==null)return;
        tv.setText(DigitUtils.formatNumber_zh(value.getQuantity())+"("+DigitUtils.formatAsPercentage(value.getPercent())+")");
    }


    @BindingAdapter(value = {"meme_contract_buysell_gas"},requireAll = false)
    public static void meme_top10_holder_number(TextView tv, String value) {
        if(tv==null||TextUtils.isEmpty(value))return;
        tv.setText(DigitUtils.formatAsPercentage(value));
    }


    @BindingAdapter(value = {"meme_contract_mark"},requireAll = false)
    public static void meme_contract_mark(TextView tv, String value) {
        if(tv==null)return;
        if(TextUtils.isEmpty(value)){
            tv.setVisibility(View.GONE);
        }else{
            tv.setVisibility(View.VISIBLE);
            tv.setText(value);
        }

    }


    @BindingAdapter(value = {"meme_safe_level"},requireAll = false)
    public static void meme_safe_level(TextView tv, int value) {
        if(tv==null)return;
        if(value>=0&&value<=40){
            tv.setText(tv.getContext().getString(R.string.str_low_level));
            tv.setTextColor(tv.getContext().getColor(R.color.c_1bc89e));
        }else{
            tv.setText(tv.getContext().getString(R.string.str_hight_level));
            tv.setTextColor(tv.getContext().getColor(R.color.c_f71816));
        }


    }

    @BindingAdapter(value = {"meme_safe_5_items"},requireAll = false)
    public static void meme_safe_5_items(TextView tv, int value) {
        if(tv==null)return;
        tv.setText(value+"/"+"5");
    }


    @BindingAdapter(value = {"meme_money_show"},requireAll = false)
    public static void meme_money_show(TextView tv, double value) {
        if(tv==null)return;
        Log.d("----price---->",value+"");
        tv.setText("$"+ StringUtils.formatNumberPointZero(value));
    }

    @BindingAdapter(value = {"meme_sol_show"},requireAll = false)
    public static void meme_sol_show(TextView tv, CommonCategory.DataDTO value) {
        if(tv==null||value==null)return;
        if(value.getToken0Address().equalsIgnoreCase(TOKEN_SOL_CONTRACT)||value.getToken0Address().equalsIgnoreCase(TOKEN_USDC_CONTRACT)){
            tv.setText(StringUtils.num2thousand00(new BigDecimal(value.getReserve0()).toPlainString())+value.getToken0Symbol().toLowerCase());
        }else{
            tv.setText(StringUtils.num2thousand00(new BigDecimal(value.getReserve1()).toPlainString())+value.getToken1Symbol().toLowerCase());
        }

    }

    @BindingAdapter(value = {"meme_mcap_show"},requireAll = false)
    public static void meme_mcap_show(TextView tv, double value) {
        if(tv==null)return;
        tv.setText(DigitUtils.formatAmount(value));
    }

    @BindingAdapter(value = {"meme_tag_show"},requireAll = false)
    public static void meme_tag_show(ShapeImageView imv, String tag) {
        if(imv==null)return;
        if(TextUtils.isEmpty(tag)){
            imv.setVisibility(View.GONE);
        }else{
            imv.setVisibility(View.VISIBLE);
        }

    }



    @BindingAdapter(value = {"meme_dynamic_tag_show"},requireAll = false)
    public static void meme_dynamic_tag_show(ShapeImageView imv, String json_dynamic_tag) {
        if(imv==null||TextUtils.isEmpty(json_dynamic_tag))return;
        try{
            String[] arrays= parseJsonArray(json_dynamic_tag);
            if(arrays.length>0) {
                if (imv.getId() == R.id.tag_gen_doge) {//潜力狗
                    matchImvDoge(imv,arrays,"dynamic-alpha");
                } else if (imv.getId() == R.id.tag_gold_doge) {//金狗
                    matchImvDoge(imv,arrays,"dynamic-gem");
                } else if (imv.getId() == R.id.tag_rug_doge) {//跑路
                    matchImvDoge(imv,arrays,"dynamic-rug_pull");
                } else if (imv.getId() == R.id.tag_shit) {//流水盘
                    matchImvDoge(imv,arrays,"dynamic-shitcoin");

                } else if (imv.getId() == R.id.tag_hard_dump) {//暴跌
                    matchImvDoge(imv,arrays,"dynamic-hard_dump");
                }
            }
            }catch (Exception e){
                Log.e("----matchImvDoge-err-----",e.getMessage());
               }


    }



    @BindingAdapter(value = {"meme_name_show"},requireAll = false)
    public static void meme_name_show(TextView tv, CommonCategory.DataDTO value) {
        if(tv==null||value==null)return;
        if(value.getToken0Address().equalsIgnoreCase("So11111111111111111111111111111111111111112")){
            tv.setText(value.getToken1Symbol());
        }else{
            tv.setText(value.getToken0Symbol());
        }

    }




    private static void matchImvDoge(ShapeImageView imv,String[] arrays,String target){
        boolean result= StringUtils.contains(arrays,target);
        if(result){
            imv.setVisibility(View.VISIBLE);
        }else{
            imv.setVisibility(View.GONE);
        }

    }





}
