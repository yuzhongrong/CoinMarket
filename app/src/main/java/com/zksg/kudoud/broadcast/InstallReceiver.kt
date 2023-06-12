package com.zksg.kudoud.broadcast

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.tencent.mmkv.MMKV

class InstallReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (DownloadManager.ACTION_NOTIFICATION_CLICKED.equals(intent.action)) {


            var completeId= intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
            var allKeys= MMKV.mmkvWithID("DownloadIds").allKeys()

            if(allKeys==null&&!allKeys?.contains(completeId.toString())!!){//不是metastore下载的任务id
                return
            }
            val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            // 用户点击了下载完成通知
            val downloadIds =
                intent.getLongArrayExtra(DownloadManager.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS)

            // 处理点击事件，例如打开安装界面
            if (downloadIds != null && downloadIds.size > 0) {
                val downloadId = downloadIds[0]
                val uri: Uri = downloadManager.getUriForDownloadedFile(downloadId)
                if (uri != null) {
                    val installIntent = Intent(Intent.ACTION_VIEW)
                    installIntent.setDataAndType(uri, "application/vnd.android.package-archive")
                    installIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(installIntent)
                }
            }
        }
    }
}