package com.netease.lib_network;


import static com.netease.lib_network.constants.config.host;
import static com.netease.lib_network.constants.config.server_port;

import com.netease.lib_network.entitys.DexScreenTokenInfo;
import com.netease.lib_network.entitys.KlineOriginDataEntity;
import com.netease.lib_network.entitys.NewWalletToken;
import com.zksg.lib_api.beans.AppInfoBean;
import com.zksg.lib_api.beans.BannerBean;
import com.zksg.lib_api.beans.CommonResponse;
import com.zksg.lib_api.beans.DataResponse;
import com.zksg.lib_api.beans.NotifyBean;
import com.zksg.lib_api.beans.ResponsPublishApk;
import com.zksg.lib_api.beans.UpgradeBean;
import com.zksg.lib_api.login.LoginBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiService {


//    String BASE_URL = "https://api.dexscreener.com";
      String BASE_URL=" http://192.168.10.4:3000";


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
    Single<CommonResponse<DataResponse<ArrayList<AppInfoBean>>>> getAppinfoOneSearch(@Query("page") int page, @Query("pageSize") int pageSize,@Query("app_file") String app_file);


    @GET("/defi/history_price")
    Single<CommonResponse<KlineOriginDataEntity>> getKlineOriginData(@Query("address") String address, @Query("address_type") String address_type, @Query("type") String type, @Query("time_from") long time_from, @Query("time_to") long time_to );


    @GET("/mst/getAppinfoList")
    Single<CommonResponse<DataResponse<ArrayList<KlineOriginDataEntity>>>> getAppinfoListRanking(@Query("page") int page, @Query("pageSize") int pageSize,@Query("sort") String sort,@Query("order") String order);


    @GET("/notify/getNotifyList")
    Single<CommonResponse<DataResponse<ArrayList<NotifyBean>>>> getLastNotify(@Query("page") int page, @Query("pageSize") int pageSize,@Query("sort") String sort,@Query("order") String order);


    @GET("/mst/getAppinfoList")
    Single<CommonResponse<DataResponse<ArrayList<AppInfoBean>>>> getAppinfoListRecentPublish(@Query("page") int page, @Query("pageSize") int pageSize,@Query("sort") String sort,@Query("order") String order);


    @PUT("/mst/updateAppinfo")
    Single<CommonResponse> updateAppinfo(@Body RequestBody requestBody);

    @GET("/notify/getNotifyList")
    Single<CommonResponse<DataResponse<ArrayList<NotifyBean>>>> getNotifyList(@Query("page") int page, @Query("pageSize") int pageSize,@Query("sort") String sort,@Query("order") String order);

    @GET("/banner/getBannerList")
    Single<CommonResponse<DataResponse<ArrayList<BannerBean>>>> getBannerList();

    @GET("/upgrade/findUpgrade")
    Single<CommonResponse<UpgradeBean>> getUpgradeInfo();


    @GET("/latest/dex/tokens/{tokenAddresses}")
    Single<DexScreenTokenInfo> getTokenInfoForDexscreen(@Path("tokenAddresses") String address);


    @GET("/v4/price/{ids}")
    Single<DexScreenTokenInfo> getTokenInfoForJupSwap(@Path("ids") String address);
    @GET("/api/wallet/getTokenList")
    Single<CommonResponse<List<NewWalletToken>>> getWalletTokens(@Query("wallet") String wallet);

    @GET("/api/wallet/getWalletSolBalance")
    Single<CommonResponse<String>> getWalletSolBalance(@Query("wallet") String wallet);



}
