package com.zksg.kudoud.fragments

import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.core.os.postDelayed
import com.blankj.utilcode.util.ToastUtils
import com.kunminx.architecture.ui.page.BaseFragment
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.tencent.mmkv.MMKV
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.callback.QuoGasCallback
import com.zksg.kudoud.callback.TokenInfo
import com.zksg.kudoud.entitys.UiWalletToken
import com.zksg.kudoud.state.ExchangeFragmentViewModel
import com.zksg.kudoud.state.SharedViewModel
import com.zksg.kudoud.utils.ObjectSerializationUtils
import com.zksg.kudoud.utils.WalletUtils
import com.zksg.kudoud.wallet.constants.Constants.TOKEN_SOL_CONTRACT
import com.zksg.kudoud.wallet.constants.Constants.TOKEN_WIF_CONTRACT
import kotlinx.android.synthetic.main.fragment_exchange.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

class ExchangeFragment:BaseFragment(){

    var handler: Handler = Handler(Looper.getMainLooper())
    val delayMillis = 1000L // 1秒
    var runnable: Runnable? = null
    val minInputAmount=0.01
    var tempInputAmount="1"


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
           .addBindingParam(BR.click, ClickProxy())

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

        }
        //select wallet event
        sharedViewModel!!.oneSelectWallet.observe(this){
            ToastUtils.showShort("选中钱包："+it.keyAlias)
            //更新状态
            meViewModel!!.hasSelectWallet.value=true
            //更新from和to的钱包余额
            meViewModel!!.from.value= meViewModel!!.from.value
            meViewModel!!.to.value= meViewModel!!.to.value

        }

        meViewModel!!.quosolfee.observe(this){
            //每次获取报价后再去计算
            calculaterSolBalanceAfterGuo()
        }

    }

    fun updateBalance(mint:String,isfrom:Boolean){
        var result= WalletUtils.getWalletUiTokenBalance(sharedViewModel!!,mint)
        if(isfrom){
            if(result!=null){
                meViewModel!!.from_wallet_amount.value=result.balance
            }else{//not create wallet

            }
        }else{
            if(result!=null){
                meViewModel!!.to_wallet_amount.value=result.balance
            }else{ //not create wallet

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
            meViewModel!!.from_amount.value="1"


        }else{
            meViewModel!!.from.value=ObjectSerializationUtils.deserializeObject(from) as UiWalletToken
            //from_amount默认初始化是1
            meViewModel!!.from_amount.value="1"
        }


        var defaultto=UiWalletToken(TOKEN_WIF_CONTRACT,"0","6","0","WIF","dogwifhat","https://www.dextools.io/resources/tokens/logos/solana/EKpQGSJtjMFqKZ9KQanSqYXRcF8fBopzLHYxdM65zcjm.png",0)
        var to= MMKV.mmkvWithID(keyAlias+"_"+"swap").decodeBytes("to")
        if(to==null){
            meViewModel!!.to.value=defaultto
        }else{
            meViewModel!!.to.value=ObjectSerializationUtils.deserializeObject(to) as UiWalletToken
        }

        //获取当前选中钱包
       var select= WalletUtils.getCurrentSelectWallet(sharedViewModel!!)
        if(select!=null){//not select any wallet
            meViewModel!!.hasSelectWallet.value=true
            //get rent for wallet
            getRent()

        }else{
            meViewModel!!.hasSelectWallet.value=false
            ToastUtils.showShort(getString(R.string.str_createorselect_wallet))
        }


    }


    fun getRent(){
        var wallet= WalletUtils.getCurrentSimpleWallet(sharedViewModel!!)
        if(wallet!=null&&!TextUtils.isEmpty(wallet.address)){
            meViewModel!!.getRentForAccount(wallet.address)
        }
    }



    fun calculaterSolBalance(){
        //spl-token
        if(!meViewModel!!.from.value!!.mint.equals(TOKEN_SOL_CONTRACT)){
            meViewModel!!.from_amount.value=meViewModel!!.from_wallet_amount.value
        }else {//sol
            //余额-(账号租金+转出的sol+gas) 必须大于0
            //获取租金
            var rent = meViewModel!!.AccountRent.get()
            //因为是点击全部所以这里在一开始没有办法获取报价后的gas 所以这里只能给个 预留的比例 100个sol大概预留0.06个 阀值+- 0.02
            var pregas = BigDecimal(meViewModel!!.from_wallet_amount.value).multiply(BigDecimal(0.0008))
            Log.d("-----pregas--->", pregas.toPlainString())
            // 钱包余额
            var balance = meViewModel!!.from_wallet_amount.value

            //计算支付所需sol
            var minHolderBalance = BigDecimal(rent).add(pregas)
            Log.d("-----holderbalance--->", minHolderBalance.toPlainString())
            //计算最大可转出多少
            var maxpay = BigDecimal(balance).subtract(minHolderBalance).toDouble()
            Log.d("-----maxpay--->", maxpay.toString())
            if (maxpay < 0.0) {
                meViewModel!!.insufficient_sol_balance.value = false
                ToastUtils.showShort(getString(R.string.str_balance_not_value))
                return
            }
            else {

                meViewModel!!.insufficient_sol_balance.value = true
                meViewModel!!.from_amount.value = maxpay.toString()
                tempInputAmount=maxpay.toString()

            }
        }


    }

    fun calculaterSolBalanceAfterGuo(){
        //获取租金
        var rent = meViewModel!!.AccountRent.get()
        Log.d("---rent-->",rent.toString())
        //获取交易网络费用
        var gas = meViewModel!!.quosolfee.value
        Log.d("---gas-->",gas.toString())
        //输入框需要兑换多少sol
        var needswap=if(meViewModel!!.from.value!!.mint.equals(TOKEN_SOL_CONTRACT)){tempInputAmount}else{"0"}
        Log.d("---needswap-->",needswap.toString())
        // 钱包余额
        var balance = meViewModel!!.from_wallet_amount.value
        Log.d("---balance-->",balance.toString())
        var amount_sol=BigDecimal(rent).add(BigDecimal(gas)).add(BigDecimal(needswap))
        Log.d("---amount_sol-->",amount_sol.toString())
        meViewModel!!.insufficient_sol_balance.value = BigDecimal(balance).toDouble()>=amount_sol.toDouble()

    }
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        Log.d("---onHiddenChanged()-->","onHiddenChanged")
        //当这个fragment处于暂停状态我们要保存最后一次的from和to
        if(hidden){
            WalletUtils.saveCurrentFromTo(sharedViewModel!!,meViewModel!!.from.value!!,meViewModel!!.to.value!!)
        }else{
            meViewModel!!.from.value= meViewModel!!.from.value
            meViewModel!!.to.value= meViewModel!!.to.value
        }
    }



    private val quoWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

        }
        override fun afterTextChanged(s: Editable) {

            // 检查是否有前导零且长度大于1且不包含小数点
            if (s.isNotEmpty() && s.startsWith("0") && s.length > 1 && !s.contains(".")) {
                // 去掉前导零
                editText.setText(s.toString().replaceFirst("^0+".toRegex(), ""))

            }



            //这里直接使用edittext的id了 不再用viewmodel知识了 这样方便
            editText.setSelection(editText.text.length)


            // 取消之前的任务（如果有）
            runnable?.let { handler.removeCallbacks(it) }

            // 创建一个新的任务
            runnable = Runnable {
                // 在这里发送网络请求
                if(!TextUtils.isEmpty(s)){
                    Log.d("----onTextChanged11-->",s.toString())
                    var amount=s.toString().trim()
                    tempInputAmount=amount
                    meViewModel!!.getQuo(meViewModel!!.from.value!!.mint,meViewModel!!.to.value!!.mint,amount,meViewModel!!.from.value!!.decimal.toInt())
                }

            }

            // 安排新的任务在指定延迟后执行
            handler.postDelayed(runnable!!, delayMillis)



        }
    }


    inner class ClickProxy {
        fun allAction(){//点击全部按钮
            //if has the select wallet
            if(meViewModel!!.hasSelectWallet!!.value==false){
                ToastUtils.showShort(getString(R.string.str_createorselect_wallet))
                return
            }

            calculaterSolBalance()




            }


        fun swapPlaces(){
            //1.先获取from 的uitoken
            var from=meViewModel!!.from.value!!
            //2.再获取to的uitoken
            var to=meViewModel!!.to.value!!
            //3.然后把 from 的值给to ，to的值给from
            meViewModel!!.from.value=to
            meViewModel!!.to.value=from
            //4.最后把from_amount input输入框的值设置成初始化为1
            meViewModel!!.from_amount.value="1"
            meViewModel!!.startAnimation.set(true)
            handler.postDelayed({ meViewModel!!.startAnimation.set(false)},1000)

        }

        }

    }










