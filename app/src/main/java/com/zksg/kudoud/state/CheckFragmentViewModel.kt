package com.zksg.kudoud.state

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.netease.lib_network.entitys.CheckToken
import com.netease.lib_network.entitys.DexScreenTokenInfo1
import com.zksg.kudoud.entitys.Base2QuoEntity
import com.zksg.kudoud.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.stream.Collectors

class CheckFragmentViewModel : ViewModel(){

//    @JvmField
//    var url = ObservableField<CheckToken.TokenContractDTO.ContractDataDTO.TokenHoldersRankDTO>()

    var top10hold=ObservableField<List<CheckToken.TokenContractDTO.ContractDataDTO.TokenHoldersRankDTO>>()
    var riskScore=ObservableField<Int>()
    var has_black_method=ObservableField<Int>() //黑名单
    var has_mint_method=ObservableField<Int>() //增发
    var has_owner_removed_risk=ObservableField<Int>() //抛弃管理权
    var has_not_burned_lp=ObservableField<Int>() //池子燃烧
    var has_top10_holder_amount_over_30=ObservableField<Int>() //top10持币超过30%
    var unsafeamount=ObservableField(0) //统计危险总数
    var buygas=ObservableField<String>()
    var sellgas=ObservableField<String>()


    fun getCheckTokenInfo(contract:String){
        viewModelScope.launch{
//            loadingVisible.postValue(true)
            withContext(Dispatchers.IO){

                DataRepository.getInstance().getCheckToken(contract){
                    if(it.responseStatus.isSuccess){
                        if(it.result.data==null)return@getCheckToken
                        top10hold.set(it.result.data.tokenContract.contractData.tokenHoldersRank)
                        riskScore.set(it.result.data.tokenContract.contractData.riskScore)
                        buygas.set(it.result.data.tokenContract.contractData.buyGas)
                        sellgas.set(it.result.data.tokenContract.contractData.sellGas)


                        if(it.result.data.tokenContract.contractData.hasBlackMethod==1){
                            unsafeamount.set(unsafeamount.get()!!+1)
                        }
                        has_black_method.set(it.result.data.tokenContract.contractData.hasBlackMethod)

                        if(it.result.data.tokenContract.contractData.hasMintMethod==1){
                            unsafeamount.set(unsafeamount.get()!!+1)
                        }
                        has_mint_method.set(it.result.data.tokenContract.contractData.hasMintMethod)

//                        if(it.result.data.tokenContract.contractData.hasOwnerRemovedRisk==1){
//                            unsafeamount.set(unsafeamount.get()!!+1)
//                        }
//                        has_owner_removed_risk.set(it.result.data.tokenContract.contractData.hasOwnerRemovedRisk)

                        if(it.result.data.tokenContract.contractData.hasNotBurnedLp==1){
                            unsafeamount.set(unsafeamount.get()!!+1)
                        }
                        has_not_burned_lp.set(it.result.data.tokenContract.contractData.hasNotBurnedLp)

                        if(it.result.data.tokenContract.contractData.hasTop10HolderAmountOver30==1){
                            unsafeamount.set(unsafeamount.get()!!+1)
                        }
                        has_top10_holder_amount_over_30.set(it.result.data.tokenContract.contractData.hasTop10HolderAmountOver30)
                    }
//                    loadingVisible.postValue(false)
                }

            }



        }

    }


}