package com.zksg.kudoud.utils;

public class DigitUtils {

    public static String formatAmount(double amount) {

        if (amount < 1000) {
            // 如果金额小于 1000，直接返回金额的字符串形式，保留两位小数
            return String.format("%.2f", amount);
        } else if (amount < 1000000) {
            // 如果金额在 1000 到 1000000 之间，使用 k 后缀表示
            return String.format("%.2fk", amount / 1000);
        } else if (amount < 1000000000) {
            // 如果金额在 1000000 到 1000000000 之间，使用 m 后缀表示
            return String.format("%.2fm", amount / 1000000);
        } else {
            // 如果金额大于等于 1000000000，使用 b 后缀表示
            return String.format("%.2fb", amount / 1000000000);
        }
    }



    public static String formatAmountPercentChange(double amount) {
        return String.format("%.2f", amount);
    }


    //判断double 是否小于0
    public static boolean isNegative(double value) {
        // 使用阈值避免精度问题
        double threshold = 1e-10;
        return value < -threshold;
    }

}
