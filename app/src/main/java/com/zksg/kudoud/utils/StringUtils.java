package com.zksg.kudoud.utils;

import java.math.BigDecimal;
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


    // 0.00000003434 这样的数字格式化为类似 0.0{7}3434 的格式
    public static String formatNumberPointZero(double num) {
        // 将数字转换为字符串
        String numStr =StringUtils.convertScientificToPlainString(num);

        // 检查是否为小数格式
        if (!numStr.startsWith("0.")) {
            return DigitUtils.formatPriceWithoutScientificNotation(numStr, 6);
        }

        // 去掉 "0."
        String decimalPart = numStr.substring(2);

        // 统计前导零的数量
        int zeroCount = 0;
        for (char ch : decimalPart.toCharArray()) {
            if (ch == '0') {
                zeroCount++;
            } else {
                break;
            }
        }

        // 只有前导零数量超过3时才进行格式化
        if (zeroCount > 3) {
            String nonZeroPart = decimalPart.substring(zeroCount);

            // 保留3位非零数字
            String truncatedNonZeroPart = nonZeroPart.length() > 3
                    ? nonZeroPart.substring(0, 3)
                    : nonZeroPart;

            return "0.0{" + zeroCount + "}" + truncatedNonZeroPart;
        }

        // 如果前导零数量不超过3，保留小数点后面5位
        if (decimalPart.length() > 5) {
            decimalPart = decimalPart.substring(0, 5);
        }

        // 返回保留5位小数的结果
        return "0." + decimalPart;
    }

    public static String convertScientificToPlainString(double number) {
        // 将 double 转换为字符串
        String numberStr = Double.toString(number);

        // 检查是否为科学计数法
        if (numberStr.contains("E") || numberStr.contains("e")) {
            // 使用 BigDecimal 将科学计数法转换为常规数字格式
            BigDecimal bigDecimal = new BigDecimal(numberStr);
            return bigDecimal.toPlainString();
        }

        // 如果不是科学计数法，直接返回格式化的数字
        return numberStr;
    }



}
