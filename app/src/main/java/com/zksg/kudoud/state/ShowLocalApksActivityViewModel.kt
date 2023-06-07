package com.zksg.kudoud.state

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kunminx.architecture.data.response.DataResult
import com.kunminx.architecture.domain.message.MutableResult
import com.kunminx.architecture.ui.page.StateHolder
import com.zksg.kudoud.beans.FileApkItem
import com.zksg.kudoud.state.load.BaseLoadingViewModel
import com.zksg.kudoud.utils.FileUtils
import com.zksg.kudoud.utils.FileUtils.TYPE_APK
import com.zksg.lib_api.beans.EnvBean
import com.zksg.lib_api.login.LoginBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
 */
class ShowLocalApksActivityViewModel : BaseLoadingViewModel() {

    @JvmField
    var mApksAdapter = ObservableField<BaseQuickAdapter<*, *>>()
    var mloginResult = MutableResult<List<FileApkItem>>()

    fun loadAllLocalApks(mContext:Context) {
        viewModelScope.launch {
            loadingVisible.set(true)
            val result = withContext(Dispatchers.IO) {
                // 在后台线程上执行耗时操作，比如网络请求
                FileUtils.getAllApkFilePaths(mContext,TYPE_APK)

            }
            mloginResult.value=result
            loadingVisible.set(false)

        }
    }

}