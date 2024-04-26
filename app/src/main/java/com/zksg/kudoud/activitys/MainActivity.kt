package com.zksg.kudoud.activitys

//import com.kunminx.architecture.ui.page.StateHolder
//import com.kunminx.architecture.ui.state.State
//import com.kunminx.architecture.utils.BarUtils
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.kunminx.architecture.ui.page.BaseActivity
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.tencent.mmkv.MMKV
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.contants.CoinType
import com.zksg.kudoud.databinding.ActivityMainBinding
import com.zksg.kudoud.entitys.SelectWalletEntity
import com.zksg.kudoud.fragments.CategoryFragment
import com.zksg.kudoud.fragments.HomeFragment
import com.zksg.kudoud.fragments.MeFragment
import com.zksg.kudoud.fragments.RankingFragment
import com.zksg.kudoud.state.MainActivityViewModel
import com.zksg.kudoud.state.SharedViewModel
import com.zksg.kudoud.utils.StatusBarUtil
import com.zksg.kudoud.widgets.NavigateTabBar


class MainActivity : BaseActivity() {
    private var mMainActivityViewModel: MainActivityViewModel? = null

    var mSharedViewModel: SharedViewModel? = null
    override fun initViewModel() {
        StatusBarUtil.setTranslucent(this)
        mMainActivityViewModel = getActivityScopeViewModel(
            MainActivityViewModel::class.java
        )

        mSharedViewModel =
            getApplicationScopeViewModel(SharedViewModel::class.java)

    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_main,BR.vm, mMainActivityViewModel!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView(binding as ActivityMainBinding)
    }

    private fun initView(mainBinding: ActivityMainBinding) {

        //临时debug去掉
        var group=mainBinding.root as ViewGroup
        group.getChildAt(group.childCount-1).visibility= View.GONE

        val HOME_PAGE = getString(R.string.str_home)
        mainBinding.homeNavigate!!.addTab(HomeFragment::class.java,
            NavigateTabBar.TabParam(
                resources.getColor(R.color.white),
                R.mipmap.home_select,
                R.mipmap.home_normal,
                HOME_PAGE
            )
        )

        val CATEGORY_PAGE = getString(R.string.str_category)
        mainBinding.homeNavigate!!.addTab(CategoryFragment::class.java,
            NavigateTabBar.TabParam(
                resources.getColor(R.color.white),
                R.mipmap.app_normal,
                R.mipmap.app_select,
                CATEGORY_PAGE
            )
        )

        val CJ_PAGE = getString(R.string.str_markets)
        mainBinding.homeNavigate!!.addTab(RankingFragment::class.java,
            NavigateTabBar.TabParam(
                resources.getColor(R.color.white),
                R.mipmap.app_ranking_normal,
                R.mipmap.app_ranking_select,
                CJ_PAGE
            )
        )
        val ME_PAGE = getString(R.string.str_chance)
        mainBinding.homeNavigate!!.addTab(MeFragment::class.java,
            NavigateTabBar.TabParam(
                resources.getColor(R.color.white),
                R.mipmap.me_normal,
                R.mipmap.me_select,
                ME_PAGE
            )
        )

        val ME_ASSET = getString(R.string.str_wallet)
        mainBinding.homeNavigate!!.addTab(MeFragment::class.java,
            NavigateTabBar.TabParam(
                resources.getColor(R.color.white),
                R.mipmap.me_normal,
                R.mipmap.me_select,
                ME_ASSET
            )
        )


        mainBinding.homeNavigate!!.setTabSelectListener(object : NavigateTabBar.OnTabSelectedListener {
            override fun onTabSelected(holder: NavigateTabBar.ViewHolder) {
                mainBinding.homeNavigate!!.showFragment(holder)
            }
        })

        mainBinding.homeNavigate!!.setSelectedTabTextColor(getColor(R.color.colorAccent))
        mainBinding.homeNavigate!!.setTabTextColor(getColor(R.color.c_a0a0ab))

        initData()


    }



    fun initData(){
        //获取保存的当前选中钱包 第一次默认solana网络
        var network=MMKV.mmkvWithID("currentWallet").decodeString("netwrokgroup",CoinType.SOLANA.key)
        var keyAlias= MMKV.mmkvWithID("currentWallet").decodeString("keyAlias","")

       var localSelectWallet= SelectWalletEntity(network,keyAlias)
        //初始化当前选中钱包
        mSharedViewModel!!.selectWallet.postValue(localSelectWallet)
    }

//    class States : StateHolder() {
//        //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
//        //如这么说无体会，详见 https://xiaozhuanlan.com/topic/9816742350
//        val isDrawerOpened = State(false)
//        val openDrawer = State(false)
//        val allowDrawerOpen = State(true)
//    }






}