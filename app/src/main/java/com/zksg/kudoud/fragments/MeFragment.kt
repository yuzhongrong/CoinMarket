package com.zksg.kudoud.fragments

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
import com.zksg.kudoud.databinding.FragmentMeBinding
import com.zksg.kudoud.state.MeFragmentViewModel

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


     }








}