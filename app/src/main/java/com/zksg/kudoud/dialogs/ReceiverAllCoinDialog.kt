package com.zksg.kudoud.dialogs

import android.app.Activity
import android.graphics.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.blankj.utilcode.util.ToastUtils
import com.lxj.xpopup.core.BottomPopupView
import com.zksg.kudoud.R
import com.zksg.kudoud.utils.CopyUtils
import net.glxn.qrgen.android.QRCode
import java.util.*

class ReceiverAllCoinDialog(context: Activity, wallet:String,iconUrls:List<String>) : BottomPopupView(context) {

    private var mcontext=context
    private var mWallet=wallet
    private var miconUrls=iconUrls


    override fun onCreate() {
        super.onCreate()

        var icon=findViewById<ImageView>(R.id.icon)
        var copy=findViewById<ImageView>(R.id.copy)
        var imv_scan=findViewById<ImageView>(R.id.scan_address)
       var tv_address=findViewById<TextView>(R.id.address)
        var bt_close=findViewById<Button>(R.id.bt_close)
        bt_close.setOnClickListener { this.dismiss() }
        copy.setOnClickListener {
            CopyUtils.copyToClipboard(mcontext,mWallet)
            ToastUtils.showShort(mcontext.getString(R.string.str_copy_success))
        }
        tv_address.setText(mWallet)
        imv_scan.setImageBitmap(generateRoundedQRCode(mWallet))
        tv_address.setOnClickListener {
            CopyUtils.copyToClipboard(mcontext,mWallet)
            ToastUtils.showShort(mcontext.getString(R.string.str_copy_success))
        }


    }

    override fun getImplLayoutId(): Int {
        return R.layout.dialog_receiver_all_coin
    }


    fun generateRoundedQRCode(text: String): Bitmap {
        // 生成二维码
        val qrCode = QRCode.from(text)
            .withSize(512, 512)         // 设置二维码的大小
            .withColor(0xFF000000.toInt(), 0xFFFFFFFF.toInt()) // 设置二维码和背景的颜色
            .bitmap()

        // 创建圆角二维码
        return createRoundedBitmap(qrCode, 30f)
    }

    fun createRoundedBitmap(bitmap: Bitmap, cornerRadius: Float): Bitmap {
        val output = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)
        val paint = Paint()
        val rect = Rect(0, 0, bitmap.width, bitmap.height)
        val rectF = RectF(rect)
        paint.isAntiAlias = true
        canvas.drawARGB(0, 0, 0, 0)
        paint.color = Color.BLACK
        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, rect, rect, paint)
        return output
    }



}