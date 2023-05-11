package com.zksg.kudoud.dialogs;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.lxj.xpopup.core.BottomPopupView;
import com.lxj.xpopup.core.CenterPopupView;
import com.zksg.kudoud.R;

/** TODO:tip 这里创建场景 如何通知全局？ 必须通过mvi模式或者使用全局viewmodel
 *
 */
public class EnvNameDialog extends CenterPopupView {
    public EnvNameDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        View view= findViewById(R.id.save);
        EditText editText=findViewById(R.id.edit);
        view.setOnClickListener(v -> {
            if(mOnSaveListener!=null&&editText.getText().length()>0){
                mOnSaveListener.onSave(editText.getText().toString());
            }
        });
    }


    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_envname_setting;
    }


    private OnSaveListener mOnSaveListener;
    public interface OnSaveListener{
        public void onSave(String title);
    }
    public EnvNameDialog setOnSaveListener(OnSaveListener onSaveListener){
        this.mOnSaveListener=onSaveListener;
        return this;
    }
}
