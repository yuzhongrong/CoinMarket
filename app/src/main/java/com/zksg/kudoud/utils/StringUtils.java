package com.zksg.kudoud.utils;

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

}
