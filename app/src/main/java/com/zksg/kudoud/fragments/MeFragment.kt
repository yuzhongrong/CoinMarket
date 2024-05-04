package com.zksg.kudoud.fragments


import android.content.Intent
import android.text.TextUtils
import android.util.Log
import com.blankj.utilcode.util.ThreadUtils.runOnUiThread
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.tencent.mmkv.MMKV
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.activitys.*
import com.zksg.kudoud.adapters.MemeCommonWalletListdapter
import com.zksg.kudoud.callback.WalletSolBalanceCallback
import com.zksg.kudoud.entitys.UiWalletToken
import com.zksg.kudoud.jupswap.utils.JsonParser
import com.zksg.kudoud.state.MeFragmentViewModel
import com.zksg.kudoud.state.SharedViewModel
import com.zksg.kudoud.utils.IntentUtils
import com.zksg.kudoud.utils.ObjectSerializationUtils
import com.zksg.kudoud.utils.manager.SimpleWallet
import com.zksg.kudoud.utils.manager.SolanaWalletManager


class MeFragment : BaseDialogFragment() {

    private var meViewModel: MeFragmentViewModel? = null
    private var sharedViewModel:SharedViewModel?=null


    override fun initViewModel() {
        meViewModel = getFragmentScopeViewModel(MeFragmentViewModel::class.java)
        sharedViewModel=getApplicationScopeViewModel(SharedViewModel::class.java)

    }



    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_mine, BR.vm, meViewModel!!)
            .addBindingParam(BR.click, ClickProxy())
            .addBindingParam(BR.adapter, MemeCommonWalletListdapter(context,meViewModel))
    }




    override fun loadInitData() {
        initData()
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

    }


    fun initData(){
//        JsonParser.parse2TokenInfo(JsonParser.jsonString)

//        meViewModel!!.loadingVisible.observe(this){ if(it)showDialog()else dismissDialog()}


        //1.判断是否展示钱包页面
        var keyAlias= sharedViewModel!!.getOneSelectWallet().value!!.keyAlias
        if(TextUtils.isEmpty(keyAlias)){
            //没有选中钱包
            meViewModel!!.show_wallet.set(false)

        }else{
            //加载选中钱包
            meViewModel!!.show_wallet.set(true)
            //这个组用于展示页面基本信息
            var network=sharedViewModel!!.oneSelectWallet.value!!.netwrokgroup
            var simpleWallet= SolanaWalletManager.getOneSimpleWalletFromMMKV(network,keyAlias)
            Log.d("----sol-address--->",simpleWallet.address)
            meViewModel!!.mSimpleWallet.set(simpleWallet)
            //获取钱吧meta数据
//            meViewModel!!.getWalletTokens("5eFsFYRrULZVTfvqGmEYE2aETpGF4V6bfReTQJ69L7qY")

            initSol(simpleWallet)
            initOtherTokenList(simpleWallet)



        }








//        var json= LocalJsonResolutionUtils.getJson(context,"memevolsimulator.json")
//        var simulators= LocalJsonResolutionUtils.JsonToObject(json, MemeCommonEntry::class.java)
//        var results= DataFilterUtils.filterNonNullName(simulators.tokens)
//        meViewModel!!.mWalletTokens.postValue(results)

        //请求tokenlist数据


    }


    fun initSol(simpleWallet:SimpleWallet){
        var uiTokenlist=meViewModel!!.uitokenInfos.get()!!
        if(uiTokenlist.size==0){
            uiTokenlist.add(0,simpleWallet.defaultsol)
        }else{
            uiTokenlist.set(0,simpleWallet.defaultsol)
        }

        meViewModel!!.uitokenInfos.set(uiTokenlist)

        //获取钱包SOL余额
        meViewModel!!.getWalletSolBalance(simpleWallet.address,object:WalletSolBalanceCallback{
            override fun walletSolUpdate(balance: String?) {
                if(TextUtils.isEmpty(balance)||balance.equals("0"))return
                requireActivity().runOnUiThread {
                    var olddatas= meViewModel!!.uitokenInfos.get()!!
                    var newdatas=olddatas.apply {
                        set(0,UiWalletToken(olddatas.get(0).mint,balance,olddatas.get(0).decimal,olddatas.get(0).price,olddatas.get(0).symbol,olddatas.get(0).imageUrl,olddatas.get(0).resId))
                    }
//                        var newwallet= SimpleWallet(oldWallet.keyAlias,oldWallet.network,oldWallet.name,oldWallet.address,newTokens)
                    meViewModel!!.uitokenInfos.set(newdatas)

                }

            }

        })

    }

    //这里实现除了sol以外的其他tOKEN展示 主要是处理tokendinfos
    fun initOtherTokenList(simpleWallet: SimpleWallet){
        //从本地拿这个钱包address<-->tokeninfos
        var tokens= MMKV.mmkvWithID(simpleWallet.keyAlias).decodeBytes("tokens",null)
        if(tokens==null){ //除了默认token之外,没有其他token了

        }else{
           var tokens= ObjectSerializationUtils.deserializeObject(tokens) as MutableList<UiWalletToken>
           var newtokens= meViewModel!!.uitokenInfos.get()!!.apply { addAll(tokens) }
            meViewModel!!.uitokenInfos.set(newtokens)
        }


    }





    inner class ClickProxy {
        fun startCreateWallet() {
            IntentUtils.openIntent(requireContext(), Intent(requireContext(),WalleManagertActivity::class.java))
        }

    }


}