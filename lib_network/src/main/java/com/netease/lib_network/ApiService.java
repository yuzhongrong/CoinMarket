package com.netease.lib_network;


import com.netease.lib_network.entitys.ApiTokenInfo;

import com.netease.lib_network.entitys.BroadcastRequest;
import com.netease.lib_network.entitys.CommitTransation;
import com.netease.lib_network.entitys.DexScreenTokenInfo1;
import com.netease.lib_network.entitys.DexScreenTokenInfo1;
import com.netease.lib_network.entitys.JupToken;
import com.netease.lib_network.entitys.KlineOriginDataEntity;
import com.netease.lib_network.entitys.NewWalletToken;
import com.netease.lib_network.entitys.QuoEntity;
import com.netease.lib_network.entitys.QuoPubkey58Entity;
import com.netease.lib_network.entitys.ReqSwapTransation;
import com.netease.lib_network.entitys.SubmmitVerTxReqBodyEntity;
import com.netease.lib_network.entitys.SwapQueryStateResult;
import com.netease.lib_network.entitys.TransationHistoryEntity;
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
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {


//    String BASE_URL = "https://api.dexscreener.com";
      String BASE_URL=" http://192.168.10.2:3000";


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


    @GET("/api/wallet/getDexScreenTokenInfo")
    Single<CommonResponse<DexScreenTokenInfo1>> getTokenInfoForDexscreen(@Query("contract") String address);


    @GET("/v4/price/{ids}")
    Single<DexScreenTokenInfo1> getTokenInfoForJupSwap(@Path("ids") String address);
    @GET("/api/wallet/getTokenList")
    Single<CommonResponse<List<NewWalletToken>>> getWalletTokens(@Query("wallet") String wallet);

    @GET("/api/wallet/getWalletSolBalance")
    Single<CommonResponse<ApiTokenInfo>> getWalletSolBalance(@Query("wallet") String wallet,@Query("mint") String mint);

    @GET("/api/wallet/getDefaultStrict")
    Single<CommonResponse<List<JupToken>>> getHotCoinDatas(@Query("model") String model);

    @GET("/api/wallet/getCusCoinInfo")
    Single<CommonResponse<JupToken>> getCusCoinInfo(@Query("contract") String contract);
    @GET("/api/wallet/updateWalletBalance")
    Single<CommonResponse<List<NewWalletToken>>> updateWalletBalance(@Query("wallet") String wallet);


    @GET("/api/wallet/getEstimatedFee")
    Single<CommonResponse<String>> getEstimatedFee(@Query("from") String from,@Query("to") String to,@Query("amount") long amount);

    @GET("/api/wallet/getSplEstimatedFee")
    Single<CommonResponse<String>> getSplEstimatedFee(@Query("from") String from,@Query("to") String to,@Query("mint") String mint,@Query("amount") long amount);
    @GET("/api/wallet/getRentForAccount")
    Single<CommonResponse<String>> getRentForAccount(@Query("wallet") String sign);

    @GET("/api/wallet/getLatestBlockhash")
    Single<CommonResponse<String>> getLatestBlockhash();

    @POST("/api/wallet/broadcast")
    Single<CommonResponse<CommitTransation>> BroadCastTx(@Body BroadcastRequest request );





    @GET("/api/wallet/getTransations")
    Single<CommonResponse<List<TransationHistoryEntity>>> getAllTransationHistory(@Query("wallet") String wallet,@Query("before") String before);

    @GET("/api/wallet/getSplTransations")
    Single<CommonResponse<List<TransationHistoryEntity>>> getSplTransations(@Query("wallet") String wallet,@Query("mint") String mint,@Query("before") String before);


    @GET("/api/wallet/getSolTransations")
    Single<CommonResponse<List<TransationHistoryEntity>>> getSolTransations(@Query("wallet") String wallet,@Query("before") String before);
    @GET("/api/swap/getQuo")
    Single<CommonResponse<QuoEntity>> getQuo(@Query("from") String from, @Query("to") String to, @Query("amount") String amount, @Query("fromdecimal") int fromdecimal);
    @GET("/api/swap/getNetworkGas")
    Single<CommonResponse<String>> getNetworkGas(@Query("feeMints") String feeMints);
    @POST("/api/swap/postRouterFee")
    Single<CommonResponse<String>> postRouterFee(@Body QuoEntity request );
    @POST("/api/swap/reqSwapTransation")
    Single<CommonResponse<ReqSwapTransation>> reqSwapTransation(@Body QuoPubkey58Entity request );

    @POST("/api/swap/submmitSwapTx")
    Single<CommonResponse<String>> submmitSwapTx(@Body SubmmitVerTxReqBodyEntity request );

    @GET("/api/swap/getSwapTxState")
    Single<CommonResponse<List<SwapQueryStateResult>>> getSwapTxState(@Query("txId") String txId);




}
