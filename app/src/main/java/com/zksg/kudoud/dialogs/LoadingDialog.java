package com.zksg.kudoud.dialogs;

import android.content.Context;

import androidx.annotation.NonNull;

import com.lxj.xpopup.core.CenterPopupView;
import com.zksg.kudoud.R;

/** TODO:tip 这里创建场景 如何通知全局？ 必须通过mvi模式或者使用全局viewmodel
 *
 */
public class LoadingDialog extends CenterPopupView {
    public LoadingDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_loading_layout;
    }


}
