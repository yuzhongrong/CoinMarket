package com.zksg.kudoud.state;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

/**
 *  //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
 */
public class CreateWalletActivityViewmodel extends ViewModel {
//    public ObservableField<List<FeedTip>> datas=new ObservableField<>();
    public ObservableField<String> walletname=new ObservableField<>();
    public ObservableField<String> walletpwd=new ObservableField<>();
    public ObservableField<String> walletconfirmpwd=new ObservableField<>();

    public ObservableField<Boolean> pwd=new ObservableField<>(false);
    public ObservableField<Boolean> pwdConfirm=new ObservableField<>(false);


}
