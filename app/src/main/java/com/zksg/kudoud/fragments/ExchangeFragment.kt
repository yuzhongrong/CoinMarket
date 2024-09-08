package com.zksg.kudoud.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import androidx.annotation.RequiresApi
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.ToastUtils
import com.kunminx.architecture.ui.page.BaseFragment
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.lxj.xpopup.XPopup
import com.netease.lib_network.entitys.QuoPubkey58Entity
import com.netease.lib_network.entitys.SubmmitVerTxReqBodyEntity
import com.paymennt.crypto.lib.Base58
import com.tencent.mmkv.MMKV
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.activitys.MainActivity
import com.zksg.kudoud.activitys.SwapDetailActivity
import com.zksg.kudoud.callback.WalletCreateFingPrintCallback
import com.zksg.kudoud.contants.GlobalConstant
import com.zksg.kudoud.dialogs.CreateWalletfingprintDialog
import com.zksg.kudoud.entitys.SwapStateEntity
import com.zksg.kudoud.entitys.UiWalletToken
import com.zksg.kudoud.state.ExchangeFragmentViewModel
import com.zksg.kudoud.state.SharedViewModel
import com.zksg.kudoud.utils.ObjectSerializationUtils
import com.zksg.kudoud.utils.WalletUtils
import com.zksg.kudoud.wallet.api.rpc.Cluster
import com.zksg.kudoud.wallet.api.rpc.types.SolanaCommitment
import com.zksg.kudoud.wallet.api.ws.SolanaWebSocketClient
import com.zksg.kudoud.wallet.api.ws.listener.NotificationEventListener
import com.zksg.kudoud.wallet.constants.Constants.TOKEN_SOL_CONTRACT
import com.zksg.kudoud.wallet.constants.Constants.TOKEN_WIF_CONTRACT
import com.zksg.kudoud.wallet.data.jupswap.SolanaTransactionSerializer.*
import com.zksg.kudoud.wallet.utils.TweetNaclFast
import com.zksg.kudoud.widgets.CircularProgressBarCountDown
import kotlinx.android.synthetic.main.fragment_exchange.*
import java.math.BigDecimal
import java.math.RoundingMode


class ExchangeFragment:BaseFragment(){

    var handler: Handler = Handler(Looper.getMainLooper())
    val delayMillis = 800L // 1秒
    var runnable: Runnable? = null
    val minInputAmount=0.01
    var tempInputAmount="1"
    var mSolanaWebSocketClient: SolanaWebSocketClient?=null


    private var  exViewModel: ExchangeFragmentViewModel?=null
    private var sharedViewModel: SharedViewModel?=null
    //TODO:tip oncreate 时候调用
    override fun initViewModel() {
        exViewModel=getFragmentScopeViewModel(ExchangeFragmentViewModel::class.java)
        sharedViewModel=getApplicationScopeViewModel(SharedViewModel::class.java)
    }



