package com.zksg.kudoud.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.blankj.utilcode.util.Utils
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.activitys.AppDetailActivity
import com.zksg.kudoud.activitys.NotifyActivity
import com.zksg.kudoud.adapters.HomeCWAdapter_V
import com.zksg.kudoud.adapters.HomeRecentAdapter
import com.zksg.kudoud.databinding.FragmentHomeBinding
import com.zksg.kudoud.state.HomeFragmentViewModel
import com.zksg.lib_api.beans.BannerBean


class HomeFragment:BaseDialogFragment(){
    private var  homeViewModel: HomeFragmentViewModel ?=null

    override fun initViewModel() {
        homeViewModel=getFragmentScopeViewModel(HomeFragmentViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
       return DataBindingConfig(R.layout.fragment_home,BR.vm,homeViewModel!!)
           .addBindingParam(BR.click,ClickProxy()!!)
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData(binding as FragmentHomeBinding)
    }


    fun  initData(bind:FragmentHomeBinding){
        bind.convenientBanner.setOnItemClickListener {
            val mBannerBean =homeViewModel?.banner_datas?.value?.get(it)
            if (mBannerBean?.type == "0") { //跳转到网页
                if(!TextUtils.isEmpty(mBannerBean.bannerContent.targeturl)){
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(mBannerBean.bannerContent.targeturl))
                    startActivity(intent)
                }
            } else if (mBannerBean?.type == "1") { //跳转到安装的app
                val appcid = mBannerBean.bannerContent.targeturl
                if(!TextUtils.isEmpty(appcid)){
                    //请求网络查询appinfo信息 然后带到详情页面
                    homeViewModel?.getOnePublishApp(appcid)
                }

            }
        }

        homeViewModel?.mBannerClickAppinfo?.observe(this){
            if(it!=null&&it.size==1){
                var intent=Intent(activity,AppDetailActivity::class.java).putExtra("appinfo",it.get(0))
                startActivity(intent)
            }

        }
        homeViewModel?.banner_datas?.observe(this){


        }

        homeViewModel?.mPublishApks?.observe(this){
            Log.d("----mPublishApks-->",it?.size.toString())
           var dapter= homeViewModel?.todayHealthAdapter?.get() as HomeRecentAdapter
            dapter.setList(it)
        }

        homeViewModel?.mHotApks?.observe(this){
            Log.d("----mHotApks-->",it?.size.toString())
            var dapter= homeViewModel?.coininstallAdapter?.get() as HomeCWAdapter_V
            dapter.setList(it)
        }
        homeViewModel?.loadingVisible?.observe(this){
            Log.d("-loadingVisible-->",it.toString())
            if(it) showDialog() else dismissDialog()
        }






        homeViewModel?.todayHealthAdapter?.set(
            HomeRecentAdapter(R.layout.item_today_health,null)
        )


        val categorys = resources.getStringArray(R.array.category_str)
        homeViewModel?.coininstallAdapter?.set(
            HomeCWAdapter_V(
                R.layout.item_today_app_h,
                null
            ,categorys
            )
        )

//       XPopup.Builder(context)
//            .asLoading("",R.layout.delegate_normal_loading)
//            .show()
//        homeViewModel?.getRecentPublishApp(1,50)
//        homeViewModel?.getCwApps(1,50,1000)
        homeViewModel?.getHomeDatas()

    }



    inner class ClickProxy {

        fun startNotify(){
            startActivity(Intent(activity,NotifyActivity::class.java))
        }


    }



}


