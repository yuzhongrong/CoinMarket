package com.zksg.kudoud.state;

import androidx.lifecycle.ViewModel;

import com.kunminx.architecture.domain.message.MutableResult;
import com.zksg.kudoud.entitys.WalletNetworkEntity;
import com.zksg.kudoud.utils.manager.SimpleWallet;

import java.util.List;

/**
 *  //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
 */
public class WalletManagerActivityViewModel extends ViewModel {
//    public ObservableField<List<FeedTip>> datas=new ObservableField<>();

    public MutableResult<List<WalletNetworkEntity>> leftDatas=new MutableResult<>();

    public MutableResult<List<SimpleWallet>> rightDatas=new MutableResult<>();


}
