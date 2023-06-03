package com.zksg.kudoud.utils

import android.content.Context
import android.util.Log
import com.netease.lib_common_ui.utils.GsonUtil
import com.tokenpocket.opensdk.base.TPListener
import com.tokenpocket.opensdk.base.TPManager
import com.tokenpocket.opensdk.simple.model.Authorize
import com.tokenpocket.opensdk.simple.model.Blockchain
import com.zksg.kudoud.beans.TpWalletConnectResult
import com.zksg.kudoud.state.MeFragmentViewModel

object TpWalletUtils {
    fun loginWallet(context: Context?, meViewModel: MeFragmentViewModel?) {

        val authorize = Authorize()
        //已废弃
        //authorize.setBlockchain(CHAIN);
        //标识链
        //已废弃
        //authorize.setBlockchain(CHAIN);
        //标识链
        val blockchains: MutableList<Blockchain> = ArrayList()
        blockchains.add(Blockchain("ethereum", "56"))
        authorize.blockchains = blockchains

        authorize.dappName = "MetaStore"
        authorize.dappIcon = "https://eosknights.io/img/icon.png"
        authorize.actionId = "11"
        authorize.memo = "MetaStore"
        TPManager.getInstance().authorize(context, authorize, object : TPListener {
            override fun onSuccess(s: String) {
                Log.d("TP-WALLET",s)
                var result= GsonUtil.fromJSON(s, TpWalletConnectResult::class.java)
                meViewModel?.account?.set(result.account)
            }
            override fun onError(s: String) {
            }

            override fun onCancel(s: String) {
            }
        })


    }


}