package com.zksg.kudoud.state;


import androidx.lifecycle.ViewModel;

import com.kunminx.architecture.ui.callback.ProtectedUnPeekLiveData;
import com.kunminx.architecture.ui.callback.UnPeekLiveData;
import com.kunminx.architecture.ui.state.State;
import com.zksg.kudoud.state.load.BaseLoadingViewModel;
import com.zksg.lib_api.beans.EnvBean;


 public class SharedViewModel extends ViewModel {
    private final UnPeekLiveData<EnvBean> addOneEnvNotify = new UnPeekLiveData<>();

     public ProtectedUnPeekLiveData<EnvBean> getOneEnvNotify() {
         return addOneEnvNotify;
     }
     public void requestToAddOneEnv(EnvBean env) {
         addOneEnvNotify.setValue(env);
     }
}
