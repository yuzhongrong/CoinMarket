package com.zksg.kudoud.dialogs;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.lxj.xpopup.core.CenterPopupView;
import com.zksg.kudoud.R;

/** TODO:tip 这里创建场景 如何通知全局？ 必须通过mvi模式或者使用全局viewmodel
 *
 */
public class ChangeEmailDialog extends CenterPopupView {
    public ChangeEmailDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        findViewById(R.id.change_email).setOnClickListener(view -> {
            //TODO:tip删除操作
            Toast.makeText(getContext(),"111",Toast.LENGTH_SHORT).show();
        });
        findViewById(R.id.cancel).setOnClickListener(v->{
            Toast.makeText(getContext(),"222",Toast.LENGTH_SHORT).show();
            dismiss();
        });
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_change_email;
    }


}
