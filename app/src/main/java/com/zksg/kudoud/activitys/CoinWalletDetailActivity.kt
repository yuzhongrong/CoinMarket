package com.zksg.kudoud.activitys

import android.content.Intent
import android.os.Bundle
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.lxj.xpopup.XPopup
import com.netease.lib_network.entitys.TransationHistoryEntity
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.adapters.TransDetailHistorysrdapter
import com.zksg.kudoud.dialogs.ReceiverCoinDialog
import com.zksg.kudoud.entitys.UiWalletToken
import com.zksg.kudoud.state.CoinWalletDetailActivityViewModel
import com.zksg.kudoud.state.SharedViewModel
import com.zksg.kudoud.utils.IntentUtils
import com.zksg.kudoud.wallet.constants.Constants
import com.zksg.kudoud.wallet.constants.Constants.TOKEN_SOL_CONTRACT

class CoinWalletDetailActivity : BaseDialogActivity() {
    var sol: UiWalletToken? = null
    var viewModel: CoinWalletDetailActivityViewModel? = null
    var item: UiWalletToken? = null
    var wallet: String? = ""
    private var mSharedViewModel: SharedViewModel? = null
    override fun initViewModel() {
        mSharedViewModel = getApplicationScopeViewModel(SharedViewModel::class.java)
        viewModel = getActivityScopeViewModel(
            CoinWalletDetailActivityViewModel::class.java
        )
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_wallet_coin_detail, BR.vm, viewModel!!)
            .addBindingParam(BR.click, ClickProxy())
            .addBindingParam(BR.historysAdaper, TransDetailHistorysrdapter(this))
            .addBindingParam(BR.loadmore,loadmore)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }

    private fun initData() {
        viewModel!!.loadingVisible.observe(this) { it: Boolean -> if (it) showDialog() else dismissDialog() }
        viewModel!!.historys.observe(this) { it: List<TransationHistoryEntity?>? ->
            if (it != null && it.size == 0) {
                viewModel!!.rv_empter.set(true)
            } else {
                viewModel!!.rv_empter.set(false)
            }
        }
        mSharedViewModel!!.fristPageClose.observe(this) { aBoolean: Boolean? -> finish() }
        item = intent.extras!!["token"] as UiWalletToken?
        sol = intent.extras!!["sol"] as UiWalletToken?
        wallet = intent.getStringExtra("wallet")
        viewModel!!.walletAddress.set(wallet)
        if (item != null) {
            viewModel!!.currentToken.set(item)
        }

        //获取sol历史记录
        if (item!!.mint == Constants.TOKEN_SOL_CONTRACT) {
            viewModel!!.getSolHistorys(wallet!!,"",true)
        } else { //获取spl历史记录
            viewModel!!.getSplHistorys(wallet!!, item!!.mint, "",true)
        }
    }

    inner class ClickProxy {
        fun close() {
            finish()
        }

        fun startSendIntent() {
            IntentUtils.openIntent(
                this@CoinWalletDetailActivity,
                Intent(
                    this@CoinWalletDetailActivity,
                    SendCoinActivity::class.java
                ).putExtra("token", item).putExtra("sol", sol)
            )
        }

        fun start2ReceiverDialog() {
            XPopup.Builder(this@CoinWalletDetailActivity)
                .dismissOnTouchOutside(true)
                .dismissOnBackPressed(true)
                .asCustom(ReceiverCoinDialog(this@CoinWalletDetailActivity, item!!, wallet!!))
                .show()
        }
    }


    var loadmore=object: OnLoadMoreListener {

        override fun onLoadMore(refreshLayout: RefreshLayout) {
            if(item!!.mint.equals(TOKEN_SOL_CONTRACT)){
                viewModel?.getSolHistorys(wallet!!,viewModel!!.next.value!!,false)
            }else{
                viewModel?.getSplHistorys(wallet!!,item!!.mint,viewModel!!.next.value!!,false)
            }

        }

    }

}