package com.zksg.kudoud.broadcast

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.Utils
import com.tencent.mmkv.MMKV
import java.io.File

class DownloadBroadcastReceiver : BroadcastReceiver() {


    @SuppressLint("Range")
    override fun onReceive(context: Context, intent: Intent) {
        //mmkv get a download id
        var completeId= intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
        var allKeys= MMKV.mmkvWithID("DownloadIds").allKeys()

        if(allKeys==null&&!allKeys?.contains(completeId.toString())!!){//不是metastore下载的任务id
            return
        }
        //下载完成 重命名文件后缀
        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val query = DownloadManager.Query().setFilterById(completeId)
            val cursor = downloadManager.query(query)
            if (cursor.moveToFirst()) {
                val columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)
                if (cursor.getInt(columnIndex) == DownloadManager.STATUS_SUCCESSFUL) {
                    val columnUriIndex = cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI)
                    val status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
                    val localUriString = cursor.getString(columnUriIndex)
                    val localUri = Uri.parse(localUriString)
                    val file = File(localUri.path)

                   //  在这里获取到下载完成的文件，并进行进一步的操作
//                    val actualFileName = file.name
//
////                     对文件进行重命名或其他操作
////                     例如，如果你期望下载的是图片文件，可以根据实际的文件类型为其添加正确的后缀
//
////                     这里需要根据你期望的后缀类型进行相应的处理
//                    val desiredFileExtension = ".apk"
//                    val newFileName =
//                        "${actualFileName.substringBeforeLast(".")}$desiredFileExtension"
//                    val renamedFile = File(file.parent, newFileName)
//                    file.renameTo(renamedFile)

                    if(status==DownloadManager.STATUS_SUCCESSFUL){
                        AppUtils.installApp(file)
                    }

                }
            }
            cursor.close()

        context.unregisterReceiver(this)
        MMKV.mmkvWithID("DownloadIds").remove(completeId.toString())
    }
}