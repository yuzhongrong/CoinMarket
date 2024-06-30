package com.zksg.kudoud.state

import android.annotation.SuppressLint
import android.os.Build
import android.util.Base64
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.kunminx.architecture.domain.message.MutableResult
import com.netease.lib_common_ui.utils.GsonUtil
import com.zksg.kudoud.wallet.data.SolanaAccount
import com.zksg.kudoud.wallet.data.SolanaPublicKey
import com.zksg.kudoud.state.load.BaseLoadingViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.netease.lib_network.entitys.BroadcastRequest
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
    var isspl=ObservableField(true)
    var commit=MutableResult(false)

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


    fun getSplEstimatedFee(from: String,to: String,mint:String,amount: Long){
        viewModelScope.launch {
            loadingVisible.postValue(true)
            withContext(Dispatchers.IO){
                DataRepository.getInstance().getSplEstimatedFee(from,to,mint,amount){
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
                            commit.postValue(true)
                        }

                    }else{
                        Log.d("----broadcastTx-err-->",it.responseStatus.msg)
                    }
                }
            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun buildAndSignTransation(solanaAccount: SolanaAccount, lastblockhash:String):String{
        // create new transaction
        var transaction = SolanaTransaction()
        var fromPublicKey=solanaAccount.getPublicKey()
        var toPublickKey =
            SolanaPublicKey(receiver.get())
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