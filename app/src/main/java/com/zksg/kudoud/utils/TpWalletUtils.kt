package com.zksg.kudoud.utils

import android.content.Context
import android.util.Log
import android.view.View
import com.netease.lib_common_ui.utils.GsonUtil
import com.netease.lib_network.constants.config
import com.tencent.mmkv.MMKV
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
        authorize.dappIcon = config.ipfs_base_url+"QmWMoYEYM5vrBh7Xyvv1izTsqumVuk5YB4GJ5xb1VPsX21"
        authorize.actionId = "11"
        authorize.memo = "MetaStore"
        TPManager.getInstance().authorize(context, authorize, object : TPListener {
            override fun onSuccess(s: String) {
                Log.d("TP-WALLET",s)
                var result= GsonUtil.fromJSON(s, TpWalletConnectResult::class.java)
                meViewModel?.account?.set(StringUtils.hideMiddleOfString(result.account,30))
                meViewModel?.account_show?.set(View.VISIBLE)
                meViewModel?.account_value?.set(result.account)
                MMKV.mmkvWithID("accounts").encode("wallet_account",result.account)


            }
            override fun onError(s: String) {

            }

            override fun onCancel(s: String) {

            }
        })


    }


}