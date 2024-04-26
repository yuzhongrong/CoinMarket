package com.zksg.kudoud.state

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kunminx.architecture.utils.Utils
import com.zksg.kudoud.callback.WalletCreateCallback
import com.zksg.kudoud.state.load.BaseLoadingViewModel
import com.zksg.kudoud.utils.manager.SolanaWalletManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
 */
class CreateWalletActivityViewmodel : BaseLoadingViewModel() {
    //    public ObservableField<List<FeedTip>> datas=new ObservableField<>();
    var walletname = ObservableField<String>()
    var walletpwd = ObservableField<String>()
    var walletconfirmpwd = ObservableField<String>()
    @JvmField
    var pwd = ObservableField(false)
    @JvmField
    var pwdConfirm = ObservableField(false)

    fun createWallet(mWalletCreateCallback: WalletCreateCallback){

        viewModelScope.launch {
            loadingVisible.postValue(true)
            withContext(Dispatchers.IO){
                SolanaWalletManager.createWallet(Utils.getApp().applicationContext,walletname.get(),walletpwd.get(),mWalletCreateCallback)
                loadingVisible.postValue(false)
            }

        }



    }

}