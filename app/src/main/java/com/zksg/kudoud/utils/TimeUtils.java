package com.zksg.kudoud.utils;

import android.util.Log;

import com.zksg.kudoud.beans.LineChartBean;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
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

    public static String time2date(long miltime,String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(miltime);
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


    public static String getRelativeTime(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        try {
            Date date = sdf.parse(dateString);
            Date now = new Date();

            long diffInMillis = now.getTime() - date.getTime();

            long minutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillis);
            long hours = TimeUnit.MILLISECONDS.toHours(diffInMillis);
            long days = TimeUnit.MILLISECONDS.toDays(diffInMillis);

            if (days > 0) {
                return days + "天前";
            } else if (hours > 0) {
                return hours + "小时前";
            } else if (minutes > 0) {
                return minutes + "分钟前";
            } else {
                return "刚刚";
            }

        } catch (ParseException e) {
            e.printStackTrace();
            return "未知时间";
        }
    }


    public enum Interval{
        HOUR,
        DAY,
        WEEK,
        MONTH,
        YEAR

    }

}
