package com.zksg.kudoud.state

import android.annotation.SuppressLint
import android.os.Build
import android.util.Base64
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.ThreadUtils.runOnUiThread
import com.blankj.utilcode.util.ToastUtils
import com.google.gson.Gson
import com.kunminx.architecture.domain.message.MutableResult
import com.kunminx.architecture.utils.Utils
import com.netease.lib_common_ui.utils.GsonUtil
import com.paymennt.crypto.bip32.wallet.AbstractWallet
import com.zksg.kudoud.wallet.api.rpc.Cluster
import com.zksg.kudoud.wallet.api.rpc.SolanaRpcClient
import com.zksg.kudoud.wallet.data.SolanaAccount
import com.zksg.kudoud.wallet.data.SolanaPublicKey
import com.zksg.kudoud.wallet.program.TokenProgram
import com.tencent.mmkv.MMKV
import com.zksg.kudoud.callback.WalletCreateCallback
import com.zksg.kudoud.contants.CoinType
import com.zksg.kudoud.state.load.BaseLoadingViewModel
import com.zksg.kudoud.utils.ObjectSerializationUtils
import com.zksg.kudoud.utils.manager.SimpleWallet
import com.zksg.kudoud.utils.manager.SolanaWalletManager
import com.zksg.kudoud.wallet.api.rpc.types.ConfigObjects
import com.zksg.kudoud.wallet.api.rpc.types.ConfigObjects.Filter
import com.zksg.kudoud.wallet.api.rpc.types.ConfigObjects.Memcmp
import com.zksg.kudoud.wallet.api.rpc.types.DataSize
import com.zksg.kudoud.wallet.api.rpc.types.RpcSendTransactionConfig
import com.zksg.kudoud.wallet.constants.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.bitcoinj.core.Base58
import com.kunminx.architecture.ui.state.State
import com.netease.lib_network.entitys.BroadcastRequest
import com.netease.lib_network.entitys.JupToken
import com.zksg.kudoud.R
import com.zksg.kudoud.callback.WalletCusTokenInfo
import com.zksg.kudoud.entitys.TransationHistory
import com.zksg.kudoud.entitys.UiWalletToken
import com.zksg.kudoud.repository.DataRepository
import com.zksg.kudoud.wallet.data.SolanaTransaction
import com.zksg.kudoud.wallet.program.SystemProgram
import com.zksg.kudoud.wallet.utils.SolanaConverter
import java.math.BigDecimal


/**
 * //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
 */
class SendCoinConfirmActivityViewmodel : BaseLoadingViewModel() {



    var currentToken=ObservableField<UiWalletToken>()
    var sender=ObservableField<String>()
    var receiver=ObservableField<String>()
    var gas=MutableResult("0.0")
    var rent=ObservableField("0.0")
    var keepsol=ObservableField("0.0")
    var number=ObservableField("0.0")
    var sol=ObservableField<UiWalletToken>()
    var issend=ObservableField(false)
    var isspl=ObservableField(false)
    var commit=MutableResult<TransationHistory>()

    @JvmField
    var lastblockhash= MutableResult("")
//    @JvmField
//    var pwd = ObservableField(false)
//    @JvmField
//    var pwdConfirm = ObservableField(false)

    fun getEstimatedFee(from: String,to: String,amount: Long){
        viewModelScope.launch {
            loadingVisible.postValue(true)
            withContext(Dispatchers.IO){
                DataRepository.getInstance().getEstimatedFee(from,to,amount){
                    if(it.responseStatus.isSuccess){
                        Log.d("----getEstimatedFee-->",it.result.data)
                        if(it.result!=null){
                            gas.postValue(it.result.data)
                            loadingVisible.postValue(false)
                        }

                    }
                }
            }

        }
    }


    @SuppressLint("SuspiciousIndentation")
    @RequiresApi(Build.VERSION_CODES.O)
    fun getLastBlockHash(solanaAccount: SolanaAccount){
        viewModelScope.launch {
            loadingVisible.postValue(true)
            withContext(Dispatchers.IO){
                DataRepository.getInstance().getLastBlockHash(){
                    if(it.responseStatus.isSuccess){
                        Log.d("----getLastBlockHash-->",it.result.data)
//                        lastblockhash.postValue(it.result.data)
//                        loadingVisible.postValue(false)
                        if(it.result!=null){
                          var base64SignTransation=buildAndSignTransation(solanaAccount,it.result.data)
                            broadcastTx(base64SignTransation)
                        }

                    }
                }
            }

        }
    }




    //广播签名的交易
    fun broadcastTx(signTransation:String){
        viewModelScope.launch {
//            loadingVisible.postValue(true)
            withContext(Dispatchers.IO){
                DataRepository.getInstance().broadcastTx(BroadcastRequest(signTransation)){
                    if(it.responseStatus.isSuccess){
                        if(it.result!=null&&it.result.data!=null){
                            Log.d("----broadcastTx-success-->",GsonUtil.toJson(it.result.data))
                            loadingVisible.postValue(false)
                            commit.postValue(TransationHistory(sender.get(),receiver.get(),gas.value,number.get(),it.result.data.txid,it.result.data.status,it.result.data.committime))//txid
                        }

                    }else{
                        Log.d("----broadcastTx-err-->",it.responseStatus.msg)

                    }
                }
            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun buildAndSignTransation(solanaAccount: SolanaAccount,lastblockhash:String):String{
        // create new transaction
        var transaction = SolanaTransaction()
        var fromPublicKey=solanaAccount.getPublicKey()
        var toPublickKey = SolanaPublicKey(receiver.get())
        // add instructions to the transaction (from, to, lamports)
        transaction.addInstruction(
            SystemProgram.transfer(fromPublicKey, toPublickKey,
                SolanaConverter.solToLamports(BigDecimal(number.get()).toDouble())
            ))
        transaction.setRecentBlockHash(lastblockhash)
        // set the fee payer
        transaction.setFeePayer(fromPublicKey)
        // sign the transaction
        transaction.sign(solanaAccount)
        val base64String = Base64.encodeToString(transaction.serialize(), Base64.DEFAULT)
        Log.d("---transation--->",base64String)
        return base64String

    }





}