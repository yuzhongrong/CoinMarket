package com.zksg.kudoud.state;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zksg.kudoud.adapters.CategoryPagerAdapter;
import com.zksg.lib_api.beans.EnvBean;

import java.util.Arrays;
import java.util.List;

/**
 *  //TODO tip 5：此处我们使用 "去除防抖特性" 的 ObservableField 子类 State，用以代替 MutableLiveData，
 */
public class AppDetailActivityViewModel extends ViewModel {
    public ObservableField<List<EnvBean>> datas = new ObservableField<>();
//    public ObservableField<BaseQuickAdapter> CategoryAdapter = new ObservableField<>();


    public ObservableField<CategoryPagerAdapter> adapter=new ObservableField();

    public ObservableField<List<String>> banner_detail_datas = new ObservableField<>();

    {
        banner_detail_datas.set(Arrays.asList("ipfs://QmYHats9k8EqJDQXrimedv7SXGDpyf9HJdLFb1DU3SieTf",
                "ipfs://Qmabbumer7VxgJvA8cdXgV34wWvsMv1BmHaXUSw2CsfuQf",
                "ipfs://QmRL21LFcvARFgMDLsqEUp1fUWEsXN7xEpgEsW4D98cckZ",
                "ipfs://QmQdyV1RNRiwzxCgZA1MKnVSq4XXiPD7o28qWnBe3eeiQd"


        ));
    }


}
