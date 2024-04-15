package com.zksg.kudoud.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.blankj.utilcode.util.AppUtils
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.lxj.xpopup.XPopup
import com.tencent.mmkv.MMKV
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.activitys.*
import com.zksg.kudoud.adapters.HomeCWAdapter_V
import com.zksg.kudoud.adapters.HomeRecentAdapter
import com.zksg.kudoud.adapters.MemeCategoryPagerAdapter
import com.zksg.kudoud.beans.CommonCategoryDataEnum
import com.zksg.kudoud.databinding.FragmentHomeBinding
import com.zksg.kudoud.dialogs.TipVpnDialog
import com.zksg.kudoud.dialogs.UpgradeVersionDialog
import com.zksg.kudoud.state.HomeFragmentViewModel
import com.zksg.lib_api.beans.AppInfoBean
import com.zksg.lib_api.beans.UpgradeBean


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
        initData()
    }


    fun  initData(){
//        bind.convenientBanner.setOnItemClickListener {
//            val mBannerBean =homeViewModel?.banner_datas?.value?.get(it)
//            if (mBannerBean?.type == "2") { //利用浏览器来跳转
//                if(!TextUtils.isEmpty(mBannerBean.bannerContent.targeturl)){
//                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(mBannerBean.bannerContent.targeturl))
//                    startActivity(intent)
//                }
//            } else if (mBannerBean?.type == "1") { //跳转到安装的app
//                val appcid = mBannerBean.bannerContent.targeturl
//                if(!TextUtils.isEmpty(appcid)){
//                    //请求网络查询appinfo信息 然后带到详情页面
//                    homeViewModel?.getOnePublishApp(appcid)
//                }
//
//            }else if(mBannerBean?.type=="0"){ //使用webview加载url 不支持download等操作，适合展示一般的页面
//                if(!TextUtils.isEmpty(mBannerBean.bannerContent.targeturl)){
//                    val intent = Intent(activity, CusWebviewActivity::class.java)
//                        .putExtra("url",mBannerBean.bannerContent.targeturl)
//                        .putExtra("title",mBannerBean.bannerContent.title)
//                    startActivity(intent)
//                }
//            }
//        }

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

        homeViewModel?.mUpgradeBean?.observe(this){
            if(it!=null){
               var versionCode= AppUtils.getAppVersionCode()
                var remoteVersionCode=it.versioncode
                if(remoteVersionCode> versionCode){
                    showUpgradeTip(it)
                }else{


                }

            }
        }






        homeViewModel?.todayHealthAdapter?.set(
            HomeRecentAdapter(R.layout.item_meme_trending,null)
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


        var vpn_tip_open= MMKV.mmkvWithID("switchs").decodeBool("tip_vpn",false)
        if(!vpn_tip_open){ showTipVpn(homeViewModel!!) }else{
//            homeViewModel?.getHomeDatas()
        }



        //simulate data
        var dapter= homeViewModel?.todayHealthAdapter?.get() as HomeRecentAdapter
        var hotmmdata= arrayListOf<AppInfoBean>()
        hotmmdata.add(AppInfoBean())
        hotmmdata.add(AppInfoBean())
        hotmmdata.add(AppInfoBean())
        dapter.setList(hotmmdata)


        //meme category
        homeViewModel?.indicatorTitle?.set(
            arrayOf(

                getString(R.string.str_zx),
                getString(R.string.str_24up),
                getString(R.string.str_24down),
                getString(R.string.str_24ex)
            )
        )

        homeViewModel?.memecategoryadapter?.set(
            MemeCategoryPagerAdapter(
                childFragmentManager,
                arrayOf(
                    CommonCategoryDataEnum.ZX,
                    CommonCategoryDataEnum.UP24,
                    CommonCategoryDataEnum.DOWN24,
                    CommonCategoryDataEnum.EX24
                )
            )
        )




    }


    fun showUpgradeTip(info:UpgradeBean?){

        XPopup.Builder(requireActivity())
            .dismissOnTouchOutside(false)
            .dismissOnBackPressed(false)
            .asCustom(UpgradeVersionDialog(requireActivity(),info!!))
            .show()

    }

    fun showTipVpn(homeViewModel: HomeFragmentViewModel){

        XPopup.Builder(requireActivity())
            .dismissOnTouchOutside(false)
            .dismissOnBackPressed(false)
            .asCustom(TipVpnDialog(requireActivity(),homeViewModel))
            .show()

    }



    inner class ClickProxy {

        fun startSearch(){
            startActivity(Intent(activity, SearchActivity::class.java))
        }

        fun startNotify(){
            startActivity(Intent(activity, NotifyActivity::class.java))
        }

        fun startPreSale(){
            startActivity(Intent(activity, CoinsDetailActivity::class.java))
        }



    }



}


