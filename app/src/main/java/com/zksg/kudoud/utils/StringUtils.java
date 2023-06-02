package com.zksg.kudoud.utils;

public class StringUtils {

    public static String maskMiddle(String str) {
        int length = str.length();
        int mid = length / 2;

        StringBuilder builder = new StringBuilder(str);
        int start = mid - 1;
        int end = mid + 2;

        // 将中间的字符替换为星号
        builder.replace(start, end, "*************");

        return builder.toString();
    }

}
