package com.zksg.kudoud.activitys

//import com.kunminx.architecture.ui.page.StateHolder
//import com.kunminx.architecture.ui.state.State
//import com.kunminx.architecture.utils.BarUtils
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.github.ajalt.reprint.core.Reprint
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kunminx.architecture.ui.page.BaseActivity
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.lxj.xpopup.XPopup
import com.netease.lib_network.entitys.JupToken
import com.tencent.mmkv.MMKV
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.contants.CoinType
import com.zksg.kudoud.contants.GlobalConstant.*
import com.zksg.kudoud.databinding.ActivityMainBinding
import com.zksg.kudoud.dialogs.ConfirmTransationfingprintDialog
import com.zksg.kudoud.entitys.SelectWalletEntity
import com.zksg.kudoud.entitys.UiWalletToken
import com.zksg.kudoud.fragments.*
import com.zksg.kudoud.state.MainActivityViewModel
import com.zksg.kudoud.state.SharedViewModel
import com.zksg.kudoud.utils.ObjectSerializationUtils
import com.zksg.kudoud.utils.StatusBarUtil
import com.zksg.kudoud.utils.TokenConverter
import com.zksg.kudoud.wallet.keystore.KeystoreManager
import com.zksg.kudoud.wallet.keystore.KeystoreManagerDebug
import com.zksg.kudoud.widgets.NavigateTabBar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


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

        val CJ_PAGE = getString(R.string.str_ex_swap)
        mainBinding.homeNavigate!!.addTab(ExchangeFragment::class.java,
            NavigateTabBar.TabParam(
                resources.getColor(R.color.white),
                R.mipmap.app_ranking_normal,
                R.mipmap.app_ranking_select,
                CJ_PAGE
            )
        )
//        val ME_PAGE = getString(R.string.str_chance)
//        mainBinding.homeNavigate!!.addTab(MeFragment::class.java,
//            NavigateTabBar.TabParam(
//                resources.getColor(R.color.white),
//                R.mipmap.me_normal,
//                R.mipmap.me_select,
//                ME_PAGE
//            )
//        )

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

        mSharedViewModel!!.requestSelectWallet(localSelectWallet)

        mMainActivityViewModel!!.ReqHotCointDatas("strict"){

            if(it!=null){
                //初始化热门代币
                var convertResault = TokenConverter.convertJubTokensToUiWalletTokens(it)
//                mSharedViewModel!!.walletHotCoins.postValue(convertResault)
                MMKV.mmkvWithID(GROUP_WALLET_DATAS).encode(GROUP_WALLET_DATAS_STRICT,ObjectSerializationUtils.serializeObject(convertResault))

            }else{

               var hotdatasbytes= MMKV.mmkvWithID(GROUP_WALLET_DATAS).decodeBytes(GROUP_WALLET_DATAS_STRICT,null)
                if(hotdatasbytes==null){
                    //本地也没有-这种是第一次安装app极端情况
                    var datas=parseTokenData(DEFAULT_WALLET_DATAS_JSON)
                    var convertResault = TokenConverter.convertJubTokensToUiWalletTokens(datas)
//                    mSharedViewModel!!.walletHotCoins.postValue(convertResault)
                    MMKV.mmkvWithID(GROUP_WALLET_DATAS).encode(GROUP_WALLET_DATAS_STRICT,ObjectSerializationUtils.serializeObject(convertResault))

                }else{
                    var hotdatas=ObjectSerializationUtils.deserializeObject(hotdatasbytes) as List<UiWalletToken>
//                    mSharedViewModel!!.walletHotCoins.postValue(hotdatas)
                }

            }

        }




    }

    fun parseTokenData(jsonString: String?): List<JupToken?>? {
        val gson = Gson()
        val listType = object : TypeToken<List<JupToken?>?>() {}.type
        return gson.fromJson(jsonString, listType)
    }

//    class States : StateHolder() {
//        //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
//        //如这么说无体会，详见 https://xiaozhuanlan.com/topic/9816742350
//        val isDrawerOpened = State(false)
//        val openDrawer = State(false)
//        val allowDrawerOpen = State(true)
//    }


//    @RequiresApi(Build.VERSION_CODES.O)
//    fun debug(){
//
//        try {
//            KeystoreManager.initialize()
//            Reprint.initialize(this)
//            // 加密钱包数据
//            val walletData = "This is a secret wallet data."
//            val encryptedData = KeystoreManagerDebug.encryptWalletData(walletData.toByteArray())
//            println("Encrypted Data: " + encryptedData.encryptedData)
//            println("Encrypted AES Key: " + encryptedData.encryptedAESKey)
//            System.out.println("IV: " + Base64.getEncoder().encodeToString(encryptedData.iv))
//
//
//            XPopup.Builder(this@MainActivity)
//                .autoOpenSoftInput(false)
//                .dismissOnTouchOutside(false)
//                .dismissOnBackPressed(false)
//                .moveUpToKeyboard(false)
//                .asCustom(ConfirmTransationfingprintDialog(this@MainActivity,mSharedViewModel!!,null))
//                .show()
//
//
//            // 解密钱包数据
//            val decryptedData = KeystoreManagerDebug.decryptWalletData(encryptedData)
//            val decryptedWalletData = String(decryptedData)
//            println("Decrypted Wallet Data: $decryptedWalletData")
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//
//
//    }


}