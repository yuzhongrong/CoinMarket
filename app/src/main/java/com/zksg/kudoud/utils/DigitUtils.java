package com.zksg.kudoud.utils;

import android.content.Context;
import android.widget.TextView;

import com.zksg.kudoud.R;
import com.zksg.kudoud.entitys.UiWalletToken;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;
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


    public static String formatNumber_zh(String numberStr) {
        // Parse the string to a double
        double number;
        try {
            number = Double.parseDouble(numberStr);
        } catch (NumberFormatException e) {
            // Handle the case where the input string is not a valid number
            return "Invalid number";
        }

        // Define the units and their thresholds
        String[] units = {"", "万", "亿"};
        double[] thresholds = {1, 10_000, 100_000_000};

        String formattedNumber = "";

        for (int i = units.length - 1; i >= 0; i--) {
            if (number >= thresholds[i]) {
                formattedNumber = new DecimalFormat("#,##0.##").format(number / thresholds[i]) + units[i];
                break;
            }
        }

        return formattedNumber;
    }


    public static String formatAsPercentage(String numberStr) {
        // Parse the string to a double
        double number;
        try {
            number = Double.parseDouble(numberStr);
        } catch (NumberFormatException e) {
            // Handle the case where the input string is not a valid number
            return "Invalid number";
        }

        // Multiply by 100 to convert to percentage
        double percentage = number * 100;

        // Format the percentage with two decimal places
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(percentage) + "%";
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


    public static String formatNumber(double value) {
        if (value < 1_000_000) {
            return String.valueOf(value);
        } else if (value < 10_000_000) {
            return String.format("%.2f百万", value / 1_000_000.0);
        } else if (value < 100_000_000) {
            return String.format("%.2f千万", value / 10_000_000.0);
        } else {
            return String.format("%.3f亿", value / 100_000_000.0);
        }
    }


    public static String formatAmountTip() {
        return "1m=1百万,1b=10亿";
    }

    //格式化大数字整型
    public static String formatNumberWithCommas(double number) {
        // 如果小于 0，则直接返回原始的 double 字符串
        if (number <=0) {
            return String.valueOf(number);
        }

        // 设置小数点后的位数为 2
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance(Locale.getDefault());
        DecimalFormat df = new DecimalFormat("#,###.##", symbols);

        // 对数字进行舍入，保留两位小数，并使用 DecimalFormat 格式化数字
        return df.format(Math.round(number * 100) / 100.0);
    }


    public static String formatNumberWithCommas(double number, int decimalDigits) {
        // 如果小于等于 0，则直接返回原始的 double 字符串
        if (number <= 0) {
            return String.valueOf(number);
        }

        // 构造格式化对象
        String pattern = "#,###." + new String(new char[decimalDigits]).replace('\0', '#');
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance(Locale.getDefault());
        DecimalFormat df = new DecimalFormat(pattern, symbols);

        // 对数字进行舍入，并使用 DecimalFormat 格式化数字
        return df.format(Math.round(number * Math.pow(10, decimalDigits)) / Math.pow(10, decimalDigits));
    }


    public static String formatPriceWithoutScientificNotation(String price, int precision) {
        // Convert String to BigDecimal
        BigDecimal bigDecimal = new BigDecimal(price);

        // Convert BigDecimal to plain string to avoid scientific notation
        String plainString = bigDecimal.toPlainString();

        // Find the decimal point
        int decimalPointIndex = plainString.indexOf('.');

        // Check if there is a decimal part
        if (decimalPointIndex != -1) {
            // Split the integer and decimal parts
            String integerPart = plainString.substring(0, decimalPointIndex);
            String decimalPart = plainString.substring(decimalPointIndex + 1);

            // Truncate the decimal part based on the specified precision
            if (decimalPart.length() > precision) {
                decimalPart = decimalPart.substring(0, precision);
            }

            // Reconstruct the number with the truncated decimal part
            return integerPart + "." + decimalPart;
        }

        // If there's no decimal part, return the integer part as is
        return plainString;
    }




    public static String formatAmount(long amount, int decimalPlaces) {
        // 将 long 类型的 amount 转换为 BigDecimal
        BigDecimal amountBigDecimal = new BigDecimal(amount);

        // 计算除数 10 的次方
        BigDecimal divisor = BigDecimal.TEN.pow(decimalPlaces);

        // 将金额除以 10 的 divisorPower 次方
        BigDecimal result = amountBigDecimal.divide(divisor, decimalPlaces, RoundingMode.HALF_UP);

        // 使用 toPlainString 方法来避免科学记数法
        return result.toPlainString();
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


    public static String calculateAmount(Context mContext,List<UiWalletToken> tokens){
        if(tokens==null||tokens.size()==0)return "0";
        BigDecimal amount=new BigDecimal(0);
        for(UiWalletToken token:tokens){
            //获取balance
            BigDecimal balance=  new BigDecimal(token.getBalance());
            if(balance.doubleValue()==0.0)continue;
            BigDecimal price=new BigDecimal(token.getPrice());
            amount=amount.add(balance.multiply(price));
        }
        String flat=mContext.getString(R.string.str_vol_daller);
       return flat+DigitUtils.formatNumberWithCommas(amount.doubleValue(),6);
    }

}
