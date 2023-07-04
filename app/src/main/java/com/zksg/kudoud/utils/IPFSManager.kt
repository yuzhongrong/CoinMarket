package com.zksg.kudoud.utils

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import com.blankj.utilcode.util.ToastUtils
import com.kunminx.architecture.utils.Utils
import com.tencent.mmkv.MMKV
import com.zksg.kudoud.R
import com.netease.lib_network.constants.config.*
import io.ipfs.api.IPFS
import io.ipfs.api.NamedStreamable.FileWrapper
import io.ipfs.multihash.Multihash
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.util.*


object IPFSManager {

    private var ipfs: IPFS? = null

      fun getIPFS(): IPFS?{
        if (ipfs == null || !isConnected()) {
            try {
                ipfs = IPFS(host,ipfs_port)
            }catch (e:Exception){
                ipfs=null
                ToastUtils.showShort(Utils.getApp().getString(R.string.str_server_error))
            }

        }
        return ipfs
    }

     fun uploadFile( filePath: String): String{
//        val file = NamedStreamable.ByteArrayWrapper(filename, data)
         val file = FileWrapper(File(filePath))
         val result = getIPFS()?.add(file)?.get(0)
         ipfs?.pin?.add(result?.hash) //只有pin才能固定资源 方便加节点的时候直接copy上一个节点
         val metadata: MutableMap<String, Any> = HashMap()
//         metadata["name"] = "XUIDemo.apk"
//         metadata["author"] = "John Doe" // 将元数据转换为字节数组

//         var data = Gson().toJson(metadata)
         // 上传包含元数据的数据
//         var node = ipfs?.dag?.put(data.encodeToByteArray())
//         Log.d("--cid---->",node?.hash.toString())
//         Log.d("--cid1---->",result?.hash.toString())


       return result?.hash.toString()
    }

     fun getFile(hash: String): ByteArray {
        val cid = Multihash.fromBase58(hash)
        val inputStream = getIPFS()?.catStream(cid)
        val outputStream = ByteArrayOutputStream()
        val buffer = ByteArray(1024)
        var bytesRead: Int
        while (inputStream?.read(buffer).also { bytesRead = it!! } != -1) {
            outputStream.write(buffer, 0, bytesRead)
        }
      return  outputStream.toByteArray()
    }


    fun downloadFileWithDownloadManager(context: Context, cid: String, fileName: String):Long {
        val gatewayUrl = ipfs_base_url
        val downloadUrl = "$gatewayUrl$cid"
        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val request = DownloadManager.Request(Uri.parse(downloadUrl))
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
            .setTitle(fileName)
            .setDestinationUri(Uri.fromFile(File(fileName)))
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)

//        val filter = IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
//        val receiver: BroadcastReceiver = DownloadBroadcastReceiver()
//        context.registerReceiver(receiver, filter)

         var id=downloadManager.enqueue(request)
        //save the id to local
        Log.d("----download-id-->",id.toString())
        MMKV.mmkvWithID("DownloadIds").encode(id.toString(),fileName)
        return id

    }





    //big file to read

     fun readFileAsByteArray(filePath: String): ByteArray? {
        val file = File(filePath)
        val inputStream = FileInputStream(file)
        val outputStream = ByteArrayOutputStream()

        val buffer = ByteArray(1024)
        var bytesRead: Int
        try {
            while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                outputStream.write(buffer, 0, bytesRead)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            inputStream.close()
            outputStream.close()
        }

       return outputStream.toByteArray()
    }



    private  fun isConnected(): Boolean {
        return try {
            val localPeerId = ipfs?.id()?.get("ID").toString()
            val peerId = Multihash.fromBase58(localPeerId)
            val pingResult = ipfs?.ping(peerId)
            pingResult!!.containsKey("Success")
        } catch (e: Exception) {
            false
        }
    }



}