    //TODO:tip oncreateview 时候调用
    override fun getDataBindingConfig(): DataBindingConfig {
       return DataBindingConfig(R.layout.fragment_exchange,BR.vm,exViewModel!!)
           .addBindingParam(BR.quoWatcher, quoWatcher)
           .addBindingParam(BR.click, ClickProxy())
//           .addBindingParam(BR.listener,listener)

    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun loadInitData() {
        initWebSorket()
        initObserve()
        initData()
    }


    override fun onResume() {
        super.onResume()
        //init swap state
        initSwapstate()

    }


    fun initWebSorket(){
        //订阅账号
        var solanaAccount= WalletUtils.getSolanaAccount(sharedViewModel!!,"")
         mSolanaWebSocketClient= SolanaWebSocketClient(Cluster.QUICKNODE_ws)

        mSolanaWebSocketClient?.accountSubscribe(
            solanaAccount!!.publicKey.toBase58()

            ){
            Log.d("---accountSubscribe-->",GsonUtils.toJson(it))

        }


    }


    @SuppressLint("SuspiciousIndentation")
    @RequiresApi(Build.VERSION_CODES.O)
    fun initObserve(){
        exViewModel!!.from.observe(this){
            //去钱包查询余额
            updateBalance(it.mint,true)
        }

        exViewModel!!.to.observe(this){
            //去钱包查询余额
            updateBalance(it.mint,false)

        }
        exViewModel!!.mTransaction.observe(this){

        }


        //更新我的页面余额变化后 会更新这里
        sharedViewModel!!.tokenListUpdateNotify.observe(this){

        }
        //select wallet event
        sharedViewModel!!.oneSelectWallet.observe(this){
            ToastUtils.showShort("选中钱包："+it.keyAlias)
            //更新状态
            exViewModel!!.hasSelectWallet.value=true
            //更新from和to的钱包余额
            exViewModel!!.from.value= exViewModel!!.from.value
            exViewModel!!.to.value= exViewModel!!.to.value

        }

        exViewModel!!.quosolfee.observe(this){
            //每次获取报价后再去计算
            calculaterSolBalanceAfterGuo()
        }



//        exViewModel!!.signatureOnChain.observe(this){
//
//            if(!TextUtils.isEmpty(it)){
//
//                //获取兑换 把刚刚下单的拿笔交易构造出来 在页面下方显示
//
//
//                //创建socket 监控交易状态
//
//            }
//        }

        //动态获取swap 最近一次订单状态
        exViewModel!!.mSwapGetState.observe(this){

            var pubkey58= WalletUtils.getSolanaAccount(sharedViewModel!!,"")?.publicKey?.toBase58()
            if(!TextUtils.isEmpty(pubkey58)){
                var swapJson= MMKV.mmkvWithID(GlobalConstant.MMKV_SWAP_STATE +"_"+pubkey58).decodeString(GlobalConstant.MMKV_SWAP_STATE)
                if(!TextUtils.isEmpty(swapJson)){
                    var mSwapStateEntity=GsonUtils.fromJson(swapJson, SwapStateEntity::class.java)
                    mSwapStateEntity.state=it.state
                    MMKV.mmkvWithID(GlobalConstant.MMKV_SWAP_STATE +"_"+pubkey58).encode(GlobalConstant.MMKV_SWAP_STATE, GsonUtils.toJson(mSwapStateEntity))
                    exViewModel!!.mSwapStateEntity.postValue(mSwapStateEntity)
                }
            }

        }

        sharedViewModel!!.getToExchangePageNotify().observe(this){
            //构造一个to token
//            var targetToken=UiWalletToken(TOKEN_WIF_CONTRACT,"0","6","0","WIF","dogwifhat","https://www.dextools.io/resources/tokens/logos/solana/EKpQGSJtjMFqKZ9KQanSqYXRcF8fBopzLHYxdM65zcjm.png",0)
//            exViewModel!!.to.value=targetToken
//            //构造一个from token
//            var fromToken=UiWalletToken(TOKEN_SOL_CONTRACT,"0","9","0","SOL","Wrapped SOL","",R.mipmap.ic_solana_common)
//            exViewModel!!.from.value=fromToken

            if(it!=null){
                exViewModel!!.to.value=it
            }

        }


    }

    fun updateBalance(mint:String,isfrom:Boolean){
        var result= WalletUtils.getWalletUiTokenBalance(sharedViewModel!!,mint)
        if(isfrom){
            if(result!=null){
                exViewModel!!.from_wallet_amount.value=result.balance
            }else{//not create wallet

            }
        }else{
            if(result!=null){
                exViewModel!!.to_wallet_amount.value=result.balance
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
            exViewModel!!.from.value=defaultfrom
            //from_amount默认初始化是1
            exViewModel!!.from_amount.value="1"


        }else{
            exViewModel!!.from.value=ObjectSerializationUtils.deserializeObject(from) as UiWalletToken
            //from_amount默认初始化是1
            exViewModel!!.from_amount.value="1"
        }


        var defaultto=UiWalletToken(TOKEN_WIF_CONTRACT,"0","6","0","WIF","dogwifhat","https://www.dextools.io/resources/tokens/logos/solana/EKpQGSJtjMFqKZ9KQanSqYXRcF8fBopzLHYxdM65zcjm.png",0)
        var to= MMKV.mmkvWithID(keyAlias+"_"+"swap").decodeBytes("to")
        if(to==null){
            exViewModel!!.to.value=defaultto
        }else{
            exViewModel!!.to.value=ObjectSerializationUtils.deserializeObject(to) as UiWalletToken
        }

        //获取当前选中钱包
       var select= WalletUtils.getCurrentSelectWallet(sharedViewModel!!)
        if(select!=null){//not select any wallet
            exViewModel!!.hasSelectWallet.value=true
            //get rent for wallet
            getRent()

        }else{
            exViewModel!!.hasSelectWallet.value=false
            ToastUtils.showShort(getString(R.string.str_createorselect_wallet))
        }





    }


    fun getRent(){
        var wallet= WalletUtils.getCurrentSimpleWallet(sharedViewModel!!)
        if(wallet!=null&&!TextUtils.isEmpty(wallet.address)){
            exViewModel!!.getRentForAccount(wallet.address)
        }
    }



    fun calculaterSolBalance(){
        //spl-token
        if(!exViewModel!!.from.value!!.mint.equals(TOKEN_SOL_CONTRACT)){
            exViewModel!!.from_amount.value=exViewModel!!.from_wallet_amount.value
        }else {//sol
            //余额-(账号租金+转出的sol+gas) 必须大于0
            //获取租金
            var rent = exViewModel!!.AccountRent.get()
            //因为是点击全部所以这里在一开始没有办法获取报价后的gas 所以这里只能给个 预留的比例 100个sol大概预留0.06个 阀值+- 0.02
            var pregas = BigDecimal(exViewModel!!.from_wallet_amount.value).multiply(BigDecimal(0.0008))
            Log.d("-----pregas--->", pregas.toPlainString())
            // 钱包余额
            var balance = exViewModel!!.from_wallet_amount.value

            //计算支付所需sol
            var minHolderBalance = BigDecimal(rent).add(pregas)
            Log.d("-----holderbalance--->", minHolderBalance.toPlainString())
            //计算最大可转出多少
            var maxpay = BigDecimal(balance).subtract(minHolderBalance).toDouble()
            Log.d("-----maxpay--->", maxpay.toString())
            if (maxpay < 0.0) {
                exViewModel!!.insufficient_sol_balance.value = false
                ToastUtils.showShort(getString(R.string.str_balance_not_value))
                return
            }
            else {

                exViewModel!!.insufficient_sol_balance.value = true
                exViewModel!!.from_amount.value = maxpay.toString()
                tempInputAmount=maxpay.toString()

            }

        }


    }

    fun calculaterSolBalanceAfterGuo(){
        //获取租金
        var rent = exViewModel!!.AccountRent.get()
        Log.d("---rent-->",rent.toString())
        //获取交易网络费用
        var gas = exViewModel!!.quosolfee.value
        Log.d("---gas-->",gas.toString())
        //输入框需要兑换多少sol
        var needswap=if(exViewModel!!.from.value!!.mint.equals(TOKEN_SOL_CONTRACT)){tempInputAmount}else{"0"}
        Log.d("---needswap-->",needswap.toString())
        // 钱包余额
        var balance = exViewModel!!.from_wallet_amount.value
        Log.d("---balance-->",balance.toString())
        var amount_sol=BigDecimal(rent).add(BigDecimal(gas)).add(BigDecimal(needswap))
        Log.d("---amount_sol-->",amount_sol.toString())
        exViewModel!!.insufficient_sol_balance.value = BigDecimal(balance).toDouble()>=amount_sol.toDouble()

    }
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        Log.d("---onHiddenChanged()-->","onHiddenChanged")
        //当这个fragment处于暂停状态我们要保存最后一次的from和to
        if(hidden){
            WalletUtils.saveCurrentFromTo(sharedViewModel!!,exViewModel!!.from.value!!,exViewModel!!.to.value!!)
        }else{
            exViewModel!!.from.value= exViewModel!!.from.value
            exViewModel!!.to.value= exViewModel!!.to.value
        }



    }



    private fun initSwapstate(){

        //根据swap的txid获取状态如何是process则显示,相反不显示
        var pubkey58= WalletUtils.getSolanaAccount(sharedViewModel!!,"")?.publicKey?.toBase58()
        if(TextUtils.isEmpty(pubkey58)){
            return
        }
        var swapJson= MMKV.mmkvWithID(GlobalConstant.MMKV_SWAP_STATE +"_"+pubkey58).decodeString(GlobalConstant.MMKV_SWAP_STATE)
        if(swapJson!=null&&!TextUtils.isEmpty(swapJson)){//如果存在 就分3种情况processed|confirmed|fail
            var mSwapStateEntity=GsonUtils.fromJson(swapJson, SwapStateEntity::class.java)
            Log.d("-----mSwapStateEntity---->",swapJson)
            if(mSwapStateEntity.state.equals("processed")){

                //显示布局
//                exViewModel!!.mSwapStateEntityShow.set(true)
                exViewModel!!.mSwapStateEntity.postValue(mSwapStateEntity)
                //初始化需要请求一次
                exViewModel!!.getSwapstate(mSwapStateEntity.txId)

            }else{
//                exViewModel!!.mSwapStateEntityShow.set(false)
                exViewModel!!.mSwapStateEntity.postValue(mSwapStateEntity)

            }
        }else{//初始第一次没有

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

                    var amount=s.toString().trim()
                    Log.d("----onTextChanged11-->",amount)
                    // 将字符串转换为 BigDecimal 保留小数点后6位
                    val decimalAmount = BigDecimal(amount)
                    val roundedAmount = decimalAmount.setScale(6, RoundingMode.HALF_UP).toPlainString()
                    Log.d("----roundedAmount-->",roundedAmount)
                    tempInputAmount=amount
                    exViewModel!!.getQuo(exViewModel!!.from.value!!.mint,exViewModel!!.to.value!!.mint,roundedAmount,exViewModel!!.from.value!!.decimal.toInt())
                }

            }

            // 安排新的任务在指定延迟后执行
            handler.postDelayed(runnable!!, delayMillis)

        }
    }


    inner class ClickProxy {
        fun allAction(){//点击全部按钮
            //if has the select wallet
            if(exViewModel!!.hasSelectWallet!!.value==false){
                ToastUtils.showShort(getString(R.string.str_createorselect_wallet))
                return
            }

            calculaterSolBalance()




            }


        fun swapPlaces(){
            //1.先获取from 的uitoken
            var from=exViewModel!!.from.value!!
            //2.再获取to的uitoken
            var to=exViewModel!!.to.value!!
            //3.然后把 from 的值给to ，to的值给from
            exViewModel!!.from.value=to
            exViewModel!!.to.value=from
            //4.最后把from_amount input输入框的值设置成初始化为1
            exViewModel!!.from_amount.value="1"
            exViewModel!!.startAnimation.set(true)
            handler.postDelayed({ exViewModel!!.startAnimation.set(false)},1000)

        }
        
        


        fun startExDetail(){
            var intent= Intent(this@ExchangeFragment.requireActivity(),SwapDetailActivity::class.java)
            intent.putExtra("from",exViewModel!!.from.value)
            intent.putExtra("to",exViewModel!!.to.value)
            intent.putExtra("fromamount",tempInputAmount)
            startActivity(intent)
        }


        }


    var listener= CircularProgressBarCountDown.OnCountDownFinishListener {
        Log.d("----count down--->",tempInputAmount)
        //还原标识
//        exViewModel!!.startCirc.set(false)
//        exViewModel!!.getQuo(exViewModel!!.from.value!!.mint,exViewModel!!.to.value!!.mint,tempInputAmount,exViewModel!!.from.value!!.decimal.toInt())
    }


    override fun onDestroy() {
        super.onDestroy()
//        mSolanaWebSocketClient?.accountUnsubscribe( WalletUtils.getSolanaAccount(sharedViewModel!!,"")!!.publicKey.toBase58())
    }







    }
