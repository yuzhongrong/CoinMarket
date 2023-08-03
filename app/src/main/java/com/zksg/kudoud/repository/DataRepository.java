/*
 * Copyright 2018-present KunMinX
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zksg.kudoud.repository;

import com.google.gson.Gson;
import com.kunminx.architecture.data.response.DataResult;
import com.kunminx.architecture.data.response.ResponseStatus;
import com.kunminx.architecture.data.response.ResultSource;
import com.netease.lib_network.ApiEngine;
import com.netease.lib_network.ExceptionHandle;
import com.netease.lib_network.MySimpleObserver;
import com.zksg.kudoud.repository.example.User;
import com.zksg.lib_api.beans.AppInfoBean;
import com.zksg.lib_api.beans.BannerBean;
import com.zksg.lib_api.beans.CommonResponse;
import com.zksg.lib_api.beans.DataResponse;
import com.zksg.lib_api.beans.NotifyBean;
import com.zksg.lib_api.beans.ResponsPublishApk;
import com.zksg.lib_api.beans.UpgradeBean;
import com.zksg.lib_api.login.LoginBean;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/** TODO:tip 唯一数据源 所有的网络请求在这里实现,在viewmode 调用这些方法 可参考login方法
 * Create by KunMinX at 19/10/29
 *
 */
public class DataRepository {

    private static final DataRepository S_REQUEST_MANAGER = new DataRepository();

    private DataRepository() {
    }

    public static DataRepository getInstance() {
        return S_REQUEST_MANAGER;
    }

    /**
     * TODO: 建议在 DataRepository 使用 DataResult 而非 LiveData 来返回结果：
     * liveData 是专用于页面开发的、用于解决生命周期安全问题的组件，
     * 有时候数据并非一定是通过 liveData 来分发给页面，也可能是通过别的组件去通知给非页面的东西，
     * 这时候 repo 方法中内定通过 liveData 分发就不太合适，不如一开始就规定不在数据层通过 liveData 返回结果。
     * <p>
     * 如果这样说还不理解的话，详见《如何让同事爱上架构模式、少写 bug 多注释》篇的解析
     * https://xiaozhuanlan.com/topic/8204519736
     *
     * @param result result
     */
//    public void getFreeMusic(DataResult.Result<TestAlbum> result) {
//        Gson gson = new Gson();
//        Type type = new TypeToken<TestAlbum>() {
//        }.getType();
//        TestAlbum testAlbum = gson.fromJson(Utils.getApp().getString(R.string.free_music_json), type);
//        result.onResult(new DataResult<>(testAlbum, new ResponseStatus()));
//    }

//    public void getLibraryInfo(DataResult.Result<List<LibraryInfo>> result) {
//        Gson gson = new Gson();
//        Type type = new TypeToken<List<LibraryInfo>>() {
//        }.getType();
//        List<LibraryInfo> list = gson.fromJson(Utils.getApp().getString(R.string.library_json), type);
//        result.onResult(new DataResult<>(list, new ResponseStatus()));
//    }

    /**
     * TODO tip：这里我们采用了kotlin的 CloseableCoroutineScope 来实现自动取消请求
     */
    //登陆example
    public void login(User user, DataResult.Result<LoginBean> result){

        ApiEngine.getInstance().getApiService().login(user.getName(), user.getPassword())
                .compose(ApiEngine.getInstance().applySchedulers())
//                .delay(3, TimeUnit.SECONDS)
                .subscribe(new MySimpleObserver<LoginBean>() {
                    @Override
                    protected void onSuccessed(LoginBean bean) {
                        ResponseStatus responseStatus = new ResponseStatus(
                                String.valueOf(bean.getCode()), bean.getCode() == 200, ResultSource.NETWORK);
                        result.onResult(new DataResult(bean, responseStatus));
                    }
                    @Override
                    protected void onFailed(ExceptionHandle.ResponseThrowable err) {
                        ResponseStatus responseStatus = new ResponseStatus(String.valueOf(err.code), err.getMessage(),false,ResultSource.NETWORK);
//                        loginData.postValue(new DataResult<>(null, responseStatus));
                        result.onResult(new DataResult(null, responseStatus));
                    }
                });

    }



