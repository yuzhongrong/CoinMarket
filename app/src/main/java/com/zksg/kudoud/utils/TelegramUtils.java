package com.zksg.kudoud.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.netease.lib_network.entitys.DexScreenTokenInfo1;
import com.zksg.kudoud.R;


public class TelegramUtils {

    public static void startTelegram(Context mContext, DexScreenTokenInfo1.PairsDTO.InfoDTO.SocialsDTO social){
        String username="";
        if(social==null){
            ToastUtils.showShort(R.string.str_Invalid_telegram);
            return;
        }

        if(social.getType().equals("telegram")){
            username= StringUtils.extractUsernameFromUrlTG(social.getUrl());
        }
        if(username.equals("")){
            ToastUtils.showShort(R.string.str_Invalid_telegram);
            return;
        }
        IntentUtils.openTelegram(mContext,username);
    }






        /**
         * 打开Telegram机器人链接的方法
         *
         * @param context          上下文对象
         * @param telegramBotLink  Telegram机器人的链接
         */
        public static void openTelegramBot(Context context, String telegramBotLink) {
            // 创建Intent以打开Telegram
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(telegramBotLink));
            intent.setPackage("org.telegram.messenger");  // 指定使用Telegram应用程序

            // 检查是否有应用程序能够处理这个Intent
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);  // 使用Telegram应用程序打开链接
            } else {
                // 如果Telegram未安装，使用浏览器打开链接
                intent.setPackage(null);  // 移除包名，以便使用默认浏览器打开链接
                context.startActivity(intent);
            }
        }

        public static void openTelegramBot1(Context mContext,String url){

            String telegramBotUrl =url;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(telegramBotUrl));

// 检查设备是否安装了Telegram
            if (intent.resolveActivity(mContext.getPackageManager()) != null) {
                mContext.startActivity(intent);
            } else {
                // 如果没有安装Telegram，提示用户安装
                Toast.makeText(mContext, "请先安装Telegram", Toast.LENGTH_SHORT).show();
            }



        }







}
