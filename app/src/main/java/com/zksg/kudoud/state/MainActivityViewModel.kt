package com.zksg.kudoud.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zksg.kudoud.callback.WalletTokensCallback
import com.zksg.kudoud.repository.DataRepository
import com.zksg.kudoud.utils.IPFSManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigDecimal

class MainActivityViewModel : ViewModel() {

    fun ReqHotCointDatas(model: String,mWalletTokensCallback: WalletTokensCallback){
        viewModelScope.launch {
          withContext(Dispatchers.IO){
              DataRepository.getInstance().getHotCoinDatas(model){
                  if(it.responseStatus.isSuccess){

                          mWalletTokensCallback.WalletHotCoins(it.result.data)

                  }else{
                      mWalletTokensCallback.WalletHotCoins(null)
                  }
              }

            }
        }


    }

}