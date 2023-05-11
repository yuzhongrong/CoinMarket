package com.zksg.kudoud.utils;

import android.util.Log;

import com.zksg.kudoud.beans.LineChartBean;
import com.zksg.kudoud.charts.formatters.GraphXAxisValueFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TimeUtils {


    /**
     *
     * @param miltime:时间戳
     * @param format:年日月
     * @return
     */
    public static String time2date(String miltime,String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.valueOf(miltime));
//        SimpleDateFormat sf = new SimpleDateFormat("MM-dd ");//这里的格式可换"yyyy年-MM月dd日-HH时mm分ss秒"等等格式
        SimpleDateFormat sf = new SimpleDateFormat(format);
        String date = sf.format(calendar.getTime());
        return date;
    }

    public static String time2week(long miltime){
       return com.blankj.utilcode.util.TimeUtils.getChineseWeek(miltime);
    }


    public static String[] formatTime(List<LineChartBean.GRID0DTO.ResultDTO.ClientAccumulativeRateDTO> range, Interval mSlot){
        String[] mValues=new String[range.size()];
        for (int i = 0; i < range.size(); i++) {
            String s = "";
            long mills=TimeUnit.SECONDS.toMillis(Integer.valueOf(range.get(i).getTradeDate()));
            if (mSlot.equals(TimeUtils.Interval.HOUR) || mSlot.equals(TimeUtils.Interval.DAY))
                s = TimeUtils.time2date(mills+"","HH:mm");
            else if (mSlot.equals(TimeUtils.Interval.WEEK))
                s = TimeUtils.time2week(mills);//周天到周六
            else if (mSlot.equals(TimeUtils.Interval.MONTH))
                s = TimeUtils.time2date(mills+"","dd");//1~30号
            else if (mSlot.equals(TimeUtils.Interval.YEAR))
                s = TimeUtils.time2date(mills+"","MM");//1~12月

            Log.d("GraphXAxisValueFormatter", "Time : "+s);
            mValues[i] = s;
        }
        return mValues;

    }

    public enum Interval{
        HOUR,
        DAY,
        WEEK,
        MONTH,
        YEAR

    }

}
