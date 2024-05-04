package com.zksg.kudoud.jupswap.utils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
public class JsonTokenInfoParser {


    private Gson gson;

    public JsonTokenInfoParser() {
        this.gson = new Gson();
    }

    public <T> T parseJson(String jsonString, Class<T> clazz) {
        return gson.fromJson(jsonString, clazz);
    }

    public <T> List<T> parseJsonArray(String jsonString, Class<T> clazz) {
        JsonElement jsonElement = gson.fromJson(jsonString, JsonElement.class);
        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            List<T> list = new ArrayList<>();
            for (String key : jsonObject.keySet()) {
                T item = gson.fromJson(jsonObject.get(key), clazz);
                list.add(item);
            }
            return list;
        } else {
            throw new IllegalArgumentException("JSON is not a valid array");
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
