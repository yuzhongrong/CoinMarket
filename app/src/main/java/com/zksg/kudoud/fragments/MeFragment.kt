package com.zksg.kudoud.fragments


import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.netease.lib_network.entitys.NewWalletToken
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.tencent.mmkv.MMKV
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.activitys.*
import com.zksg.kudoud.adapters.MemeWalletListdapter
import com.zksg.kudoud.callback.WalletUpdateTokensBalanceCallback
import com.zksg.kudoud.entitys.UiWalletToken
import com.zksg.kudoud.state.MeFragmentViewModel
import com.zksg.kudoud.state.SharedViewModel
import com.zksg.kudoud.utils.IntentUtils
import com.zksg.kudoud.utils.ObjectSerializationUtils
import com.zksg.kudoud.utils.manager.SimpleWallet
import com.zksg.kudoud.utils.manager.SolanaWalletManager
import com.zksg.kudoud.wallet.constants.Constants.UI_TOKENS


class MeFragment : BaseDialogFragment() {

    private var meViewModel: MeFragmentViewModel? = null
    private var sharedViewModel:SharedViewModel?=null
    var mMemeWalletListdapter:MemeWalletListdapter?=null



    override fun initViewModel() {
        meViewModel = getFragmentScopeViewModel(MeFragmentViewModel::class.java)
        sharedViewModel=getApplicationScopeViewModel(SharedViewModel::class.java)

    }



    override fun getDataBindingConfig(): DataBindingConfig {

        mMemeWalletListdapter=MemeWalletListdapter(
            context,
            meViewModel
        )
        return DataBindingConfig(R.layout.fragment_mine, BR.vm, meViewModel!!)
            .addBindingParam(BR.click, ClickProxy())
            .addBindingParam(BR.adapter,mMemeWalletListdapter!!)
            .addBindingParam(BR.listener,this.onRefresh)
    }





    override fun loadInitData() {
//        initData()
        //切换钱包走的逻辑
        sharedViewModel!!.getOneSelectWallet().observe(this){

//            var simpleWallet= SolanaWalletManager.getOneSimpleWalletFromMMKV(it.netwrokgroup,it.keyAlias)
//            if(simpleWallet!=null){
//                meViewModel!!.mSimpleWallet.set(simpleWallet)
//                meViewModel!!.show_wallet.set(true)
//            }
            Log.d("----selectWallet.observe--->","observe")
            initData()

        }

        initObsver()
        //切换钱包逻辑暂时不处理
        initData()

    }


    fun initObsver(){

        //当你点击添加删除时候会走这里
        sharedViewModel!!.getAddTokenNotify().observe(this){
            var keyAlias= sharedViewModel!!.getOneSelectWallet().value!!.keyAlias
            var tokensbytes= MMKV.mmkvWithID(UI_TOKENS).decodeBytes(keyAlias)
            var uitokens=ObjectSerializationUtils.deserializeObject(tokensbytes) as ArrayList<UiWalletToken>
            meViewModel!!.uitokenInfos.postValue(uitokens)
        }


    }



    fun initData(){
//        JsonParser.parse2TokenInfo(JsonParser.jsonString)



        //1.判断是否展示钱包页面
        var keyAlias= sharedViewModel!!.getOneSelectWallet().value!!.keyAlias
        if(TextUtils.isEmpty(keyAlias)){
            //没有选中钱包
            meViewModel!!.show_wallet.set(false)

        }else{
            //加载选中钱包
            meViewModel!!.show_wallet.set(true)
            //初始化总余额
            meViewModel!!.mWalletAmountMoney.value="0.0"
            //这个组用于展示页面基本信息
            var network=sharedViewModel!!.oneSelectWallet.value!!.netwrokgroup
            var simpleWallet= SolanaWalletManager.getOneSimpleWalletFromMMKV(network,keyAlias)
            Log.d("----sol-address--->",simpleWallet.address)
            meViewModel!!.mSimpleWallet.set(simpleWallet)
            initLoadingTokensForWallet(simpleWallet)

        }








//        var json= LocalJsonResolutionUtils.getJson(context,"memevolsimulator.json")
//        var simulators= LocalJsonResolutionUtils.JsonToObject(json, MemeCommonEntry::class.java)
//        var results= DataFilterUtils.filterNonNullName(simulators.tokens)
//        meViewModel!!.mWalletTokens.postValue(results)

        //请求tokenlist数据


    }


    fun initLoadingTokensForWallet(simpleWallet:SimpleWallet){

        //1.加载本地token列表
        var tokensbytes= MMKV.mmkvWithID(UI_TOKENS).decodeBytes(simpleWallet.keyAlias)
        var uitokens=ObjectSerializationUtils.deserializeObject(tokensbytes) as ArrayList<UiWalletToken>
         meViewModel!!.uitokenInfos.postValue(uitokens)

        //第一次进来自动刷新
        meViewModel!!.isAutoRefresh.postValue(true)

    }


    fun updateWalletBalance(){
        var simpleWallet= meViewModel!!.mSimpleWallet.get()!!
        //2.更新钱包余额
        meViewModel!!.updateWalletBalance(simpleWallet.address,object :
            WalletUpdateTokensBalanceCallback{
            override fun updateWalletTokensBalance(it: MutableList<NewWalletToken>?) {
                Log.d("----walletbalance---->",Gson().toJson(it))
                //在tokenlist中遍历去结果中找 如果找不到就不用设置balance和price
                if(it!=null){
                    var tokenlist= meViewModel!!.uitokenInfos.value!!
                    //更新token列表价格和余额
                    getBalanceAndPriceFromUpdateInfo(it,tokenlist)
                    //更新缓存
                    MMKV.mmkvWithID(UI_TOKENS).encode(simpleWallet.keyAlias,ObjectSerializationUtils.serializeObject(tokenlist))
                    //刷新列表-这里采用这种模式更好因为你更改的是引用
                    requireActivity().runOnUiThread {
                        mMemeWalletListdapter!!.notifyDataSetChanged()
                    }

                }
                finishRefresh()
            }


        })

    }



    fun finishRefresh(){
        meViewModel!!.isAutoRefresh.postValue(false)
    }
    fun getBalanceAndPriceFromUpdateInfo(results: MutableList<NewWalletToken>, uiWalletTokens: MutableList<UiWalletToken>): MutableList<UiWalletToken> {
        if (uiWalletTokens.isNotEmpty()) {
            uiWalletTokens.forEach { uiToken ->
                val matchingResult = results.find { it.mint == uiToken.mint } // 在 results 中查找与当前 uiToken 匹配的 NewWalletToken 对象
                matchingResult?.let { result ->
                    // 如果找到了匹配的结果，则更新 uiToken 的 balance 和 price 字段
                    uiToken.balance = result.balance
                    uiToken.price = result.price
                }
            }
        }
       return uiWalletTokens;
    }



    var onRefresh=object :OnRefreshListener{
        override fun onRefresh(refreshLayout: RefreshLayout) {
            updateWalletBalance()
        }

    }


    inner class ClickProxy {
        fun startCreateWallet() {
            IntentUtils.openIntent(requireContext(), Intent(requireContext(),WalleManagertActivity::class.java))
        }

        fun startAddCoin(){
            var keyAlias=meViewModel!!.mSimpleWallet.get()?.keyAlias
            var i =Intent(context,CoinManagerActivity::class.java).putExtra("keyAlias",keyAlias)
            IntentUtils.openIntent(context,i)

        }

    }


}