package com.zksg.kudoud.state

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kunminx.architecture.domain.message.MutableResult
import com.netease.lib_network.entitys.DexScreenTokenInfo1
import com.zksg.kudoud.entitys.Base2QuoEntity
import com.zksg.kudoud.repository.DataRepository
import com.zksg.kudoud.state.load.BaseLoadingViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.stream.Collectors

class PoolsFragmentViewModel : BaseLoadingViewModel(){
    //总的数据
    @JvmField
    var tokenInfo= ObservableField<DexScreenTokenInfo1>()


    //计算总的base币个数的时候需要用到
    @JvmField
    var mBase2QuoEntity= ObservableField<Base2QuoEntity>()


    fun getTokenInfo(address:String){
        viewModelScope.launch{
//            loadingVisible.postValue(true)
            withContext(Dispatchers.IO){

                DataRepository.getInstance().getTokenInfoForDexScreen(address){
                    if(it.responseStatus.isSuccess){

                        var filter_result=it.result.data.pairs.stream()
                            .filter { item->item.liquidity!=null }
                            .sorted(compareByDescending { dao -> dao.liquidity?.usd })
                            .collect(Collectors.toList())
                        //覆盖原有集合位过滤后的集合
                        it.result.data.pairs=filter_result

                        //推送数据出去
                        tokenInfo.set(it.result.data)
                        mBase2QuoEntity.set(Base2QuoEntity(address,it.result.data.pairs))

                    }
//                    loadingVisible.postValue(false)
                }

            }



        }

    }



}