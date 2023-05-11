package com.zksg.kudoud.dialogs;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.lxj.xpopup.core.BottomPopupView;
import com.zksg.kudoud.R;

public class ControlShakeDialog extends BottomPopupView {
    public ControlShakeDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_shake_setting;
    }
}
