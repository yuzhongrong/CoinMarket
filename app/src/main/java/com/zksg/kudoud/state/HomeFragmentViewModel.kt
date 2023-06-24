package com.zksg.kudoud.state

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kunminx.architecture.data.response.DataResult
import com.kunminx.architecture.domain.message.MutableResult
import com.zksg.kudoud.repository.DataRepository
import com.zksg.kudoud.state.load.BaseLoadingViewModel
import com.zksg.lib_api.beans.AppInfoBean
import com.zksg.lib_api.beans.ResponsPublishApk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.ArrayList

class HomeFragmentViewModel : BaseLoadingViewModel() {
    @JvmField
    var todayHealthAdapter = ObservableField<BaseQuickAdapter<*, *>>()
    var todayhotAdapter = ObservableField<BaseQuickAdapter<*, *>>()
    @JvmField
    var coininstallAdapter = ObservableField<BaseQuickAdapter<*, *>>()
    @JvmField
    var banner_datas = ObservableField<List<String>>()

    /*----------------------Respons result----------------------------*/
    val mPublishApks = MutableResult<List<AppInfoBean>>()
    val mHotApks = MutableResult<List<AppInfoBean>>()
    init {
        banner_datas.set(
            Arrays.asList(
                "ipfs://QmP6UJ6cNYEh8BYbcdGKecZB1uKK3uKp5JWxnSkDQJZqQf",
                "ipfs://QmP6UJ6cNYEh8BYbcdGKecZB1uKK3uKp5JWxnSkDQJZqQf"
            )
        )
    }

    fun getRecentPublishApp(page:Int,pageSize:Int){
        viewModelScope.launch {
            loadingVisible.value=true
            withContext(Dispatchers.IO){
                DataRepository.getInstance().getAppinfoList(page,pageSize){

                  if(it.result.data!=null) mPublishApks.postValue(it.result.data.list)

                }
            }
            loadingVisible.value=false

        }
    }

    fun getCwApps(page:Int,pageSize:Int,downloadCount:Int){
        viewModelScope.launch {
            loadingVisible.value=true
            withContext(Dispatchers.IO){
                DataRepository.getInstance().getAppinfoList(page,pageSize,downloadCount){
                    if(it.result.data!=null) mHotApks.postValue(it.result.data.list)
                }
            }
            loadingVisible.value=false

        }
    }

}