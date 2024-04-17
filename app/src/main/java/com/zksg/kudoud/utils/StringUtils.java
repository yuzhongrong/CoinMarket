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

    //获取twitter用户名
    public static String extractUsernameFromUrl(String url) {
        // 如果链接以 "https://twitter.com/" 开头
        if (url.startsWith("https://twitter.com/")) {
            // 获取链接中最后一个 "/" 后面的部分作为用户名
            int index = url.lastIndexOf("/") + 1;
            if (index != -1 && index < url.length()) {
                return url.substring(index);
            }
        }

        // 如果链接不符合格式，返回空字符串
        return "";
    }


    public static String extractUsernameFromUrlTG(String url) {
        // 如果链接以 "https://t.me/" 开头
        if (url.startsWith("https://t.me/")) {
            // 获取链接中最后一个 "/" 后面的部分作为用户名
            int index = url.lastIndexOf("/") + 1;
            if (index != -1 && index < url.length()) {
                return url.substring(index);
            }
        }

        // 如果链接不符合格式，返回空字符串
        return "";
    }


}
