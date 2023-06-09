package com.zksg.kudoud.fragments


import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blankj.utilcode.util.ToastUtils
import com.kunminx.architecture.ui.page.BaseFragment
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zksg.kudoud.BR
import com.zksg.kudoud.R
import com.zksg.kudoud.activitys.*
import com.zksg.kudoud.state.MeFragmentViewModel
import com.zksg.kudoud.utils.TpWalletUtils


class MeFragment : BaseFragment() {

    private var meViewModel: MeFragmentViewModel? = null


    override fun initViewModel() {
        meViewModel = getFragmentScopeViewModel(MeFragmentViewModel::class.java)

    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_mine, BR.vm, meViewModel!!)
            .addBindingParam(BR.click, ClickProxy())
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return super.onCreateView(inflater, container, savedInstanceState)
    }


    public inner class ClickProxy {
        fun testClick() {
            startActivity(Intent(activity, DeviceSettingActivity::class.java))
        }

        fun skip2BabyDetail() {

            startActivity(Intent(activity, BabyDetailActivity::class.java))
        }

        fun skip2Feed() {

            startActivity(Intent(activity, FeedTippctivity::class.java))
        }

        fun skip2health() {
            startActivity(Intent(activity, HealthRecordActivity::class.java))

        }

        fun skip2More() {

            startActivity(Intent(activity, MoreActivity::class.java))
        }

        fun skip2About() {
            startActivity(Intent(activity, AboutActivity::class.java))
        }

        fun skip2UploadPage() {
            startActivity(Intent(activity, AppUploadActivity::class.java))
        }

        fun loginTpWallet() {
            TpWalletUtils.loginWallet(activity, meViewModel)
        }

        fun GoPancakeSwapDex() {
            val url =
                "https://pancakeswap.finance/swap?chain=bscTestnet&outputCurrency=0x064C9A1c40CE7575d9Ca49121DAb6E2c6Cc757bB"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        fun GoTelegram() {
            val groupLink = "https://t.me/MetaStore1" // 将MetaStore1替换为实际的群链接
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(groupLink))
//            intent.setPackage("org.telegram.messenger") // 使用Telegram的包名
            intent.setPackage("org.telegram.messenger.web")//新包名
            // 检查设备是否安装了Telegram应用程序
            val packageManager: PackageManager = context?.packageManager!!
            val activities: List<ResolveInfo> = packageManager.queryIntentActivities(intent, 0)
            val isTelegramInstalled = !activities.isEmpty()
            if (isTelegramInstalled) {
                startActivity(intent)
            } else {
                // 在这里可以添加处理设备未安装Telegram的逻辑，例如提示用户下载Telegram应用程序
                ToastUtils.showShort(getString(R.string.str_go_metastore))
            }


        }

        fun GoTwitter(){

            val twitterChannelLink =
                "https://twitter.com/_metastore?s=09" // 将 ChannelName 替换为实际的 Twitter 频道名称或链接


            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(twitterChannelLink))
            intent.setPackage("com.twitter.android") // 使用 Twitter 应用的包名

            startActivity(intent)
        }

    }


}