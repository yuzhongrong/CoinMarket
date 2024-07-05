package com.zksg.kudoud.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

}
