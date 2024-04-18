package com.zksg.kudoud.state

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.kunminx.architecture.domain.message.MutableResult
import com.netease.lib_network.entitys.DexScreenTokenInfo
import com.netease.lib_network.entitys.DexScreenTokenInfo.PairsDTO
import com.zksg.kudoud.adapters.CommonKlineDataPagerAdapter
import com.zksg.kudoud.customviews.NoTouchScrollViewpager
import com.zksg.kudoud.repository.DataRepository
import com.zksg.kudoud.state.load.BaseLoadingViewModel
import com.zksg.lib_api.beans.AppInfoBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
 */
class CoinsDetailViewModel : BaseLoadingViewModel() {

    //总的数据
    @JvmField
    var tokenInfo= MutableResult<DexScreenTokenInfo>()

    @JvmField
    var tabAdapter = ObservableField<CommonKlineDataPagerAdapter>()
    var viewpager = ObservableField<NoTouchScrollViewpager>()
    @JvmField
    var indicatorTitle = ObservableField<Array<String>>()

    //当前的PairsDTO
    @JvmField
    var mPairsDTO=MutableResult<PairsDTO>()

    init {
        indicatorTitle.set(arrayOf("5M", "1H", "6H", "24H"))
    }


    fun getTokenInfo(address:String){

        viewModelScope.launch{
            loadingVisible.postValue(true)
            withContext(Dispatchers.IO){

                DataRepository.getInstance().getTokenInfoForDexScreen(address){
                    if(it.responseStatus.isSuccess){
                        tokenInfo.postValue(it.result)
                    }
                    loadingVisible.postValue(false)
                }

            }



        }

    }


}