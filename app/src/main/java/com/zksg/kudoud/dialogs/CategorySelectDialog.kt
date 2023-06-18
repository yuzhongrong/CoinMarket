package com.zksg.kudoud.dialogs

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.lxj.xpopup.core.BottomPopupView
import com.zksg.kudoud.R
import com.zksg.kudoud.adapters.CategoryAdapter_V
import com.zksg.kudoud.state.AppUploadActivityViewModel
import com.zksg.lib_api.beans.HomeItem

class CategorySelectDialog(context: Context,vm: AppUploadActivityViewModel) : BottomPopupView(context) {
    private var mViewmodel=vm
    override fun onCreate() {
        super.onCreate()

        var mRecycle=findViewById<RecyclerView>(R.id.rv_select)
        var homeitems= mutableListOf(

            HomeItem(0,R.mipmap.ic_metamask_select,"Wallets",context.getString(R.string.str_item_wallet)),
            HomeItem(1,R.mipmap.ic_binance_select,"Exchanges",context.getString(R.string.str_item_exchange)),
            HomeItem(2,R.mipmap.ic_uniswap_select1,"Swaps",context.getString(R.string.str_item_swap)),
            HomeItem(3,R.mipmap.ic_entertainment_select,"EnterTrainments",context.getString(R.string.str_item_entertainment)),
            HomeItem(4,R.mipmap.ic_telegram,"Chats",context.getString(R.string.str_item_chat)),
            HomeItem(5,R.mipmap.ic_etherscan,"Blockchain Browser",context.getString(R.string.str_item_scan)),
            HomeItem(6,R.mipmap.ic_analysis_select,"Analysis Tools",context.getString(R.string.str_item_analysis)),
            HomeItem(7,R.mipmap.ic_news_select,"News",context.getString(R.string.str_item_news)),
            HomeItem(8,R.mipmap.ic_nft_select,"Nfts",context.getString(R.string.str_item_nft)),
            HomeItem(9,R.mipmap.ic_vpn_select,"Networks",context.getString(R.string.str_item_vpn)),

            )
       var adapter= CategoryAdapter_V(
            R.layout.item_category_select_h,
            homeitems
        )
        adapter.addChildClickViewIds(R.id.root)
        adapter.setOnItemChildClickListener(object :OnItemChildClickListener{
            override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                val categoryId: String = homeitems.get(position).getId().toString() + ""
                val categoryStr = homeitems.get(position).value
                mViewmodel.of_category.set(categoryStr)
                mViewmodel.of_category_req.value=categoryId
                delayDismiss(100)
            }


        })

        mRecycle.adapter=adapter

    }

    override fun getImplLayoutId(): Int {
        return R.layout.dialog_category_select
    }
}