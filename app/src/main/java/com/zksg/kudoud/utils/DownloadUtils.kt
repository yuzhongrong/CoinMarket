package com.zksg.kudoud.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Environment
import android.text.TextUtils
import android.widget.Toast
import androidx.core.content.FileProvider
import com.zksg.kudoud.R
import java.io.File
import java.io.IOException

class DownloadUtils(private val mContext: Context, url: String, name: String) {
    /**
     * 下载器
     */
    private var downloadManager: DownloadManager? = null

    /**
     * 下载的ID
     */
    private var downloadId: Long = 0

    /**
     * apk保存路径
     */
    private var pathStr: String? = null

    //下载apk
    private fun downloadApk(url: String, name: String) {
        //设置下载的路径
        val file = File(mContext.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), name)
        pathStr = file.absolutePath
        if (file.exists()) {
            installApk()
        } else {
            Toast.makeText(mContext, "开始下载，请在通知栏中查看进度。。。", Toast.LENGTH_SHORT).show()
            //创建下载任务
            val request = DownloadManager.Request(Uri.parse(url))
            //设置允许使用的网络类型，这里是移动网络和wifi都可以
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
            //在通知栏中显示，默认就是显示的(下载完也显示)（这个如果让显示下载完成可以点击的话会导致系统的downloadmanager篡改下载的文件后缀,所有不显示下载完成）
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
            //设置下载标题
            request.setTitle(name)
            request.setDescription("downloading...")
            //显示在下载界面，即下载后的文件在系统下载管理里显示
            request.setVisibleInDownloadsUi(true)
            request.setDestinationUri(Uri.fromFile(file))
            //获取DownloadManager
            if (downloadManager == null) {
                downloadManager =
                    mContext.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            }
            //将下载请求加入下载队列，加入下载队列后会给该任务返回一个long型的id，通过该id可以取消任务，重启任务、获取下载的文件等等
            if (downloadManager != null) {
                downloadId = downloadManager!!.enqueue(request)
            }
            //注册广播接收者，监听下载状态
            mContext.registerReceiver(
                receiver,
                IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
            )
        }
    }

    /**
     * 广播监听下载的各个状态
     */
    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            checkStatus()
        }
    }

    init {
        downloadApk(url, name)
    }

    /**
     * 检查下载状态
     */
    private fun checkStatus() {
        val query = DownloadManager.Query()
        //通过下载的id查找
        query.setFilterById(downloadId)
        val cursor = downloadManager!!.query(query)
        if (cursor.moveToFirst()) {
            @SuppressLint("Range") val status = cursor.getInt(
                cursor.getColumnIndex(
                    DownloadManager.COLUMN_STATUS
                )
            )
            when (status) {
                DownloadManager.STATUS_PAUSED -> {}
                DownloadManager.STATUS_PENDING -> {}
                DownloadManager.STATUS_RUNNING -> {}
                DownloadManager.STATUS_SUCCESSFUL -> {
                    if (TextUtils.isEmpty(pathStr)) return
                    //下载完成安装APK
                    if (AppUtils.isAppBackground(mContext)) {
                        Toast.makeText(
                            mContext,
                            mContext.getString(R.string.app_name) + "下载完成，请到通知栏点击安装",
                            Toast.LENGTH_SHORT
                        ).show()
                        setTopApp()
                    }
                    installApk()
                    cursor.close()
                }
                DownloadManager.STATUS_FAILED -> {
                    //下载失败
                    Toast.makeText(mContext, "下载失败", Toast.LENGTH_SHORT).show()
                    cursor.close()
                    mContext.unregisterReceiver(receiver)
                }
                else -> {}
            }
        }
    }

    /**
     * 安装apk
     */
    private fun installApk() {
        setPermission(pathStr)
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        // 由于没有在Activity环境下启动Activity,设置下面的标签
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        //Android 7.0以上要使用FileProvider
        val file = File(pathStr)
        //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
        var apkUri: Uri? = null
        apkUri = try {
            FileProvider.getUriForFile(mContext, mContext.packageName + ".fileprovider", file)
        } catch (e: Exception) {
            return
        }
        //添加这一句表示对目标应用临时授权该Uri所代表的文件
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.setDataAndType(apkUri, "application/vnd.android.package-archive")
        mContext.startActivity(intent)
        (mContext as Activity).finish()
    }

    /**
     * 修改文件权限
     */
    private fun setPermission(absolutePath: String?) {
        val command = "chmod 777 $absolutePath"
        val runtime = Runtime.getRuntime()
        try {
            runtime.exec(command)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /**
     * 当本应用位于后台时，则将它切换到最前端
     */
    fun setTopApp() {
        //获取ActivityManager
        val activityManager = mContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        //获得当前运行的task(任务)
        val taskInfoList = activityManager.getRunningTasks(100)
        for (taskInfo in taskInfoList) {
            //找到本应用的 task，并将它切换到前台
            if (taskInfo.topActivity!!.packageName == mContext.packageName) {
                activityManager.moveTaskToFront(taskInfo.id, 0)
                break
            }
        }
    }


    private fun renamefile(path:String):String{

        val file = File(path)

        //  在这里获取到下载完成的文件，并进行进一步的操作
        val actualFileName = file.name

//                     对文件进行重命名或其他操作
//                     例如，如果你期望下载的是图片文件，可以根据实际的文件类型为其添加正确的后缀

//                     这里需要根据你期望的后缀类型进行相应的处理
        val desiredFileExtension = ".apk"
        val newFileName =
            "${actualFileName.substringBeforeLast(".")}$desiredFileExtension"
        val renamedFile = File(file.parent, newFileName)
        file.renameTo(renamedFile)
        return renamedFile.path
    }

}