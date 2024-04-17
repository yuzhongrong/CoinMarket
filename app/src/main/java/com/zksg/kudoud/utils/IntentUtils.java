package com.zksg.kudoud.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class IntentUtils {
    // 打开 Twitter 应用或者浏览器，并跳转到指定的 Twitter 用户页面
    public static void openTwitter(Context mContext,String username) {
        try {
            // 尝试打开 Twitter 应用
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=" + username));
            mContext.startActivity(intent);
        } catch (Exception e) {
            // 如果无法打开 Twitter 应用，则打开浏览器，并跳转到 Twitter 网页
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/" + username));
            mContext.startActivity(intent);
        }
    }


    // 打开 Telegram 应用或者浏览器，并跳转到指定的 Telegram 用户页面
    public static void openTelegram(Context mContext,String username) {
        try {
            // 尝试打开 Telegram 应用
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tg://resolve?domain=" + username));
            mContext.startActivity(intent);
        } catch (Exception e) {
            // 如果无法打开 Telegram 应用，则打开浏览器，并跳转到 Telegram 网页
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/" + username));
            mContext.startActivity(intent);
        }
    }


    // 打开默认的浏览器，并跳转到指定的链接
    public static void openWebsite(Context mContext,String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        mContext.startActivity(intent);
    }

}
