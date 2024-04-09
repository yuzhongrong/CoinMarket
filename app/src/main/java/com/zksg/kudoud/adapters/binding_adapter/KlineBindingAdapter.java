package com.zksg.kudoud.adapters.binding_adapter;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.github.mikephil.charting.stockChart.charts.CoupleChartGestureListener;
import com.github.mikephil.charting.stockChart.dataManage.KLineDataManage;
import com.hjq.shape.view.ShapeButton;
import com.zksg.kudoud.R;
import com.zksg.kudoud.contants.ChartDatas;
import com.zksg.kudoud.entitys.InitParamsEntity;
import com.zksg.kudoud.utils.DigitUtils;
import com.zksg.kudoud.widgets.SettingBar;
import com.github.mikephil.charting.stockChart.KLineChart;

import org.json.JSONException;
import org.json.JSONObject;

public class KlineBindingAdapter {
    private static int indexType = 1;
    @BindingAdapter(value = {"initKLineChart"},requireAll = false)
    public static void initKLineChart(KLineChart combinedchart, InitParamsEntity entity ) {

        if(combinedchart==null)return;
        boolean land=entity.isLand();
        int mType=entity.getType();
        JSONObject object = null;
        Context mContext=combinedchart.getContext();
        KLineDataManage kLineData=new KLineDataManage(mContext);
        combinedchart.initChart(land);
        try {
            if(mType == 1){
                object = new JSONObject(ChartDatas.k_1d_data);
            }else if(mType == 7){
                object = new JSONObject(ChartDatas.k_1w_data);
            }else if(mType == 30){
                object = new JSONObject(ChartDatas.k_1M_data);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //上证指数代码000001.IDX.SH
        kLineData.parseBitgetKlineData(object,"bitget.sol.usdt",land);
        combinedchart.setDataToChart(kLineData);

        combinedchart.getGestureListenerCandle().setCoupleClick(() -> {
            if(!land) {
//                    Intent intent = new Intent(mContext, StockDetailLandActivity.class);
//                    mContext.startActivity(intent);
            }
        });

        combinedchart.getGestureListenerBar().setCoupleClick(() -> {
            if(land) {
                int type=indexType < 5 ? ++indexType : 1;
                loadIndexData(type,combinedchart,kLineData);
            }else {
//                    Intent intent = new Intent(getActivity(), StockDetailLandActivity.class);
//                    mContext.startActivity(intent);
            }
        });

    }


    private static void loadIndexData(int type,KLineChart combinedchart,KLineDataManage kLineData) {
        indexType = type;
        switch (indexType) {
            case 1://成交量
                combinedchart.doBarChartSwitch(indexType);
                break;
            case 2://请求MACD
                kLineData.initMACD();
                combinedchart.doBarChartSwitch(indexType);
                break;
            case 3://请求KDJ
                kLineData.initKDJ();
                combinedchart.doBarChartSwitch(indexType);
                break;
            case 4://请求BOLL
                kLineData.initBOLL();
                combinedchart.doBarChartSwitch(indexType);
                break;
            case 5://请求RSI
                kLineData.initRSI();
                combinedchart.doBarChartSwitch(indexType);
                break;
            default:
                break;
        }
    }



}
