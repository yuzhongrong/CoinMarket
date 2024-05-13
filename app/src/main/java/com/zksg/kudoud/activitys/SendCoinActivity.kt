package com.zksg.kudoud.activitys

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import com.blankj.utilcode.util.ToastUtils
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.netease.lib_network.entitys.JupToken
import com.tencent.mmkv.MMKV
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.adapters.SendCoinNumberdapter
import com.zksg.kudoud.callback.WalletCusTokenInfo
import com.zksg.kudoud.entitys.UiWalletToken
import com.zksg.kudoud.state.CusAddCoinActivityViewmodel
import com.zksg.kudoud.state.SendCoinActivityViewmodel
import com.zksg.kudoud.state.SharedViewModel
import com.zksg.kudoud.utils.ObjectSerializationUtils
import com.zksg.kudoud.utils.TokenConverter
import com.zksg.kudoud.wallet.constants.Constants
import com.zksg.kudoud.wallet.constants.Constants.TOKEN_SOL_CONTRACT
import java.math.BigDecimal

class SendCoinActivity : BaseDialogActivity() {
    var token: UiWalletToken? = null
    var mSendCoinActivityViewmodel: SendCoinActivityViewmodel? = null
    var mSharedViewModel:SharedViewModel?=null
    var adapter:SendCoinNumberdapter?=null
    override fun initViewModel() {
        mSendCoinActivityViewmodel = getActivityScopeViewModel(
            SendCoinActivityViewmodel::class.java)

        mSharedViewModel =
            getApplicationScopeViewModel(SharedViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        adapter=SendCoinNumberdapter(this)
        return DataBindingConfig(R.layout.activity_sendcoin_input, BR.vm, mSendCoinActivityViewmodel!!)
            .addBindingParam(BR.adapter, adapter!!)
            .addBindingParam(BR.click, ClickProxy())
            .addBindingParam(BR.contractNameTextWatcher, contractNameTextWatcher)
            .addBindingParam(BR.inputCoinNumber, inputCoinNumber)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()

    }

    fun initData(){

        mSendCoinActivityViewmodel!!.numberText.observe(this){



        }

        token = intent.getSerializableExtra("token") as UiWalletToken?
        token?.let { mSendCoinActivityViewmodel!!.currentToken.set(it) }
        adapter!!.submitList(listOf("1","2","3","4","5","6","7","8","9",".","0","\u232B"))
        mSendCoinActivityViewmodel!!.loadingVisible.observe(this){
            if(it)showDialog() else dismissDialog()
        }


    }



    inner class ClickProxy {

        fun close() {
            finish()

        }

        fun sendCoinNext(){


        }

        fun actionMax(){
           var balance= mSendCoinActivityViewmodel!!.currentToken.get()!!.balance
            mSendCoinActivityViewmodel!!.numberText.postValue(balance)
        }



    }

    private val contractNameTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            //transfor string
            if(!TextUtils.isEmpty(s)){
                var contract=s.toString().trim()
                if(contract.length==44){//粘贴合约上来的时候去请求

                }else{
                    mSendCoinActivityViewmodel!!.isapass.set(false)
                }
            }

        }
        override fun afterTextChanged(s: Editable) {

        }
    }


    private val inputCoinNumber: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

       override fun afterTextChanged(s: Editable) {}
        }











}