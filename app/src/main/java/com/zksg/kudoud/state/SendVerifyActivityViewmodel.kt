package com.zksg.kudoud.state

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.kunminx.architecture.domain.message.MutableResult
import com.netease.lib_network.entitys.BroadcastRequest
import com.zksg.kudoud.repository.DataRepository
import com.zksg.kudoud.state.load.BaseLoadingViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
 */
class SendVerifyActivityViewmodel : BaseLoadingViewModel() {
    //    public ObservableField<List<FeedTip>> datas=new ObservableField<>();
    var walletname = ObservableField<String>()
    var walletpwd = ObservableField<String>()
    var walletconfirmpwd = ObservableField<String>()
    @JvmField
    var pwd = ObservableField(false)
    @JvmField
    var pwdConfirm = ObservableField(false)


    //intent params
    @JvmField
    var contract = ObservableField<String>()


//
//    fun createWallet(mWalletCreateCallback: WalletCreateCallback){
//
//        viewModelScope.launch {
//            loadingVisible.postValue(true)
//            withContext(Dispatchers.IO){
//                SolanaWalletManager.createWallet(Utils.getApp().applicationContext,walletname.get(),walletpwd.get(),mWalletCreateCallback)
//                loadingVisible.postValue(false)
//            }
//
//        }
//
//
//
//    }






}