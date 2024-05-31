package com.zksg.kudoud.dialogs

import android.app.Activity
import android.graphics.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.blankj.utilcode.util.ToastUtils
import com.hjq.shape.layout.ShapeRecyclerView
import com.lxj.xpopup.core.BottomPopupView
import com.zksg.kudoud.R
import com.zksg.kudoud.adapters.MemeWalletListDialogdapter
import com.zksg.kudoud.adapters.MemeWalletListdapter
import com.zksg.kudoud.adapters.binding_adapter.RecyclerViewBindingAdapter
import com.zksg.kudoud.utils.CopyUtils
import net.glxn.qrgen.android.QRCode
import java.util.*

class SelectCoin2SendDialog(context: Activity,adapter: MemeWalletListDialogdapter) : BottomPopupView(context) {

    private var madapter=adapter
    override fun onCreate() {
        super.onCreate()
        var rv=findViewById<ShapeRecyclerView>(R.id.rv)
        RecyclerViewBindingAdapter.initRecyclerViewWithLinearLayoutManager(rv,madapter,false,1,1)
       var datas=madapter.meFragmentViewModel.uitokenInfos.value
        madapter.submitList(datas)
    }

    override fun getImplLayoutId(): Int {
        return R.layout.dialog_select_coin_send
    }




}