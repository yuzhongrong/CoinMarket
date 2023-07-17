package com.zksg.kudoud.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

public class StringUtils {


    public static String hideMiddleOfString(String input, int visibleCharacters) {
        if (input.length() <= visibleCharacters) {
            return input;  // 字符串长度不超过指定可见字符数，无需隐藏
        }

        int startVisible = (visibleCharacters - 3) / 2;  // 中间部分开始的可见字符位置
        int endVisible = input.length() - (visibleCharacters - startVisible - 3);  // 中间部分结束的可见字符位置

        StringBuilder stringBuilder = new StringBuilder(input);
        stringBuilder.replace(startVisible, endVisible, "...");

        return stringBuilder.toString();
    }


    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 字符串 千位符
     *
     * @param num
     * @return
     */
    public static String num2thousand(String num) {
        String numStr = "";
        if (isEmpty(num)) {
            return numStr;
        }
        NumberFormat nf = NumberFormat.getInstance();
        try {
            DecimalFormat df = new DecimalFormat("#,###");
            numStr = df.format(nf.parse(num));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return numStr;
    }

    /**
     * 字符串 千位符  保留两位小数点后两位
     *
     * @param num
     * @return
     */
    public static String num2thousand00(String num) {
        String numStr = "";
        if (isEmpty(num)) {
            return numStr;
        }
        NumberFormat nf = NumberFormat.getInstance();
        try {
            DecimalFormat df = new DecimalFormat("#,##0.00");
            numStr = df.format(nf.parse(num));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return numStr;
    }


}
