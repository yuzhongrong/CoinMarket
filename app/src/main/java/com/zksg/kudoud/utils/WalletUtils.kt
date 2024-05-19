package com.zksg.kudoud.utils

import android.os.Build
import android.text.TextUtils
import androidx.annotation.RequiresApi
import com.netease.lib_common_ui.utils.GsonUtil
import com.paymennt.crypto.bip32.wallet.AbstractWallet
import com.tencent.mmkv.MMKV
import com.zksg.kudoud.state.SharedViewModel
import com.zksg.kudoud.utils.manager.SimpleWallet
import com.zksg.kudoud.utils.manager.SolanaWalletManager
import com.zksg.kudoud.wallet.data.SolanaAccount
import com.zksg.kudoud.wallet.keystore.KeystoreManager
import com.zksg.kudoud.wallet.wallet.SolanaWallet
import java.math.BigDecimal

object WalletUtils {
    @JvmStatic
    fun getAllLocalSimpleWalletByNetwork(group: String?): List<SimpleWallet>? {
        return if (TextUtils.isEmpty(group)) null else try {
            val allkey = MMKV.mmkvWithID(group).allKeys()
            if (allkey == null || allkey.size == 0) return null
            MapUtils.mapToList(group, allkey)
        } catch (e: Exception) {
//            throw new RuntimeException(e);
            null
        }
    }

    fun getCurrentSimpleWallet(sharedViewModel: SharedViewModel): SimpleWallet {
        val selectWallet = sharedViewModel.oneSelectWallet.value
        val network = selectWallet!!.netwrokgroup
        val keyAlias = selectWallet.keyAlias
        return SolanaWalletManager.getOneSimpleWalletFromMMKV(network, keyAlias)
    }

    fun getSolanaAccount(mSharedViewModel: SharedViewModel,pwd:String): SolanaAccount? {

        try {
            //获取当前钱包
            var wallet=getCurrentSimpleWallet(mSharedViewModel)
            //根据钱包去找到加密数据 并解密
            //这个组提供查询获取加密数据功能--例如公钥私钥
            var encryptedWallet= MMKV.defaultMMKV().decodeBytes(wallet.keyAlias)

            var decryptWallet=SolanaWalletManager.decrypt(encryptedWallet,wallet.keyAlias,pwd)
            var solwallet= GsonUtil.fromJSON(String(decryptWallet), SolanaWallet::class.java)
            var solanaAccount= SolanaAccount(solwallet.getPrivateKey(0, AbstractWallet.Chain.EXTERNAL,null))
            return solanaAccount
        }catch (e:Exception){
            return null
        }

    }


    fun sol2lamports(sol:String):Long{

        var sol = BigDecimal(sol)
        var SOL_FACTOR: BigDecimal = BigDecimal("1000000000")

        var lamports: BigDecimal = sol.multiply(SOL_FACTOR)

        // 保留整数部分
        return lamports.toLong()
    }



}