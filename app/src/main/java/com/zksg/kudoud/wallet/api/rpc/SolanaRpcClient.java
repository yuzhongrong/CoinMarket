/************************************************************************ 
 * Copyright PointCheckout, Ltd.
 * 
 */
package com.zksg.kudoud.wallet.api.rpc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zksg.kudoud.wallet.api.rpc.types.RpcRequest;
import com.zksg.kudoud.wallet.api.rpc.types.RpcResponse;
import com.zksg.kudoud.wallet.exception.SolanajException;
import com.zksg.kudoud.wallet.utils.JsonUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 
 */
public class SolanaRpcClient {
    private static SolanaRpcClient instance;
    private static final Object lock = new Object();
    /**  */
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**  */
    private Cluster cluster;

    /**  */
    private OkHttpClient httpClient = new OkHttpClient.Builder()//
            .connectTimeout(60, TimeUnit.SECONDS)//
            .writeTimeout(60, TimeUnit.SECONDS)//
            .readTimeout(60, TimeUnit.SECONDS)//
            .build();

    /**  */
    private SolanaRpcApi rpcApi;

    /**
     * 
     *
     * @param cluster 
     */
    private SolanaRpcClient(Cluster cluster) {
        this.cluster = cluster;
        rpcApi = new SolanaRpcApi(this);
    }

    // 获取单例实例的方法
    public static SolanaRpcClient getInstance(Cluster cluster) {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new SolanaRpcClient(cluster);
                }
            }
        }
        return instance;
    }

    /**
     * 
     *
     * @param <T> 
     * @param method 
     * @param params 
     * @param clazz 
     * @return 
     */
    @SuppressWarnings("deprecation")
    public <T> T call(String method, List<Object> params, Class<T> clazz) {
        RpcRequest rpcRequest = new RpcRequest(method, params);
        Request request = new Request.Builder()//
                .url(getEndpoint())//
                .post(RequestBody.create(JSON, JsonUtils.encode(rpcRequest)))//
                .build();

        try {
            Response response = httpClient.newCall(request).execute();
            String responseJson = response.body().string();

            RpcResponse<T> rpcResult = JsonUtils.getObjectMapper()//
                    .readValue(responseJson, new TypeReference<RpcResponse<T>>() {});

            if (rpcResult.getError() != null) {
                throw new SolanajException(rpcResult.getError().getMessage());
            }

            String resultJson = JsonUtils.getObjectMapper().writeValueAsString(rpcResult.getResult());

            return JsonUtils.decode(resultJson, clazz);
        } catch (IOException e) {
            throw new SolanajException(e.getMessage());
        }
    }

    /**
     * 
     *
     * @param <T> 
     * @param method 
     * @param paramList 
     * @param clazz 
     * @return 
     */
    @SuppressWarnings("deprecation")
    public <T> List<T> callBatch(String method, List<List<Object>> paramList, Class<T> clazz) {

        List<RpcRequest> rpcRequests =
                paramList.stream().map(params -> new RpcRequest(method, params)).collect(Collectors.toList());

        Request request = new Request.Builder()//
                .url(getEndpoint())//
                .post(RequestBody.create(JSON, JsonUtils.encode(rpcRequests)))//
                .build();

        try {
            Response response = httpClient.newCall(request).execute();
            String responseJson = response.body().string();

            if (responseJson.isEmpty())
                return new ArrayList<>();

            List<RpcResponse<T>> rpcResults = JsonUtils.getObjectMapper()//
                    .readValue(responseJson, new TypeReference<List<RpcResponse<T>>>() {});

            RpcResponse<T> errorResult = rpcResults.stream().filter(r -> r.getError() != null).findAny().orElse(null);

            if (errorResult != null) {
                throw new SolanajException(errorResult.getError().getMessage());
            }

            List<T> result = new ArrayList<>();

            for (RpcResponse<T> rpcResult : rpcResults) {
                String resultJson = JsonUtils.getObjectMapper().writeValueAsString(rpcResult.getResult());
                result.add(JsonUtils.decode(resultJson, clazz));
            }

            return result;
        } catch (IOException e) {
            throw new SolanajException(e.getMessage());
        }
    }

    /**
     * 
     *
     * @return 
     */
    public SolanaRpcApi getApi() {
        return rpcApi;
    }

    /**
     * 
     *
     * @return 
     */
    public String getEndpoint() {
        return cluster.getEndpoint();
    }

    /**
     * 
     *
     * @return 
     */
    public Cluster getCluster() {
        return cluster;
    }

}
