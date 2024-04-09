//package com.zksg.kudoud.charts.formatters;
//
//import android.util.Log;
//
//import com.github.mikephil.charting.components.AxisBase;
//import com.github.mikephil.charting.formatter.IAxisValueFormatter;
//import com.github.mikephil.charting.formatter.ValueFormatter;
//import com.zksg.kudoud.beans.LineChartBean;
//import com.zksg.kudoud.utils.TimeUtils;
//
//import java.util.Calendar;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//public class GraphXAxisValueFormatter extends ValueFormatter {
//
//    private static int MINUTES_INTERVAL = 5;
//    private String[] mValues;
//    private int mInterval;
//    private Interval mSlot;
//
//    public GraphXAxisValueFormatter(List<LineChartBean.GRID0DTO.ResultDTO.ClientAccumulativeRateDTO> range, int interval, Interval slot){
//        mValues = new String[range.size()];
//        mInterval = interval;
//        mSlot = slot;
//
//        Calendar calendar = Calendar.getInstance();
//        for (int i = 0; i < range.size(); i++) {
//            //seconds->mill
//            long mills=TimeUnit.SECONDS.toMillis(Integer.valueOf(range.get(i).getTradeDate()));
//            calendar.setTimeInMillis(mills);
//
//            int unroundedMinutes = calendar.get(Calendar.MINUTE);
//            int mod = unroundedMinutes % MINUTES_INTERVAL;
//            calendar.add(Calendar.MINUTE, mod < 8 ? -mod : (MINUTES_INTERVAL - mod));
//
//
//            String s = "";
//
//            if (mSlot.equals(Interval.HOUR) || mSlot.equals(Interval.DAY))
//                s = TimeUtils.time2date(calendar.getTimeInMillis()+"","HH:mm");
//            else if (mSlot.equals(Interval.WEEK))
//                s = TimeUtils.time2week(calendar.getTimeInMillis());//周天到周六
//            else if (mSlot.equals(Interval.MONTH))
//                s = TimeUtils.time2date(calendar.getTimeInMillis()+"","dd");//1~30号
//            else if (mSlot.equals(Interval.YEAR))
//                s = TimeUtils.time2date(calendar.getTimeInMillis()+"","MM");//1~12月
//
//            Log.d("GraphXAxisValueFormatter", "Time : "+s);
//            mValues[i] = s;
//        }
//
//
//
//    }
//
//
//    @Override
//    public String getFormattedValue(float value) {
//        Log.d("GraphXAxisValueFormatter", "Value : "+ value);
//        if (value % mInterval == 0 && value >= 0) {
//            return mValues[(int) value % mValues.length];
//        } else
//            return "";
//    }
//
//    public enum Interval{
//        HOUR,
//        DAY,
//        WEEK,
//        MONTH,
//        YEAR
//
//    }
//}
