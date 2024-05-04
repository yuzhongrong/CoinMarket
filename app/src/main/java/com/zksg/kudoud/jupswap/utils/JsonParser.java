package com.zksg.kudoud.jupswap.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    public static final String jsonString = "{\"data\":{\"GtDZKAqvMZMnti46ZewMiXCa4oXF4bZxwQPoKzXPFxZn\":{\"id\":\"GtDZKAqvMZMnti46ZewMiXCa4oXF4bZxwQPoKzXPFxZn\",\"mintSymbol\":\"nub\",\"vsToken\":\"EPjFWdd5AufqSSqeM2qN1xzybapC8G4wEGGkZwyTDt1v\",\"vsTokenSymbol\":\"USDC\",\"price\":0.050038659},\"So11111111111111111111111111111111111111112\":{\"id\":\"So11111111111111111111111111111111111111112\",\"mintSymbol\":\"SOL\",\"vsToken\":\"EPjFWdd5AufqSSqeM2qN1xzybapC8G4wEGGkZwyTDt1v\",\"vsTokenSymbol\":\"USDC\",\"price\":134.831465704}},\"timeTaken\":0.0009252249992641737}";

    public static void parse2TokenInfo(String jsonString){

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
        JsonObject dataObject = jsonObject.getAsJsonObject("data");
        List<TokenInfo> tokenInfos = new ArrayList<>();
        for (String tokenId : dataObject.keySet()) {
            JsonObject tokenInfoObject = dataObject.getAsJsonObject(tokenId);
            TokenInfo tokenInfo = gson.fromJson(tokenInfoObject, TokenInfo.class);
            tokenInfos.add(tokenInfo);
        }

        double timeTaken = jsonObject.get("timeTaken").getAsDouble();
        System.out.println("Time Taken: " + timeTaken);

        // 打印 TokenInfo 对象列表
        for (TokenInfo tokenInfo : tokenInfos) {
            System.out.println(tokenInfo);
        }

    }



    public static class TokenInfo {
        @SerializedName("id")
        private String id;

        @SerializedName("mintSymbol")
        private String mintSymbol;

        @SerializedName("vsToken")
        private String vsToken;

        @SerializedName("vsTokenSymbol")
        private String vsTokenSymbol;

        @SerializedName("price")
        private double price;

        // 添加构造函数、getter 和 setter 方法

        @Override
        public String toString() {
            return "Token ID: " + id + ", Mint Symbol: " + mintSymbol + ", VS Token: " + vsToken + ", VS Token Symbol: " + vsTokenSymbol + ", Price: " + price;
        }
    }


}
