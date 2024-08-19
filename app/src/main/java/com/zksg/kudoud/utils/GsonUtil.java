package com.zksg.kudoud.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

public class GsonUtil {

    private static final Gson gson = new Gson();

    public static <T> T fromJson(Object object, Class<T> classOfT) {
        // Convert the object to JSON string
        String jsonString = gson.toJson(object);

        // Deserialize the JSON string to the specified class
        return gson.fromJson(jsonString, classOfT);
    }

    public static <T> T fromJson(Object object, TypeToken<T> typeToken) {
        // Convert the object to JSON string
        String jsonString = gson.toJson(object);

        // Deserialize the JSON string to the specified type
        return gson.fromJson(jsonString, typeToken.getType());
    }


    /**
     * 解析 JSON 字符串数组，并返回字符串数组
     *
     * @param jsonString 需要解析的 JSON 字符串数组
     * @return 解析后的字符串数组
     * @throws JSONException 如果 JSON 解析失败
     */
    public static String[] parseJsonArray(String jsonString) throws JSONException {
        // 将 JSON 字符串解析为 JSONArray 对象
        JSONArray jsonArray = new JSONArray(jsonString);

        // 创建一个与 JSONArray 长度相同的字符串数组
        String[] result = new String[jsonArray.length()];

        // 将 JSONArray 中的元素逐个添加到字符串数组中
        for (int i = 0; i < jsonArray.length(); i++) {
            result[i] = jsonArray.getString(i);
        }

        return result;
    }

}
