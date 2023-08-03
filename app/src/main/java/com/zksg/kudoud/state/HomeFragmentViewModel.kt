package com.zksg.kudoud.state

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kunminx.architecture.domain.message.MutableResult
import com.zksg.kudoud.repository.DataRepository
import com.zksg.kudoud.state.load.BaseLoadingViewModel
import com.zksg.lib_api.beans.AppInfoBean
import com.zksg.lib_api.beans.BannerBean
import com.zksg.lib_api.beans.NotifyBean
import com.zksg.lib_api.beans.UpgradeBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragmentViewModel : BaseLoadingViewModel() {
    @JvmField
    var todayHealthAdapter = ObservableField<BaseQuickAdapter<*, *>>()
    var todayhotAdapter = ObservableField<BaseQuickAdapter<*, *>>()
    @JvmField
    var coininstallAdapter = ObservableField<BaseQuickAdapter<*, *>>()
    @JvmField
    var banner_datas = MutableResult<List<BannerBean>>()

    /*----------------------Respons result----------------------------*/
    val mPublishApks = MutableResult<List<AppInfoBean>>()
    val mBannerClickAppinfo=MutableResult<List<AppInfoBean>>()
    val mLastNotify = MutableResult<List<NotifyBean>>()

    val mUpgradeBean = MutableResult<UpgradeBean>()
    val mHotApks = MutableResult<List<AppInfoBean>>()
//    init {
//        banner_datas.set(
//            Arrays.asList(
//                "ipfs://QmWbWstc8WaGTwBzzGh2McZ9aFQCJVBRYuVY64kY219yYm",
//                "ipfs://QmNQw9c78T9gSodZm8JkwD5qAq6Sksr3ZK6M9SNpbGqBvb"
//            )
//        )
//    }

    fun getOnePublishApp(app_file:String){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                loadingVisible.postValue(true)
                DataRepository.getInstance().getAppinfoOneSearch(1,1,app_file){

                  if(it.responseStatus.isSuccess) mBannerClickAppinfo.postValue(it.result.data.list)

                }
                loadingVisible.postValue(false)
            }


        }
    }

    fun getCwApps(page:Int,pageSize:Int,downloadCount:Int){
        viewModelScope.launch {
            loadingVisible.value=true
            withContext(Dispatchers.IO){
                DataRepository.getInstance().getAppinfoList(page,pageSize,downloadCount){
                    if(it.responseStatus.isSuccess) mHotApks.postValue(it.result.data.list)
                }
            }
            loadingVisible.value=false

        }
    }

    fun getHomeDatas(){
        viewModelScope.launch {

          withContext(Dispatchers.IO){
              loadingVisible.postValue(true)

              DataRepository.getInstance().getBannerList(){
                  if(it.responseStatus.isSuccess) banner_datas.postValue(it.result.data.list)
              }

              DataRepository.getInstance().getAppinfoListForOrder(1,50,"created_at","descending"){
                  if(it.responseStatus.isSuccess) mPublishApks.postValue(it.result.data.list)
              }

              DataRepository.getInstance().getLastNoticeForOrder(1,1,"created_at","descending"){
                  if(it.responseStatus.isSuccess) mLastNotify.postValue(it.result.data.list)
              }

              DataRepository.getInstance().getAppinfoList(1,50,1000){
                  if(it.responseStatus.isSuccess){
                      mHotApks.postValue(it.result.data.list)
                      loadingVisible.postValue(false) }
                  }


              DataRepository.getInstance().getUpgradeInfo{
                  if(it.responseStatus.isSuccess) mUpgradeBean.postValue(it.result.data)
              }

            }

        }

    }







}