 /*
  *因为参数过多所以要传递对象
  *发布apk信息接口
  */
    public void commitPublishApk(AppInfoBean apkinfo, DataResult.Result<ResponsPublishApk> result){
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), new Gson().toJson(apkinfo));
        ApiEngine.getInstance().getApiService().commitPublish(requestBody)
                .compose(ApiEngine.getInstance().applySchedulers())
                .delay(3, TimeUnit.SECONDS)
                .subscribe(new MySimpleObserver<ResponsPublishApk>() {
                    @Override
                    protected void onSuccessed(ResponsPublishApk bean) {
                        ResponseStatus responseStatus = new ResponseStatus(
                                String.valueOf(bean.getCode()), bean.getCode() == 0, ResultSource.NETWORK);
                        result.onResult(new DataResult(bean, responseStatus));
                    }
                    @Override
                    protected void onFailed(ExceptionHandle.ResponseThrowable err) {
                        ResponseStatus responseStatus = new ResponseStatus(String.valueOf(err.code), err.getMessage(),false,ResultSource.NETWORK);
                        result.onResult(new DataResult(null, responseStatus));
                    }
                }

                );
    }

    //获取数据看前50条数据


    public void getAppinfoList(int page,int pageSize, DataResult.Result<CommonResponse<DataResponse<ArrayList<AppInfoBean>>>> result){
        ApiEngine.getInstance().getApiService().getAppinfoList(page,pageSize)
                .compose(ApiEngine.getInstance().applySchedulers())
//                .delay(3, TimeUnit.SECONDS)
                .subscribe(new MySimpleObserver<CommonResponse<DataResponse<ArrayList<AppInfoBean>>>>() {
                    @Override
                    protected void onSuccessed(CommonResponse<DataResponse<ArrayList<AppInfoBean>>> bean) {
                        ResponseStatus responseStatus = new ResponseStatus(
                                String.valueOf(bean.getCode()), bean.getCode() == 0, ResultSource.NETWORK);
                        result.onResult(new DataResult(bean, responseStatus));
                    }

                    @Override
                    protected void onFailed(ExceptionHandle.ResponseThrowable err) {
                        ResponseStatus responseStatus = new ResponseStatus(String.valueOf(err.code), err.getMessage(),false,ResultSource.NETWORK);
                        result.onResult(new DataResult(null, responseStatus));
                    }
                });
    }


    //get all app by downloadcount limit
    public void getAppinfoList(int page,int pageSize,int downloadcount,DataResult.Result<CommonResponse<DataResponse<ArrayList<AppInfoBean>>>> result){
        ApiEngine.getInstance().getApiService().getAppinfoList(page,pageSize,downloadcount)
                .compose(ApiEngine.getInstance().applySchedulers())
//                .delay(1, TimeUnit.SECONDS)
                .subscribe(new MySimpleObserver<CommonResponse<DataResponse<ArrayList<AppInfoBean>>>>() {
                    @Override
                    protected void onSuccessed(CommonResponse<DataResponse<ArrayList<AppInfoBean>>> bean) {
                        ResponseStatus responseStatus = new ResponseStatus(
                                String.valueOf(bean.getCode()), bean.getCode() == 0, ResultSource.NETWORK);
                        result.onResult(new DataResult(bean, responseStatus));
                    }

                    @Override
                    protected void onFailed(ExceptionHandle.ResponseThrowable err) {
                        ResponseStatus responseStatus = new ResponseStatus(String.valueOf(err.code), err.getMessage(),false,ResultSource.NETWORK);
                        result.onResult(new DataResult(null, responseStatus));
                    }
                });
    }



    public void getAppinfoList(int page,int pageSize,String catory,DataResult.Result<CommonResponse<DataResponse<ArrayList<AppInfoBean>>>> result){
        ApiEngine.getInstance().getApiService().getAppinfoList(page,pageSize,catory)
                .compose(ApiEngine.getInstance().applySchedulers())
//                .delay(3, TimeUnit.SECONDS)
                .subscribe(new MySimpleObserver<CommonResponse<DataResponse<ArrayList<AppInfoBean>>>>() {
                    @Override
                    protected void onSuccessed(CommonResponse<DataResponse<ArrayList<AppInfoBean>>> bean) {
                        ResponseStatus responseStatus = new ResponseStatus(
                                String.valueOf(bean.getCode()), bean.getCode() == 0, ResultSource.NETWORK);
                        result.onResult(new DataResult(bean, responseStatus));
                    }

                    @Override
                    protected void onFailed(ExceptionHandle.ResponseThrowable err) {
                        ResponseStatus responseStatus = new ResponseStatus(String.valueOf(err.code), err.getMessage(),false,ResultSource.NETWORK);
                        result.onResult(new DataResult(null, responseStatus));
                    }
                });
    }


    public void getAppinfoListSearch(int page,int pageSize,String app_name,DataResult.Result<CommonResponse<DataResponse<ArrayList<AppInfoBean>>>> result){
        ApiEngine.getInstance().getApiService().getAppinfoListSearch(page,pageSize,app_name)
                .compose(ApiEngine.getInstance().applySchedulers())

//                .delay(3, TimeUnit.SECONDS)
                .subscribe(new MySimpleObserver<CommonResponse<DataResponse<ArrayList<AppInfoBean>>>>() {
                    @Override
                    protected void onSuccessed(CommonResponse<DataResponse<ArrayList<AppInfoBean>>> bean) {
                        ResponseStatus responseStatus = new ResponseStatus(
                                String.valueOf(bean.getCode()), bean.getCode() == 0, ResultSource.NETWORK);
                        result.onResult(new DataResult(bean, responseStatus));
                    }

                    @Override
                    protected void onFailed(ExceptionHandle.ResponseThrowable err) {
                        ResponseStatus responseStatus = new ResponseStatus(String.valueOf(err.code), err.getMessage(),false,ResultSource.NETWORK);
                        result.onResult(new DataResult(null, responseStatus));
                    }
                });
    }


    public void getAppinfoOneSearch(int page,int pageSize,String app_file,DataResult.Result<CommonResponse<DataResponse<ArrayList<AppInfoBean>>>> result){
        ApiEngine.getInstance().getApiService().getAppinfoOneSearch(page,pageSize,app_file)
                .compose(ApiEngine.getInstance().applySchedulers())

//                .delay(3, TimeUnit.SECONDS)
                .subscribe(new MySimpleObserver<CommonResponse<DataResponse<ArrayList<AppInfoBean>>>>() {
                    @Override
                    protected void onSuccessed(CommonResponse<DataResponse<ArrayList<AppInfoBean>>> bean) {
                        ResponseStatus responseStatus = new ResponseStatus(
                                String.valueOf(bean.getCode()), bean.getCode() == 0, ResultSource.NETWORK);
                        result.onResult(new DataResult(bean, responseStatus));
                    }

                    @Override
                    protected void onFailed(ExceptionHandle.ResponseThrowable err) {
                        ResponseStatus responseStatus = new ResponseStatus(String.valueOf(err.code), err.getMessage(),false,ResultSource.NETWORK);
                        result.onResult(new DataResult(null, responseStatus));
                    }
                });
    }


    public void getAppinfoListSearchDelay(int page,int pageSize,String app_name,DataResult.Result<CommonResponse<DataResponse<ArrayList<AppInfoBean>>>> result){
        ApiEngine.getInstance().getApiService().getAppinfoListSearch(page,pageSize,app_name)
                .compose(ApiEngine.getInstance().applySchedulers())
                .delay(3, TimeUnit.SECONDS)
                .subscribe(new MySimpleObserver<CommonResponse<DataResponse<ArrayList<AppInfoBean>>>>() {
                    @Override
                    protected void onSuccessed(CommonResponse<DataResponse<ArrayList<AppInfoBean>>> bean) {
                        ResponseStatus responseStatus = new ResponseStatus(
                                String.valueOf(bean.getCode()), bean.getCode() == 0, ResultSource.NETWORK);
                        result.onResult(new DataResult(bean, responseStatus));
                    }

                    @Override
                    protected void onFailed(ExceptionHandle.ResponseThrowable err) {
                        ResponseStatus responseStatus = new ResponseStatus(String.valueOf(err.code), err.getMessage(),false,ResultSource.NETWORK);
                        result.onResult(new DataResult(null, responseStatus));
                    }
                });
    }



    public void getAppinfoListForOrder(int page,int pageSize,String sort,String order,DataResult.Result<CommonResponse<DataResponse<ArrayList<AppInfoBean>>>> result){
        ApiEngine.getInstance().getApiService().getAppinfoListRanking(page,pageSize,sort,order)
                .compose(ApiEngine.getInstance().applySchedulers())

//                .delay(3, TimeUnit.SECONDS)
                .subscribe(new MySimpleObserver<CommonResponse<DataResponse<ArrayList<AppInfoBean>>>>() {
                    @Override
                    protected void onSuccessed(CommonResponse<DataResponse<ArrayList<AppInfoBean>>> bean) {
                        ResponseStatus responseStatus = new ResponseStatus(
                                String.valueOf(bean.getCode()), bean.getCode() == 0, ResultSource.NETWORK);
                        result.onResult(new DataResult(bean, responseStatus));
                    }

                    @Override
                    protected void onFailed(ExceptionHandle.ResponseThrowable err) {
                        ResponseStatus responseStatus = new ResponseStatus(String.valueOf(err.code), err.getMessage(),false,ResultSource.NETWORK);
                        result.onResult(new DataResult(null, responseStatus));
                    }
                });
    }



    public void getLastNoticeForOrder(int page,int pageSize,String sort,String order,DataResult.Result<CommonResponse<DataResponse<ArrayList<NotifyBean>>>> result){
        ApiEngine.getInstance().getApiService().getLastNotify(page,pageSize,sort,order)
                .compose(ApiEngine.getInstance().applySchedulers())

//                .delay(3, TimeUnit.SECONDS)
                .subscribe(new MySimpleObserver<CommonResponse<DataResponse<ArrayList<NotifyBean>>>>() {
                    @Override
                    protected void onSuccessed(CommonResponse<DataResponse<ArrayList<NotifyBean>>> bean) {
                        ResponseStatus responseStatus = new ResponseStatus(
                                String.valueOf(bean.getCode()), bean.getCode() == 0, ResultSource.NETWORK);
                        result.onResult(new DataResult(bean, responseStatus));
                    }

                    @Override
                    protected void onFailed(ExceptionHandle.ResponseThrowable err) {
                        ResponseStatus responseStatus = new ResponseStatus(String.valueOf(err.code), err.getMessage(),false,ResultSource.NETWORK);
                        result.onResult(new DataResult(null, responseStatus));
                    }
                });
    }

    public void updateAppinfo(AppInfoBean apkinfo, DataResult.Result<CommonResponse> result){
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), new Gson().toJson(apkinfo));
        ApiEngine.getInstance().getApiService().updateAppinfo(requestBody)
                .compose(ApiEngine.getInstance().applySchedulers())
                .delay(3, TimeUnit.SECONDS)
                .subscribe(new MySimpleObserver<CommonResponse>() {
                               @Override
                               protected void onSuccessed(CommonResponse bean) {
                                   ResponseStatus responseStatus = new ResponseStatus(
                                           String.valueOf(bean.getCode()), bean.getCode() == 0, ResultSource.NETWORK);
                                   result.onResult(new DataResult(bean, responseStatus));
                               }
                               @Override
                               protected void onFailed(ExceptionHandle.ResponseThrowable err) {
                                   ResponseStatus responseStatus = new ResponseStatus(String.valueOf(err.code), err.getMessage(),false,ResultSource.NETWORK);
                                   result.onResult(new DataResult(null, responseStatus));
                               }
                           }

                );
    }



    //get all  notify list
    public void getNotifyList(int page,int pageSize,String sort,String order,DataResult.Result<CommonResponse<DataResponse<ArrayList<NotifyBean>>>> result){
        ApiEngine.getInstance().getApiService().getNotifyList(page,pageSize, sort, order)
                .compose(ApiEngine.getInstance().applySchedulers())
//                .delay(2, TimeUnit.SECONDS)
                .subscribe(new MySimpleObserver<CommonResponse<DataResponse<ArrayList<NotifyBean>>>>() {
                    @Override
                    protected void onSuccessed(CommonResponse<DataResponse<ArrayList<NotifyBean>>> bean) {
                        ResponseStatus responseStatus = new ResponseStatus(
                                String.valueOf(bean.getCode()), bean.getCode() == 0, ResultSource.NETWORK);
                        result.onResult(new DataResult(bean, responseStatus));
                    }

                    @Override
                    protected void onFailed(ExceptionHandle.ResponseThrowable err) {
                        ResponseStatus responseStatus = new ResponseStatus(String.valueOf(err.code), err.getMessage(),false,ResultSource.NETWORK);
                        result.onResult(new DataResult(null, responseStatus));
                    }
                });
    }


    public void getBannerList(DataResult.Result<CommonResponse<DataResponse<ArrayList<BannerBean>>>> result){
        ApiEngine.getInstance().getApiService().getBannerList()
                .compose(ApiEngine.getInstance().applySchedulers())
//                .delay(3, TimeUnit.SECONDS)
                .subscribe(new MySimpleObserver<CommonResponse<DataResponse<ArrayList<BannerBean>>>>() {
                    @Override
                    protected void onSuccessed(CommonResponse<DataResponse<ArrayList<BannerBean>>> bean) {
                        ResponseStatus responseStatus = new ResponseStatus(
                                String.valueOf(bean.getCode()), bean.getCode() == 0, ResultSource.NETWORK);
                        result.onResult(new DataResult(bean, responseStatus));
                    }

                    @Override
                    protected void onFailed(ExceptionHandle.ResponseThrowable err) {
                        ResponseStatus responseStatus = new ResponseStatus(String.valueOf(err.code), err.getMessage(),false,ResultSource.NETWORK);
                        result.onResult(new DataResult(null, responseStatus));
                    }
                });
    }



    public void getUpgradeInfo(DataResult.Result<CommonResponse<UpgradeBean>> result){
        ApiEngine.getInstance().getApiService().getUpgradeInfo()
                .compose(ApiEngine.getInstance().applySchedulers())
//                .delay(3, TimeUnit.SECONDS)
                .subscribe(new MySimpleObserver<CommonResponse<UpgradeBean>>() {
                    @Override
                    protected void onSuccessed(CommonResponse<UpgradeBean> bean) {
                        ResponseStatus responseStatus = new ResponseStatus(
                                String.valueOf(bean.getCode()), bean.getCode() == 0, ResultSource.NETWORK);
                        result.onResult(new DataResult(bean, responseStatus));
                    }

                    @Override
                    protected void onFailed(ExceptionHandle.ResponseThrowable err) {
                        ResponseStatus responseStatus = new ResponseStatus(String.valueOf(err.code), err.getMessage(),false,ResultSource.NETWORK);
                        result.onResult(new DataResult(null, responseStatus));
                    }
                });
    }


}
