package com.zksg.kudoud.fragments


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kunminx.architecture.ui.page.BaseFragment
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.netease.lib_common_ui.utils.GsonUtil
import com.tokenpocket.opensdk.base.TPListener
import com.tokenpocket.opensdk.base.TPManager
import com.tokenpocket.opensdk.simple.model.Authorize
import com.tokenpocket.opensdk.simple.model.Blockchain
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.activitys.*
import com.zksg.kudoud.beans.TpWalletConnectResult
import com.zksg.kudoud.state.MeFragmentViewModel
import com.zksg.kudoud.utils.StringUtils
import com.zksg.lib_api.login.LoginBean


class MeFragment:BaseFragment(){

    private var  meViewModel: MeFragmentViewModel ?=null





    override fun initViewModel() {
        meViewModel=getFragmentScopeViewModel(MeFragmentViewModel::class.java)

    }

    override fun getDataBindingConfig(): DataBindingConfig {
       return DataBindingConfig(R.layout.fragment_mine,BR.vm,meViewModel!!)
           .addBindingParam(BR.click,  ClickProxy());
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        return super.onCreateView(inflater, container, savedInstanceState)
    }




    public inner class ClickProxy {
         fun testClick(){
             startActivity(Intent(activity,DeviceSettingActivity::class.java))
         }
        fun skip2BabyDetail(){

            startActivity(Intent(activity,BabyDetailActivity::class.java))
        }

        fun skip2Feed(){

            startActivity(Intent(activity,FeedTippctivity::class.java))
        }

        fun skip2health(){
            startActivity(Intent(activity,HealthRecordActivity::class.java))

        }
        fun skip2More(){

            startActivity(Intent(activity, MoreActivity::class.java))
        }
        fun skip2About(){
            startActivity(Intent(activity, AboutActivity::class.java))
        }
        fun ConnectWallet(){

            val authorize = Authorize()
            //已废弃
            //authorize.setBlockchain(CHAIN);
            //标识链
            //已废弃
            //authorize.setBlockchain(CHAIN);
            //标识链
            val blockchains: MutableList<Blockchain> = ArrayList()
            blockchains.add(Blockchain("ethereum", "56"))
            authorize.blockchains = blockchains

            authorize.dappName = "MetaStore"
            authorize.dappIcon = "https://eosknights.io/img/icon.png"
            authorize.actionId = "11"
            authorize.memo = "MetaStore"
            TPManager.getInstance().authorize(activity, authorize, object : TPListener {
                override fun onSuccess(s: String) {
                    Log.d("TP-WALLET onSuccess",s)

                   var result= GsonUtil.fromJSON(s,TpWalletConnectResult::class.java)
                    meViewModel?.account?.set(result.account)

                }

                override fun onError(s: String) {
                }

                override fun onCancel(s: String) {
                }
            })


        }




     }








}