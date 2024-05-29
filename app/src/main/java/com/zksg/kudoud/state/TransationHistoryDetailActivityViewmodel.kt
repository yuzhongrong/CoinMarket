package com.zksg.kudoud.state

import android.annotation.SuppressLint
import android.os.Build
import android.util.Base64
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.ThreadUtils.runOnUiThread
import com.blankj.utilcode.util.ToastUtils
import com.google.gson.Gson
import com.kunminx.architecture.domain.message.MutableResult
import com.kunminx.architecture.utils.Utils
import com.netease.lib_common_ui.utils.GsonUtil
import com.paymennt.crypto.bip32.wallet.AbstractWallet
import com.zksg.kudoud.wallet.api.rpc.Cluster
import com.zksg.kudoud.wallet.api.rpc.SolanaRpcClient
import com.zksg.kudoud.wallet.data.SolanaAccount
import com.zksg.kudoud.wallet.data.SolanaPublicKey
import com.zksg.kudoud.wallet.program.TokenProgram
import com.tencent.mmkv.MMKV
import com.zksg.kudoud.callback.WalletCreateCallback
import com.zksg.kudoud.contants.CoinType
import com.zksg.kudoud.state.load.BaseLoadingViewModel
import com.zksg.kudoud.utils.ObjectSerializationUtils
import com.zksg.kudoud.utils.manager.SimpleWallet
import com.zksg.kudoud.utils.manager.SolanaWalletManager
import com.zksg.kudoud.wallet.api.rpc.types.ConfigObjects
import com.zksg.kudoud.wallet.api.rpc.types.ConfigObjects.Filter
import com.zksg.kudoud.wallet.api.rpc.types.ConfigObjects.Memcmp
import com.zksg.kudoud.wallet.api.rpc.types.DataSize
import com.zksg.kudoud.wallet.api.rpc.types.RpcSendTransactionConfig
import com.zksg.kudoud.wallet.constants.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.bitcoinj.core.Base58
import com.kunminx.architecture.ui.state.State
import com.netease.lib_network.entitys.BroadcastRequest
import com.netease.lib_network.entitys.JupToken
import com.netease.lib_network.entitys.TransationHistoryEntity
import com.zksg.kudoud.R
import com.zksg.kudoud.callback.WalletCusTokenInfo
import com.zksg.kudoud.entitys.UiWalletToken
import com.zksg.kudoud.repository.DataRepository
import com.zksg.kudoud.wallet.data.SolanaTransaction
import com.zksg.kudoud.wallet.program.SystemProgram
import com.zksg.kudoud.wallet.utils.SolanaConverter
import java.math.BigDecimal


/**
 * //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
 */
class TransationHistoryDetailActivityViewmodel : BaseLoadingViewModel() {

    var history=ObservableField<TransationHistoryEntity>()
    var wallet=ObservableField("")
}