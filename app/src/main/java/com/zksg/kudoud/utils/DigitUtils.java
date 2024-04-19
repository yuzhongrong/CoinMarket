package com.zksg.kudoud.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

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


    public static String formatPriceAmount(double amount) {
        // 如果金额小于 1000，直接返回金额的字符串形式，保留实际的小数位数
        if (amount < 1000) {
            // 获取小数部分的位数
            String decimalPart = String.valueOf(amount).split("\\.")[1];
            int decimalPlaces = decimalPart.length();

            // 格式化小数，保留实际位数的小数点后数字
            String format = "%." + decimalPlaces + "f";
            return String.format(format, amount);
        }

        // 如果金额在 1000 到 1000000 之间，使用 k 后缀表示
        if (amount < 1000000) {
            return String.format("%.10fk", amount / 1000);
        }

        // 如果金额在 1000000 到 1000000000 之间，使用 m 后缀表示
        if (amount < 1000000000) {
            return String.format("%.10fm", amount / 1000000);
        }

        // 如果金额大于等于 1000000000，使用 b 后缀表示
        return String.format("%.10fb", amount / 1000000000);
    }

    public static String formatAmountTip() {
        return "1m=1百万,1b=10亿";
    }

    //格式化大数字整型
    public static String formatNumberWithCommas(long number) {
        // 创建 NumberFormat 对象，指定本地化为默认地区
        NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());

        // 设置分组大小为 3
        nf.setGroupingUsed(true);

        // 使用 NumberFormat 格式化数字，并返回格式化后的字符串
        return nf.format(number);
    }


    public static double parseDouble(String str) {
        // 使用 BigDecimal 将字符串转换为 double 类型
        BigDecimal bd = new BigDecimal(str);
        double value = bd.doubleValue();
        return value;
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
