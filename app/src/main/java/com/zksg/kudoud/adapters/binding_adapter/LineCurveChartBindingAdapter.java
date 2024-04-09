///*
// * Copyright 2018-present KunMinX
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *    http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package com.zksg.kudoud.adapters.binding_adapter;
//
//import android.graphics.Color;
//import android.graphics.Matrix;
//import android.graphics.drawable.Drawable;
//import android.util.Log;
//
//import androidx.core.content.ContextCompat;
//import androidx.databinding.BindingAdapter;
//
//import com.github.mikephil.charting.charts.LineChart;
//import com.github.mikephil.charting.components.AxisBase;
//import com.github.mikephil.charting.components.Legend;
//import com.github.mikephil.charting.components.XAxis;
//import com.github.mikephil.charting.components.YAxis;
//import com.github.mikephil.charting.data.Entry;
//import com.github.mikephil.charting.data.LineData;
//import com.github.mikephil.charting.data.LineDataSet;
//import com.github.mikephil.charting.formatter.IAxisValueFormatter;
//import com.github.mikephil.charting.formatter.ValueFormatter;
//import com.github.mikephil.charting.utils.Utils;
//import com.zksg.kudoud.R;
//import com.zksg.kudoud.beans.LineChartBean;
//import com.zksg.kudoud.charts.formatters.GraphXAxisValueFormatter;
//import com.zksg.kudoud.utils.TimeUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//import java.util.stream.Collectors;
//
///**TODO:Tip 这个类主要负责app 曲线绘制初始化工（心率+体重）
// * Create by FISH at 19/9/18
// */
//@SuppressWarnings("unused")
//public class LineCurveChartBindingAdapter {
//
//    /**
//     * 初始化图表
//     */
//    private static void initCurveChart(
//            LineChart lineChart,
//            List<LineChartBean.GRID0DTO.ResultDTO.ClientAccumulativeRateDTO> datas,
//            TimeUtils.Interval mSlot) {
//        Log.d("---xxx->initCurveChart", "initCurveChart");
//        if(lineChart==null)return;
//         XAxis xAxis;                //X轴
//         YAxis leftYAxis;            //左侧Y轴
//         YAxis rightYaxis;           //右侧Y轴
//         Legend legend;              //图例
////         LimitLine limitLine;        //限制线
////  private MyMarkerView markerView;    //标记视图 即点击xy轴交点时弹出展示信息的View 需自定义
//
//        /***图表设置***/
//
//        //是否展示网格线
//        lineChart.setDrawGridBackground(false);
//        //是否显示边界
//        lineChart.setDrawBorders(false);
//        //是否可以拖动
//        lineChart.setDragEnabled(true);
//        //是否可以缩放
//        lineChart.setScaleEnabled(false);
////        Matrix matrix = new Matrix();
//        // x轴放大4倍，y不变
////        matrix.postScale(2.0f, 1.0f);
//
//        // 设置缩放
////        lineChart.getViewPortHandler().refresh(matrix, lineChart, false);
//
//        //是否有触摸事件
//        lineChart.setTouchEnabled(true);
//        //设置XY轴动画效果
////        lineChart.animateY(500);
////        lineChart.animateX(500);
//        //设置背景
//        lineChart.setBackgroundColor(Color.TRANSPARENT);
//        //是否显示边界
//        lineChart.setDrawBorders(false);
//        //描述相关
//        lineChart.getDescription().setEnabled(false);
//
//
//
//        /***XY轴的设置***/
//        xAxis = lineChart.getXAxis();
//        leftYAxis = lineChart.getAxisLeft();
//        rightYaxis = lineChart.getAxisRight();
//        //去掉网格线
//        xAxis.setDrawGridLines(false);
//        rightYaxis.setDrawGridLines(false);
//        //保证Y轴从0开始，不然会上移一点
//        leftYAxis.setAxisMinimum(0f);
//        leftYAxis.setDrawGridLines(false);// 不从y轴发出横向直线
//        leftYAxis.setDrawZeroLine(false);// 是否显示y轴坐标线
//        leftYAxis.setDrawAxisLine(false);
//        leftYAxis.setTextColor(lineChart.getResources().getColor(R.color.c_666666));
//        leftYAxis.setTextSize(12);
//        leftYAxis.setXOffset(10f);
//        leftYAxis.setYOffset(-10f);
//        leftYAxis.setLabelCount(5, true);
//        leftYAxis.setEnabled(false);//隐藏左边y轴
//        /** TODO:tip 这里通过linechart id 来区分y轴值的范围
//         *   R.id.chart_weight:10~50
//         *   R.id.chart_heartrate:40~200
//         */
//        switch (lineChart.getId()){
//            case R.id.chart_weight:
//                leftYAxis.setAxisMaximum(50);//设置y轴的最大值
//                leftYAxis.setAxisMinimum(10);// 设置y轴的最小值
//                break;
//            case R.id.chart_heartrate:
//                leftYAxis.setAxisMaximum(200);//设置y轴的最大值
//                leftYAxis.setAxisMinimum(40);// 设置y轴的最小值
//                break;
//        }
//
//
//        //X轴设置显示位置在底部
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setTextSize(12);
//        xAxis.setYOffset(15f);
//        xAxis.setXOffset(-15f);
//        xAxis.setDrawAxisLine(false);
//        xAxis.setTextColor(lineChart.getResources().getColor(R.color.c_666666));
//        //java8 使用
//        String[] mLabels=TimeUtils.formatTime(datas, mSlot);
//        // 显示x轴标签
//        ValueFormatter formatter = new ValueFormatter() {
//            @Override
//            public String getFormattedValue(float value) {
//                int index = (int) value;
//                if (index < 0 || index >= mLabels.length) {
//                    return "";
//                }
//                return mLabels[index];
//                // return labels.get(Math.min(Math.max((int) value, 0), labels.size() - 1));
//            }
//        };
//        // 引用标签
////        GraphXAxisValueFormatter formatter= new GraphXAxisValueFormatter(datas,3, GraphXAxisValueFormatter.Interval.HOUR);
//        xAxis.setValueFormatter(formatter);
//
//        //设置x自动缩放
////        Matrix matrix = new Matrix();
//        // 根据数据量来确定 x轴缩放大倍
////        if (mLabels.size() <= 10) {
////            matrix.postScale(1.0f, 1.0f);
////        } else if (mLabels.size() <= 15) {
////            matrix.postScale(1.5f, 1.0f);
////        } else if (mLabels.size() <= 20) {
////            matrix.postScale(2.0f, 1.0f);
////        } else {
////            matrix.postScale(3.0f, 1.0f);
////        }
////        lineChart.getViewPortHandler().refresh(matrix, lineChart, false);
////         设置x轴每最小刻度 interval
//        xAxis.setGranularity(4f);
//        xAxis.setLabelCount(5,false);
//        xAxis.setEnabled(false);//隐藏x轴
//
//        //去掉右边y轴
//        rightYaxis.setEnabled(false);
//        rightYaxis.setAxisMinimum(0f);
//
//        /***折线图例 标签 设置 目前用不到***/
//        legend = lineChart.getLegend();
//        legend.setEnabled(false);
////        //设置显示类型，LINE CIRCLE SQUARE EMPTY 等等 多种方式，查看LegendForm 即可
////        legend.setForm(Legend.LegendForm.LINE);
////        legend.setTextSize(12f);
////        //显示位置 左下方
////        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
////        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
////        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
////        //是否绘制在图表里面
////        legend.setDrawInside(false);
//    }
//
//    /**
//     * 曲线初始化设置 一个LineDataSet 代表一条曲线
//     *
//     * @param lineDataSet 线条
//     * @param color       线条颜色
//     * @param mode
//     */
//
//    private static void initCurveLineDataSet(LineDataSet lineDataSet, int color, LineDataSet.Mode mode) {
//        if(lineDataSet==null)return;
//        lineDataSet.setColor(color);
//        lineDataSet.setCircleColor(color);
//        lineDataSet.setLineWidth(1f);
//        lineDataSet.setCircleRadius(1.3f);
//        //设置曲线值的圆点是实心还是空心
//        lineDataSet.setDrawCircleHole(false);
//        lineDataSet.setDrawCircles(false);//不画圆点
//        lineDataSet.setValueTextSize(15f); //去掉显示文字
//        lineDataSet.setDrawValues(false);
//        //高亮设置
//        lineDataSet.setHighLightColor(Color.TRANSPARENT);
//        //设置折线图填充
//        lineDataSet.setDrawFilled(true);
//        lineDataSet.setFormLineWidth(1f);
//        lineDataSet.setFormSize(15.f);
//        if (Utils.getSDKInt() >= 18) {
//            // fill drawable only supported on api level 18 and above
//            Drawable drawable = ContextCompat.getDrawable(com.kunminx.architecture.utils.Utils.getApp(), R.drawable.chart_bg);
//            lineDataSet.setFillDrawable(drawable);
//        }
//        else {
//            lineDataSet.setFillColor(Color.BLACK);
//        }
//
//        if (mode == null) {
//            //设置曲线展示为圆滑曲线（如果不设置则默认折线）
//            lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
//        } else {
//            lineDataSet.setMode(mode);
//        }
//
//
//    }
//
//
//
//    /**
//     * 展示曲线
//     *
//     * @param dataList 数据集合
//     */
//    @BindingAdapter(value = {"showCurveLineChart","mSlot"})
//    public static void showCurveLineChart(LineChart lineChart,List<LineChartBean.GRID0DTO.ResultDTO.ClientAccumulativeRateDTO> dataList,TimeUtils.Interval mSlot) {
//        if(dataList==null||dataList.size()==0)return;
//        initCurveChart(lineChart,dataList,mSlot);
//        Log.d("---xxx->showCurveLineChart", "showCurveLineChart: ");
//        List<Entry> entries = new ArrayList<>();
//        for (int i = 0; i < dataList.size(); i++) {
//            LineChartBean.GRID0DTO.ResultDTO.ClientAccumulativeRateDTO data = dataList.get(i);
//            /**
//             * 在此可查看 Entry构造方法，可发现 可传入数值 Entry(float x, float y)
//             * 也可传入Drawable， Entry(float x, float y, Drawable icon) 可在XY轴交点 设置Drawable图像展示
//             */
//            Entry entry = new Entry(i, (float) data.getValue());
//            entries.add(entry);
//        }
//        // 每一个LineDataSet代表一条线
//        LineDataSet lineDataSet = new LineDataSet(entries, "");
//        initCurveLineDataSet(lineDataSet, Color.parseColor("#0ecb81"), LineDataSet.Mode.HORIZONTAL_BEZIER);
//        LineData lineData = new LineData(lineDataSet);
//        lineChart.setData(lineData);
//        lineChart.invalidate();
//    }
//
//
//
//
//
//
//}
