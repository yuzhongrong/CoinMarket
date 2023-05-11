package com.zksg.kudoud.dialogs;

import android.content.Context;

import androidx.annotation.NonNull;

import com.lxj.xpopup.core.BottomPopupView;
import com.zksg.kudoud.R;

public class ControlDashboardDialog extends BottomPopupView {
    public ControlDashboardDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_dashboad_setting;
    }
}
