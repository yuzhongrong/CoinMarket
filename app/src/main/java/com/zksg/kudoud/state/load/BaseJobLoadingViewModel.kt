package com.zksg.kudoud.state.load

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.GsonUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kunminx.architecture.domain.message.MutableResult
import com.netease.lib_network.entitys.CommonCategory
import com.tencent.mmkv.MMKV
import com.zksg.kudoud.repository.DataRepository
import kotlinx.coroutines.*

open class BaseJobLoadingViewModel : ViewModel() {
    //加载动画
    //    public final ObservableBoolean loadingVisible = new ObservableBoolean();
    private var trendingJob: Job? = null
    val loadingVisible = MutableResult<Boolean>()
    val adapter = ObservableField<BaseQuickAdapter<*, *>>()

    @JvmField
    val categorys = MutableResult<List<CommonCategory.DataDTO>>()


    fun startFetchingTrendingTokens(category: String,resultTake:Int) {
        trendingJob?.cancel() // 取消之前的任务，避免重复创建
        trendingJob = viewModelScope.launch {
            while (isActive) {
                getCategoryDatas(category,resultTake)
                delay(60 * 1000) // 1分钟
            }
        }
    }


    fun stopFetchingTrendingTokens() {
        trendingJob?.cancel()
    }


    fun getCategoryDatas(category:String,resultTake:Int){
        Log.d("----getCategoryDatas--->",category)
        viewModelScope.launch {
            withContext(Dispatchers.IO){
//                loadingVisible.postValue(true)
                DataRepository.getInstance().getCategoryDatas(category){
                    if(it.responseStatus.isSuccess){
                        if(it.result!=null&&it.result.data!=null){
                            Log.d("---getCategoryDatas_ok--->$category--->", GsonUtils.toJson(it.result.data))
                            categorys.postValue(it.result.data.take(resultTake))
                            //下面这个操作是为了全局提供热门数据
                            MMKV.mmkvWithID("request_data_share").encode(category, GsonUtils.toJson(it.result.data))

                        }
                    }


                }
//                loadingVisible.postValue(false)
            }


        }
    }


}