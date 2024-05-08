package com.zksg.kudoud.state

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
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
import com.netease.lib_network.entitys.JupToken
import com.zksg.kudoud.repository.DataRepository


/**
 * //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
 */
class CusAddCoinActivityViewmodel : BaseLoadingViewModel() {
    //    public ObservableField<List<FeedTip>> datas=new ObservableField<>();
    var contract = ObservableField<String>()
    var isadd = State(false)
    var symbol=ObservableField<String>()
    var decimal=ObservableField<String>()
    var token=MutableResult<JupToken>()

//    @JvmField
//    var pwd = ObservableField(false)
//    @JvmField
//    var pwdConfirm = ObservableField(false)

    fun reqCusCoinInfo(contract: String){

        viewModelScope.launch {
            loadingVisible.postValue(true)
            withContext(Dispatchers.IO){
                DataRepository.getInstance().getCusCoinInfo(contract){
                    if(it.responseStatus.isSuccess){
//                        symbol.postValue()
//                        decimal.postValue()
                        Log.d("-----coin info--->",Gson().toJson(it.result.data))
                        //用于保存的数据
                        token.postValue(it.result.data)
                        //用于展示的数据
                        symbol.set(it.result.data.symbol)
                        var decimalValue=it.result.data.decimals.toString()
                        decimal.set(decimalValue)
                        //更新状态
                        loadingVisible.postValue(false)
                        isadd.set(true)
                    }else{
                        loadingVisible.postValue(false)
                    }
                }
            }

        }



    }



}