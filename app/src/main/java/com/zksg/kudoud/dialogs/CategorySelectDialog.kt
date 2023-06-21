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
        var category=resources.getIntArray(R.array.category)
        var homeitems= mutableListOf(

            HomeItem(category[0],R.mipmap.ic_metamask_select,context.getString(R.string.str_category_wallets),context.getString(R.string.str_item_wallet)),
            HomeItem(category[1],R.mipmap.ic_binance_select,context.getString(R.string.str_category_exchanges),context.getString(R.string.str_item_exchange)),
            HomeItem(category[2],R.mipmap.ic_uniswap_select1,context.getString(R.string.str_category_swaps),context.getString(R.string.str_item_swap)),
            HomeItem(category[3],R.mipmap.ic_entertainment_select,context.getString(R.string.str_category_entertrainments),context.getString(R.string.str_item_entertainment)),
            HomeItem(category[4],R.mipmap.ic_telegram,context.getString(R.string.str_category_chats),context.getString(R.string.str_item_chat)),
            HomeItem(category[5],R.mipmap.ic_etherscan,context.getString(R.string.str_category_blockchain_browser),context.getString(R.string.str_item_scan)),
            HomeItem(category[6],R.mipmap.ic_analysis_select,context.getString(R.string.str_category_at),context.getString(R.string.str_item_analysis)),
            HomeItem(category[7],R.mipmap.ic_news_select,context.getString(R.string.str_category_news),context.getString(R.string.str_item_news)),
            HomeItem(category[8],R.mipmap.ic_nft_select,context.getString(R.string.str_category_nfts),context.getString(R.string.str_item_nft)),
            HomeItem(category[9],R.mipmap.ic_vpn_select,context.getString(R.string.str_category_networks),context.getString(R.string.str_item_vpn)),

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