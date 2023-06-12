package com.zksg.kudoud.utils

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.net.Uri
import android.os.Environment
import android.util.Log
import com.blankj.utilcode.util.ToastUtils
import com.kunminx.architecture.utils.Utils
import com.tencent.mmkv.MMKV
import com.zksg.kudoud.R
import com.zksg.kudoud.broadcast.DownloadBroadcastReceiver
import io.ipfs.api.IPFS
import io.ipfs.api.NamedStreamable.FileWrapper
import io.ipfs.multihash.Multihash
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream


object IPFSManager {

    private val host="43.134.110.40"
    private  val port=5001
    private  val getwallport=8080
    private var ipfs: IPFS? = null

      fun getIPFS(): IPFS?{
        if (ipfs == null || !isConnected()) {
            try {
                ipfs = IPFS(host,port)
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
        val gatewayUrl = "http://$host:$getwallport/ipfs/"
        val downloadUrl = "$gatewayUrl$cid"
        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val request = DownloadManager.Request(Uri.parse(downloadUrl))
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle(fileName)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)

        val filter = IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        val receiver: BroadcastReceiver = DownloadBroadcastReceiver()
        context.registerReceiver(receiver, filter)

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