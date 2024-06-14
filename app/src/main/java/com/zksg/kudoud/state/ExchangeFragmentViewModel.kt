package com.zksg.kudoud.state

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kunminx.architecture.domain.message.MutableResult
import com.netease.lib_common_ui.utils.GsonUtil
import com.netease.lib_network.entitys.QuoEntity
import com.zksg.kudoud.R
import com.zksg.kudoud.entitys.UiWalletToken
import com.zksg.kudoud.repository.DataRepository
import com.zksg.kudoud.state.load.BaseLoadingViewModel
import com.zksg.kudoud.wallet.constants.Constants
import com.zksg.kudoud.wallet.constants.Constants.TOKEN_SOL_CONTRACT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ExchangeFragmentViewModel : BaseLoadingViewModel() {
    @JvmField
    var from = MutableResult<UiWalletToken>()

    @JvmField
    var to = MutableResult<UiWalletToken>()
    @JvmField
    var from_amount = MutableResult(1) //默认from amount=1


    @JvmField
    var to_amount = MutableResult(0) //默认from amount=1
    @JvmField
    var from_wallet_amount = MutableResult("0.0") //默认from amount=1
    @JvmField
    var to_wallet_amount = MutableResult("0.0") //默认to amount=1

    @JvmField
    var swap_out_amount=MutableResult("0.0")

    @JvmField
    var quo=MutableResult<QuoEntity>()


    fun getQuo(from: String,to: String,amount:String,fromdecimal: Int){
        viewModelScope.launch {
//            loadingVisible.postValue(true)
            withContext(Dispatchers.IO){
                DataRepository.getInstance().getQuo(from,to,amount,fromdecimal){
                    if(it.responseStatus.isSuccess){
                        Log.d("----getQuo-->",GsonUtil.toJson(it.result.data))
                        if(it.result!=null){
                            quo.postValue(it.result.data)
//                            loadingVisible.postValue(false)
                        }

                    }
                }
            }

        }
    }


}