package com.zksg.kudoud.state.load;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kunminx.architecture.domain.message.MutableResult;

public class BaseLoadingViewModel extends ViewModel {

    //加载动画
//    public final ObservableBoolean loadingVisible = new ObservableBoolean();

    public final MutableResult<Boolean> loadingVisible = new MutableResult<>();


    public final ObservableField<BaseQuickAdapter> adapter = new ObservableField<>();

}
