package com.netease.lib_network;


import static com.netease.lib_network.constants.config.host;
import static com.netease.lib_network.constants.config.server_port;

import com.zksg.lib_api.beans.AppInfoBean;
import com.zksg.lib_api.beans.CommonResponse;
import com.zksg.lib_api.beans.DataResponse;
import com.zksg.lib_api.beans.NotifyBean;
import com.zksg.lib_api.beans.ResponsPublishApk;
import com.zksg.lib_api.login.LoginBean;

import java.util.ArrayList;

import io.reactivex.Single;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiService {


    String BASE_URL = "http://"+host+":"+server_port;

    @GET("login/cellphone")
    Single<LoginBean>  login(@Query("phone") String phone, @Query("password") String password);
    @POST("/mst/createAppinfo")
    Single<ResponsPublishApk> commitPublish(@Body RequestBody requestBody);
    @GET("/mst/getAppinfoList")
    Single<CommonResponse<DataResponse<ArrayList<AppInfoBean>>>> getAppinfoList(@Query("page") int page, @Query("pageSize") int pageSize);


    @GET("/mst/getAppinfoList")
    Single<CommonResponse<DataResponse<ArrayList<AppInfoBean>>>> getAppinfoList(@Query("page") int page, @Query("pageSize") int pageSize,@Query("app_download_count") int app_download_count);


    @GET("/mst/getAppinfoList")
    Single<CommonResponse<DataResponse<ArrayList<AppInfoBean>>>> getAppinfoList(@Query("page") int page, @Query("pageSize") int pageSize,@Query("app_category") String category);

    @GET("/mst/getAppinfoList")
    Single<CommonResponse<DataResponse<ArrayList<AppInfoBean>>>> getAppinfoListSearch(@Query("page") int page, @Query("pageSize") int pageSize,@Query("app_name") String app_name);


    @GET("/mst/getAppinfoList")
    Single<CommonResponse<DataResponse<ArrayList<AppInfoBean>>>> getAppinfoListRanking(@Query("page") int page, @Query("pageSize") int pageSize,@Query("sort") String sort,@Query("order") String order);

    @GET("/mst/getAppinfoList")
    Single<CommonResponse<DataResponse<ArrayList<AppInfoBean>>>> getAppinfoListRecentPublish(@Query("page") int page, @Query("pageSize") int pageSize,@Query("sort") String sort,@Query("order") String order);


    @PUT("/mst/updateAppinfo")
    Single<CommonResponse> updateAppinfo(@Body RequestBody requestBody);

    @GET("/notify/getNotifyList")
    Single<CommonResponse<DataResponse<ArrayList<NotifyBean>>>> getNotifyList(@Query("page") int page, @Query("pageSize") int pageSize);


}
