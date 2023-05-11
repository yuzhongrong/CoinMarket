package com.netease.lib_network;


import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/** TODO:tip 主要是为了方便去掉 loginDispos 手动管理取消网络请求 如果使用kotlin 的 CloseableCoroutineScope 直接使用这个类去订阅结果
 * Describe:
 * <p></p>
 *
 * @author zhangruiping
 * @Date 2021/11/22
 */
public abstract class MySimpleObserver<T> extends SimpleObserver<T> {
    private Disposable dispos;
    @Override
    public void onSubscribe(@NonNull Disposable d) {
        dispos=d;
    }

    @Override
    public void onSuccess(@NonNull T t) {
        onSuccessed(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        onFailure(e);
    }

    protected abstract void onSuccessed(@NonNull T t);
    protected abstract void onFailed(ExceptionHandle.ResponseThrowable errorMsg);


    protected void onFailure(Throwable e) {
        if (e instanceof ExceptionHandle.ResponseThrowable) {
            ExceptionHandle.ResponseThrowable throwable = (ExceptionHandle.ResponseThrowable) e;
            onFailed(throwable);
        } else {
            e.printStackTrace();
        }
    }
}
