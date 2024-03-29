package com.zksg.kudoud.dialogs

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.azhon.appupdate.manager.DownloadManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.lxj.xpopup.core.BottomPopupView
import com.lxj.xpopup.core.CenterPopupView
import com.zksg.kudoud.R
import com.zksg.kudoud.adapters.CategoryAdapter_V
import com.zksg.kudoud.state.AppUploadActivityViewModel
import com.zksg.lib_api.beans.HomeItem
import com.zksg.lib_api.beans.UpgradeBean

class UpgradeVersionDialog(context: Activity,info:UpgradeBean) : CenterPopupView(context) {

    private var mcontext=context
    private var minfo=info;

    override fun onCreate() {
        super.onCreate()

        var tv_content=findViewById<TextView>(R.id.tv_content)
       var versionName=findViewById<TextView>(R.id.versionName)
        var bt_iknow=findViewById<TextView>(R.id.bt_iknow)
        var bt_upgrade=findViewById<TextView>(R.id.bt_upgrade)
        bt_iknow.setOnClickListener { this.delayDismiss(500) }
        bt_upgrade.setOnClickListener {

            val manager = DownloadManager.Builder(mcontext).run {
                apkUrl(minfo.downloadurl)
                apkName(mcontext.getString(R.string.app_name)+".apk")
                smallIcon(R.mipmap.app_logo)
                //省略一些非必须参数...
                build()
            }
            manager?.download()
            this.delayDismiss(500)
        }
        versionName.text="V"+minfo.versionname
        tv_content.text=minfo.content

    }

    override fun getImplLayoutId(): Int {
        return R.layout.dialog_upgrade_version
    }
}