package com.zksg.kudoud.state;

import com.kunminx.architecture.ui.state.State;
import com.zksg.kudoud.state.load.BaseLoadingViewModel;

/**
 *  //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
 */
public class BabyDetailViewModel extends BaseLoadingViewModel {

    public State<String> name=new State<>("小年糕");
    public State<String> gender=new State<>("男");
    public State<String> birthday=new State<>("2022-02-22");
    public State<String> sex=new State<>("22");
    public State<String> hight=new State<>("173");
    public State<String> weight=new State<>("75");

}
