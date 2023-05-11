package com.zksg.kudoud.dialogs;

import android.content.Context;

import androidx.annotation.NonNull;

import com.lxj.xpopup.core.BottomPopupView;
import com.zksg.kudoud.R;

public class ControLightleverDialog extends BottomPopupView {
    public ControLightleverDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_light_level_setting;
    }
}
