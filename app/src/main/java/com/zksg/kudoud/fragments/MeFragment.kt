package com.zksg.kudoud.fragments


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kunminx.architecture.ui.page.BaseFragment
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.activitys.*
import com.zksg.kudoud.adapters.MemeCommonWalletListdapter
import com.zksg.kudoud.state.MeFragmentViewModel
import com.zksg.kudoud.utils.DataFilterUtils
import com.zksg.kudoud.utils.IntentUtils
import com.zksg.kudoud.utils.LocalJsonResolutionUtils
import com.zksg.lib_api.beans.MemeCommonEntry


class MeFragment : BaseFragment() {

    private var meViewModel: MeFragmentViewModel? = null


    override fun initViewModel() {
        meViewModel = getFragmentScopeViewModel(MeFragmentViewModel::class.java)

    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_mine, BR.vm, meViewModel!!)
            .addBindingParam(BR.click, ClickProxy())
            .addBindingParam(BR.adapter, MemeCommonWalletListdapter(context))
    }




    override fun loadInitData() {

        initData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return super.onCreateView(inflater, container, savedInstanceState)
    }


    fun initData(){

        var json= LocalJsonResolutionUtils.getJson(context,"memevolsimulator.json")
        var simulators= LocalJsonResolutionUtils.JsonToObject(json, MemeCommonEntry::class.java)
        var results= DataFilterUtils.filterNonNullName(simulators.tokens)
        meViewModel!!.mWalletTokens.postValue(results)


    }





    inner class ClickProxy {
        fun startCreateWallet() {
            IntentUtils.openIntent(requireContext(), Intent(requireContext(),WalleManagertActivity::class.java))
        }

    }


}