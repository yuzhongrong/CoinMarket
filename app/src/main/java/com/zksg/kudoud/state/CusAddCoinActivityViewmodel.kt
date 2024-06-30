package com.zksg.kudoud.state

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.kunminx.architecture.domain.message.MutableResult
import com.zksg.kudoud.state.load.BaseLoadingViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.kunminx.architecture.ui.state.State
import com.netease.lib_network.entitys.JupToken
import com.zksg.kudoud.callback.WalletCusTokenInfo
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

    fun reqCusCoinInfo(contract: String,callback: WalletCusTokenInfo){

        viewModelScope.launch {
            loadingVisible.postValue(true)
            withContext(Dispatchers.IO){
                DataRepository.getInstance().getCusCoinInfo(contract){
                    if(it.responseStatus.isSuccess){

                        if(it.result.data!=null){

                            callback.cusTokenInfo(it.result.data)

                        }else{
                            callback.cusTokenInfo(null)
                        }
                        //更新状态
                        loadingVisible.postValue(false)

                    }else{
                        loadingVisible.postValue(false)
                    }
                }
            }

        }



    }



}