package com.zksg.kudoud.state

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.kunminx.architecture.domain.message.MutableResult
import com.netease.lib_network.entitys.DexScreenTokenInfo1
import com.netease.lib_network.entitys.DexScreenTokenInfo1.PairsDTO
import com.zksg.kudoud.adapters.CommonKlineDataPagerAdapter
import com.zksg.kudoud.customviews.NoTouchScrollViewpager
import com.zksg.kudoud.entitys.Base2QuoEntity
import com.zksg.kudoud.repository.DataRepository
import com.zksg.kudoud.state.load.BaseLoadingViewModel
import com.zksg.lib_api.beans.AppInfoBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Comparator
import java.util.stream.Collectors
import com.kunminx.architecture.ui.state.State

/**
 * //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
 */
class CoinsDetailViewModel : BaseLoadingViewModel() {

    //是否显示布局
    @JvmField
    var isshow=State<Boolean>(false)

    //计算总的base币个数的时候需要用到
    @JvmField
    var mBase2QuoEntity=ObservableField<Base2QuoEntity>()

    //总的数据
    @JvmField
    var tokenInfo= MutableResult<DexScreenTokenInfo1>()

    @JvmField
    var tabAdapter = ObservableField<CommonKlineDataPagerAdapter>()
    var viewpager = ObservableField<NoTouchScrollViewpager>()
    @JvmField
    var indicatorTitle = ObservableField<Array<String>>()

    //集合中第一个的PairsDTO
    @JvmField
    var mPairsDTO=MutableResult<PairsDTO>()

    init {
        indicatorTitle.set(arrayOf("5M", "1H", "6H", "24H"))
    }


    fun getTokenInfo(address:String){
//        viewModelScope.launch{
//            loadingVisible.postValue(true)
//            withContext(Dispatchers.IO){
//
//                DataRepository.getInstance().getTokenInfoForDexScreen(address){
//                    if(it.responseStatus.isSuccess){
//
//                        var filter_result=it.result.pairs.stream()
//                            .filter { item->item.liquidity!=null }
//                            .sorted(compareByDescending { dao -> dao.liquidity?.usd })
//                            .collect(Collectors.toList())
//                        //覆盖原有集合位过滤后的集合
//                        it.result.pairs=filter_result
//
//                        //推送数据出去
//                        tokenInfo.postValue(it.result)
//                        mBase2QuoEntity.set(Base2QuoEntity(address,it.result.pairs))
//
//                    }
//                    loadingVisible.postValue(false)
//                    isshow.set(true)
//                }
//
//            }
//
//
//
//        }

    }


}