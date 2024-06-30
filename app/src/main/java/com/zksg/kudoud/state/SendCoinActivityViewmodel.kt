package com.zksg.kudoud.state

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.kunminx.architecture.domain.message.MutableResult
import com.zksg.kudoud.wallet.data.SolanaAccount
import com.zksg.kudoud.state.load.BaseLoadingViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.kunminx.architecture.ui.state.State
import com.netease.lib_network.entitys.JupToken
import com.zksg.kudoud.entitys.UiWalletToken
import com.zksg.kudoud.repository.DataRepository


/**
 * //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
 */
class SendCoinActivityViewmodel : BaseLoadingViewModel() {


    @JvmField
    var currentToken = ObservableField<UiWalletToken>()
    @JvmField
    var numberText=MutableResult("0")

    //账号租金 默认 0.00089
    @JvmField
    var AccountRent=ObservableField("0.0")


    @JvmField
    var AccountRentShow=ObservableField(false)


    var solanaAccount=ObservableField<SolanaAccount>()

    //    public ObservableField<List<FeedTip>> datas=new ObservableField<>();
    var contract = ObservableField<String>()
    var isapass = State(false)
    var iscontractpass=State(false)
    var symbol=ObservableField<String>()
    var decimal=ObservableField<String>()
    var token=MutableResult<JupToken>()



//    @JvmField
//    var pwd = ObservableField(false)
//    @JvmField
//    var pwdConfirm = ObservableField(false)

    //请求账号租金-转sol的时候才需要
    fun getRentForAccount(wallet: String){
        viewModelScope.launch {
            loadingVisible.postValue(true)
            withContext(Dispatchers.IO){

                DataRepository.getInstance().getRentForAccount(wallet){
                    if(it.result!=null){
                        Log.d("----getRentForAccount---->",it.result.data)
                        AccountRent.set(it.result.data)
                        loadingVisible.postValue(false)
                    }
                }

            }

        }

    }



}