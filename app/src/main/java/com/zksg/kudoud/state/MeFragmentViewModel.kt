package com.zksg.kudoud.state

import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.kunminx.architecture.domain.message.MutableResult
import com.kunminx.architecture.ui.state.State
import com.netease.lib_common_ui.utils.GsonUtil
import com.netease.lib_network.entitys.NewWalletToken
import com.zksg.kudoud.callback.WalletSolBalanceCallback
import com.zksg.kudoud.callback.WalletUpdateTokensBalanceCallback
import com.zksg.kudoud.entitys.UiWalletToken
import com.zksg.kudoud.repository.DataRepository
import com.zksg.kudoud.state.load.BaseLoadingViewModel
import com.zksg.kudoud.utils.manager.SimpleWallet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigDecimal

class MeFragmentViewModel : BaseLoadingViewModel() {

    @JvmField
    var show_wallet = ObservableField(false)
    @JvmField
    var mSimpleWallet = ObservableField<SimpleWallet>()



    @JvmField
    var mWalletAmountMoney=MutableResult("0.0")


    //展示在钱包列表ui 数据 UiWalletToken is song for NewWalletToken
    @JvmField
    var uitokenInfos=MutableResult(mutableListOf<UiWalletToken>())

    @JvmField
    var isAutoRefresh=MutableResult<Boolean>()



    //这里已经实现了获取钱包token基本信息-现在弃用改为统一的从自己写的api服务器拿 方便统一管理
//    fun getWalletSplTokenList(){
//        var keyAlias= MMKV.mmkvWithID("currentWallet").decodeString("keyAlias","")
//        var network= MMKV.mmkvWithID("currentWallet").decodeString("netwrokgroup", CoinType.SOLANA.key)
//        var simpleWalletbyte= MMKV.mmkvWithID(network).decodeBytes(keyAlias)
//        var simpleWallet= ObjectSerializationUtils.deserializeObject(simpleWalletbyte) as SimpleWallet
//
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//
//                val client = SolanaRpcClient.getInstance(Cluster.QUICKNODE)
//                //params1
////                var params1=account.publicKey.toBase58()
//                var params1= Constants.TOKEN_PROGRAM_ID
////                var secretkey=Base58.encode(account.secretKey)
//                Log.e("----address---->",simpleWallet.address)
//
//                var filter1= ConfigObjects.Filter(ConfigObjects.Memcmp(32,simpleWallet.address))
//                var filter2= ConfigObjects.Filter1(165)
//                var filters= listOf(filter1,filter2)
//
//                var params2= ConfigObjects.ProgramAccountConfig(filters)
//                params2.encoding= RpcSendTransactionConfig.Encoding.jsonParsed
//
//                var result = client.api.getProgramAccounts1(params1,params2)
//
//                if(result.size>0){
//                    programAccounts.postValue(result)
//                }
//
//
//            }
//        }
//
//
//
//    }

//
//    fun getTokenInfos(arr: Array<String>){
//        viewModelScope.launch{
//
//            withContext(Dispatchers.IO){
//                val joinedString = arr.joinToString(separator = ",")
//                DataRepository.getInstance().getTokenInfoForJup(joinedString){
//                    if(it.responseStatus.isSuccess){
//
//                        var filter_result=it.result.pairs.stream()
//                            .filter { item->item.liquidity!=null }
//                            .sorted(compareByDescending { dao -> dao.liquidity?.usd })
//                            .collect(Collectors.toList())
//                        //覆盖原有集合位过滤后的集合
//                        it.result.pairs=filter_result
//
//                        //推送数据出去
//                        tokenInfo.postValue(it.result)
////                        mBase2QuoEntity.set(Base2QuoEntity(address,it.result.pairs))
//
//                    }
//
//
//                }
//
//            }
//
//
//
//        }
//
//    }






    fun updateWalletBalance(wallet: String,mWalletUpdateTokensBalanceCallback: WalletUpdateTokensBalanceCallback){
        viewModelScope.launch{
            withContext(Dispatchers.IO){
//                loadingVisible.postValue(true)
                DataRepository.getInstance().updateWalletBalance(wallet){
                    if(it.responseStatus.isSuccess){
                        if(it.result.data!=null||it.result.data.size>0){
                            //降序
                            val sortedWalletTokens = it.result.data.sortedByDescending { BigDecimal(it.balance).toDouble() }
                            mWalletUpdateTokensBalanceCallback.updateWalletTokensBalance(sortedWalletTokens)
                        }
                    }else{
                        mWalletUpdateTokensBalanceCallback.updateWalletTokensBalance(null)
                    }
                }

            }
        }

    }

    //获取钱包sol 余额
    fun getWalletSolBalance(wallet: String, mint: String,callback: WalletSolBalanceCallback){
        viewModelScope.launch{
            withContext(Dispatchers.IO){
                DataRepository.getInstance().getWalletSolBalanceFromRepository(wallet,mint){
                    if(it.responseStatus.isSuccess){
                        if(it.result.data!=null){
                            //更新uitokenInfos列表第一个数据
                            Log.d("----apiinfo---->",GsonUtil.toJson(it.result.data))
                            callback.walletSolUpdate(it.result.data)
                        }


                    }
                }
            }
        }

    }



}