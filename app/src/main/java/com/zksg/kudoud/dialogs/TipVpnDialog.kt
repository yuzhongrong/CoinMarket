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
import com.suke.widget.SwitchButton
import com.tencent.mmkv.MMKV
import com.zksg.kudoud.R
import com.zksg.kudoud.adapters.CategoryAdapter_V
import com.zksg.kudoud.state.AppUploadActivityViewModel
import com.zksg.kudoud.state.HomeFragmentViewModel
import com.zksg.lib_api.beans.HomeItem
import com.zksg.lib_api.beans.UpgradeBean

class TipVpnDialog(context: Activity,homeViewModel: HomeFragmentViewModel) : CenterPopupView(context) {

    private var mcontext=context
    private var isCloseTip=false
    private var homeViewModel=homeViewModel


    override fun onCreate() {
        super.onCreate()
        var bt_iknow=findViewById<TextView>(R.id.bt_iknow)
        var switch_button=findViewById<SwitchButton>(R.id.switch_button)
        switch_button.setOnCheckedChangeListener { view, isChecked -> isCloseTip = isChecked }
        bt_iknow.setOnClickListener {
             if(isCloseTip){//记录 不再提示开关
                 MMKV.mmkvWithID("switchs").encode("tip_vpn",true)
             }
            this.delayDismiss(500)
//            homeViewModel?.getHomeDatas()

        }

    }

    override fun getImplLayoutId(): Int {
        return R.layout.dialog_tip_vpn
    }
}