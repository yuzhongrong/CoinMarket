package com.netease.lib_network;




import com.kunminx.architecture.data.response.DataResult;
import com.kunminx.architecture.domain.message.MutableResult;
import com.zksg.lib_api.login.LoginBean;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    String BASE_URL = "http://192.168.1.184:3000/";

    @GET("login/cellphone")
    Single<LoginBean>  login(@Query("phone") String phone, @Query("password") String password);




}
