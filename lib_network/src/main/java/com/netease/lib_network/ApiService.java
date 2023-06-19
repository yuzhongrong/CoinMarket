package com.netease.lib_network;


import com.zksg.lib_api.beans.AppInfoBean;
import com.zksg.lib_api.beans.CommonResponse;
import com.zksg.lib_api.beans.DataResponse;
import com.zksg.lib_api.beans.ResponsPublishApk;
import com.zksg.lib_api.login.LoginBean;

import java.util.ArrayList;

import io.reactivex.Single;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

     String BASE_URL = "http://192.168.43.65:8888";

    @GET("login/cellphone")
    Single<LoginBean>  login(@Query("phone") String phone, @Query("password") String password);
    @POST("/mst/createAppinfo")
    Single<ResponsPublishApk> commitPublish(@Body RequestBody requestBody);
    @GET("/mst/getAppinfoList")
    Single<CommonResponse<DataResponse<ArrayList<AppInfoBean>>>> getAppinfoList(@Query("page") int page, @Query("pageSize") int pageSize);

}
