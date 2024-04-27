package com.zksg.kudoud.fragments


import android.content.Intent
import android.text.TextUtils
import com.kunminx.architecture.ui.page.BaseFragment
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.tencent.mmkv.MMKV
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.activitys.*
import com.zksg.kudoud.adapters.MemeCommonWalletListdapter
import com.zksg.kudoud.contants.CoinType
import com.zksg.kudoud.state.MeFragmentViewModel
import com.zksg.kudoud.state.SharedViewModel
import com.zksg.kudoud.utils.DataFilterUtils
import com.zksg.kudoud.utils.IntentUtils
import com.zksg.kudoud.utils.LocalJsonResolutionUtils
import com.zksg.kudoud.utils.ObjectSerializationUtils
import com.zksg.kudoud.utils.manager.SimpleWallet
import com.zksg.kudoud.utils.manager.SolanaWalletManager
import com.zksg.lib_api.beans.MemeCommonEntry


class MeFragment : BaseFragment() {

    private var meViewModel: MeFragmentViewModel? = null
    private var sharedViewModel:SharedViewModel?=null


    override fun initViewModel() {
        meViewModel = getFragmentScopeViewModel(MeFragmentViewModel::class.java)
        sharedViewModel=getApplicationScopeViewModel(SharedViewModel::class.java)

    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_mine, BR.vm, meViewModel!!)
            .addBindingParam(BR.click, ClickProxy())
            .addBindingParam(BR.adapter, MemeCommonWalletListdapter(context))
    }




    override fun loadInitData() {

        initData()
    }




    fun initData(){

        var json= LocalJsonResolutionUtils.getJson(context,"memevolsimulator.json")
        var simulators= LocalJsonResolutionUtils.JsonToObject(json, MemeCommonEntry::class.java)
        var results= DataFilterUtils.filterNonNullName(simulators.tokens)
        meViewModel!!.mWalletTokens.postValue(results)

        //1.判断是否展示钱包页面
        var keyAlias= MMKV.mmkvWithID("currentWallet").decodeString("keyAlias","")
        if(TextUtils.isEmpty(keyAlias)){
            //没有选中钱阿波
            meViewModel!!.show_wallet.set(false)

        }else{
            //加载选中钱包
            meViewModel!!.show_wallet.set(true)
            //这个组用于展示页面基本信息
            var network=MMKV.mmkvWithID("currentWallet").decodeString("netwrokgroup",CoinType.SOLANA.key)
            var simpleWallet= SolanaWalletManager.getOneSimpleWalletFromMMKV(network,keyAlias)
            meViewModel!!.mSimpleWallet.set(simpleWallet)

        }


        sharedViewModel!!.selectWallet.observe(this){

            var simpleWallet= SolanaWalletManager.getOneSimpleWalletFromMMKV(it.netwrokgroup,it.keyAlias)
            meViewModel!!.mSimpleWallet.set(simpleWallet)
            if(simpleWallet!=null)meViewModel!!.show_wallet.set(true)

        }


    }





    inner class ClickProxy {
        fun startCreateWallet() {
            IntentUtils.openIntent(requireContext(), Intent(requireContext(),WalleManagertActivity::class.java))
        }

    }


}