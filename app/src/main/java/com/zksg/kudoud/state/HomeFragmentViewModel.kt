package com.zksg.kudoud.state

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.GsonUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kunminx.architecture.domain.message.MutableResult
import com.zksg.kudoud.adapters.MemeCategoryPagerAdapter
import com.netease.lib_network.entitys.CommonCategory
import com.tencent.mmkv.MMKV
import com.zksg.kudoud.repository.DataRepository
import com.zksg.kudoud.state.load.BaseLoadingViewModel
import com.zksg.kudoud.utils.GsonUtil
import com.zksg.lib_api.beans.AppInfoBean
import com.zksg.lib_api.beans.BannerBean
import com.zksg.lib_api.beans.NotifyBean
import com.zksg.lib_api.beans.UpgradeBean
import kotlinx.coroutines.*

class HomeFragmentViewModel : BaseLoadingViewModel() {
    private var trendingJob: Job? = null

    var indicatorTitle = ObservableField<Array<String>>()
    var memecategoryadapter = ObservableField<MemeCategoryPagerAdapter?>()

    @JvmField
    var coininstallAdapter = ObservableField<BaseQuickAdapter<*, *>>()
    @JvmField
    var banner_datas = MutableResult<List<BannerBean>>()

    /*----------------------Respons result----------------------------*/
    @JvmField
    val mTrendings = MutableResult<List<CommonCategory.DataDTO>>()

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

    fun getTrendingTokens(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
//                loadingVisible.postValue(true)
                DataRepository.getInstance().getTrendingTokens{

                  if(it.responseStatus.isSuccess){
                      if(it.result!=null&&it.result.data!=null){
                        Log.d("---getTrendingTokens--->", GsonUtils.toJson(it.result.data))
                          mTrendings.postValue(it.result.data.take(10))
                          //下面这个操作是为了全局提供热门数据
                          MMKV.mmkvWithID("request_data_share").encode("trending",GsonUtils.toJson(it.result.data))

                      }
                  }


                }
//                loadingVisible.postValue(false)
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


              DataRepository.getInstance().getLastNoticeForOrder(1,50,"created_at","descending"){
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

    fun startFetchingTrendingTokens() {
        trendingJob?.cancel() // 取消之前的任务，避免重复创建
        trendingJob = viewModelScope.launch {
            while (isActive) {
                getTrendingTokens()
                delay(60 * 1000) // 1分钟
            }
        }
    }

    fun stopFetchingTrendingTokens() {
        trendingJob?.cancel()
    }





}