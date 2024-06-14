package com.zksg.kudoud.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kunminx.architecture.ui.page.BaseFragment
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.tencent.mmkv.MMKV
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.adapters.CycMusicAdapter
import com.zksg.kudoud.entitys.UiWalletToken
import com.zksg.kudoud.state.CycMusicFragmentViewModel
import com.zksg.kudoud.state.ExchangeFragmentViewModel
import com.zksg.kudoud.state.MeFragmentViewModel
import com.zksg.kudoud.state.SharedViewModel
import com.zksg.kudoud.utils.ObjectSerializationUtils
import com.zksg.kudoud.utils.WalletUtils
import com.zksg.kudoud.wallet.constants.Constants.TOKEN_SOL_CONTRACT
import com.zksg.kudoud.wallet.constants.Constants.TOKEN_WIF_CONTRACT
import com.zksg.lib_api.playlist.BasicMusicInfo
import kotlinx.android.synthetic.main.fragment_exchange.*

class ExchangeFragment:BaseFragment(){
    private var  meViewModel: ExchangeFragmentViewModel?=null
    private var sharedViewModel: SharedViewModel?=null
    //TODO:tip oncreate 时候调用
    override fun initViewModel() {
        meViewModel=getFragmentScopeViewModel(ExchangeFragmentViewModel::class.java)
        sharedViewModel=getApplicationScopeViewModel(SharedViewModel::class.java)
    }



    //TODO:tip oncreateview 时候调用
    override fun getDataBindingConfig(): DataBindingConfig {
       return DataBindingConfig(R.layout.fragment_exchange,BR.vm,meViewModel!!)
           .addBindingParam(BR.quoWatcher, quoWatcher)

    }


    override fun loadInitData() {

        initObserve()
        initData()
    }


    fun initObserve(){

        meViewModel!!.from.observe(this){
            //去钱包查询余额
            updateBalance(it.mint,true)
        }

        meViewModel!!.to.observe(this){
            //去钱包查询余额
            updateBalance(it.mint,false)

        }
        //更新我的页面余额变化后 会更新这里
        sharedViewModel!!.tokenListUpdateNotify.observe(this){
            if(it){initData()}
        }



    }

    fun updateBalance(mint:String,isfrom:Boolean){
        var result= WalletUtils.getWalletUiTokenBalance(sharedViewModel!!,mint)
        if(isfrom){
            if(result!=null){
                meViewModel!!.from_wallet_amount.value=result.balance
            }
        }else{
            if(result!=null){
                meViewModel!!.to_wallet_amount.value=result.balance
            }
        }

    }

    fun  initData() {

        var keyAlias= sharedViewModel!!.getOneSelectWallet().value!!.keyAlias
        //去本地获取初始化数据 如果是null 表示第一次,给初始化数据，否则直接赋值
        var defaultfrom=UiWalletToken(TOKEN_SOL_CONTRACT,"0","9","0","SOL","Wrapped SOL","",R.mipmap.ic_solana_common)
        var from= MMKV.mmkvWithID(keyAlias+"_"+"swap").decodeBytes("from")
        if(from==null){
            meViewModel!!.from.value=defaultfrom
            //from_amount默认初始化是1
            meViewModel!!.from_amount.value=1


        }else{
            meViewModel!!.from.value=ObjectSerializationUtils.deserializeObject(from) as UiWalletToken
            //from_amount默认初始化是1
            meViewModel!!.from_amount.value=1
        }




        var defaultto=UiWalletToken(TOKEN_WIF_CONTRACT,"0","6","0","WIF","dogwifhat","https://www.dextools.io/resources/tokens/logos/solana/EKpQGSJtjMFqKZ9KQanSqYXRcF8fBopzLHYxdM65zcjm.png",0)
        var to= MMKV.mmkvWithID(keyAlias+"_"+"swap").decodeBytes("to")
        if(to==null){
            meViewModel!!.to.value=defaultto
        }else{
            meViewModel!!.to.value=ObjectSerializationUtils.deserializeObject(to) as UiWalletToken
        }




    }


    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        Log.d("---onHiddenChanged()-->","onHiddenChanged")
        //当这个fragment处于暂停状态我们要保存最后一次的from和to
        if(hidden){
            WalletUtils.saveCurrentFromTo(sharedViewModel!!,meViewModel!!.from.value!!,meViewModel!!.to.value!!)
        }
    }



    private val quoWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            if(!TextUtils.isEmpty(s)){
                Log.d("----onTextChanged11-->",s.toString())
                var amount=s.toString().trim()
                meViewModel!!.getQuo(meViewModel!!.from.value!!.mint,meViewModel!!.to.value!!.mint,amount,meViewModel!!.from.value!!.decimal.toInt())
            }else{

            }

        }
        override fun afterTextChanged(s: Editable) {
            //这里直接使用edittext的id了 不再用viewmodel知识了 这样方便
            editText.setSelection(s.length)

        }
    }






    }


