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
import com.zksg.kudoud.callback.WalletCusTokenInfo
import com.zksg.kudoud.entitys.UiWalletToken
import com.zksg.kudoud.state.CusAddCoinActivityViewmodel
import com.zksg.kudoud.state.SharedViewModel
import com.zksg.kudoud.utils.ObjectSerializationUtils
import com.zksg.kudoud.utils.TokenConverter
import com.zksg.kudoud.wallet.constants.Constants

class CusAddCoinActivity : BaseDialogActivity() {
    var keyAlias: String? = null
    var mCusAddCoinActivityViewmodel: CusAddCoinActivityViewmodel? = null
    var mSharedViewModel:SharedViewModel?=null
    override fun initViewModel() {
        mCusAddCoinActivityViewmodel = getActivityScopeViewModel(
            CusAddCoinActivityViewmodel::class.java)

        mSharedViewModel =
            getApplicationScopeViewModel(SharedViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(
            R.layout.activity_add_cus_coin,
            BR.vm,
            mCusAddCoinActivityViewmodel!!
        ).addBindingParam(BR.click, ClickProxy())
         .addBindingParam(BR.contractNameTextWatcher, contractNameTextWatcher)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()

    }

    fun initData(){

        keyAlias = intent.getStringExtra("keyAlias")
        mCusAddCoinActivityViewmodel!!.loadingVisible.observe(this){
            if(it)showDialog() else dismissDialog()
        }


    }



    inner class ClickProxy {

        fun close() {
            finish()

        }

        fun addCoin2Local(){
            //保存到本地已添加列表

            showDialog()
            //1.先初始化本地已有的币种列表
            var tokensbytes = MMKV.mmkvWithID(Constants.UI_TOKENS).decodeBytes(keyAlias)
            var localtokens  = ObjectSerializationUtils.deserializeObject(tokensbytes) as MutableList<UiWalletToken>
            var jupToken=mCusAddCoinActivityViewmodel!!.token.value
            var result=TokenConverter.convertJubTokensToUiWalletTokens(listOf(jupToken))
            //判断是不是已经加过了
            if(!localtokens.contains(result.get(0))){
                localtokens.add(result.get(0))
                MMKV.mmkvWithID(Constants.UI_TOKENS).encode(keyAlias, ObjectSerializationUtils.serializeObject(localtokens))
                mSharedViewModel!!.requestAddToken(true)
                ToastUtils.showShort(getString(R.string.str_add_success))
                finish()
            }else{
                ToastUtils.showShort(getString(R.string.str_existed))
            }

        }



    }

    private val contractNameTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            //transfor string
            if(!TextUtils.isEmpty(s)){
                var contract=s.toString().trim()
                if(contract.length==44){//粘贴合约上来的时候去请求
                    mCusAddCoinActivityViewmodel!!.reqCusCoinInfo(contract,object:WalletCusTokenInfo{
                        override fun cusTokenInfo(jupToken: JupToken?) {
                            if(jupToken==null){
                                runOnUiThread { ToastUtils.showShort(getString(R.string.str_not_found_coin)) }
                            }else{
                                //用于保存的数据
                                mCusAddCoinActivityViewmodel!!.token.postValue(jupToken)
                                mCusAddCoinActivityViewmodel!!.symbol.set("")
                                mCusAddCoinActivityViewmodel!!.decimal.set("")
                              //用于展示的数据
                                mCusAddCoinActivityViewmodel!!.symbol.set(jupToken.symbol)
                                mCusAddCoinActivityViewmodel!!.decimal.set(jupToken.decimals.toString())
                                mCusAddCoinActivityViewmodel!!.isadd.set(true)
                            }
                        }


                    })
                }else{
                    mCusAddCoinActivityViewmodel!!.isadd.set(false)
                }
            }

        }
        override fun afterTextChanged(s: Editable) {

        }
    }









}