package com.zksg.kudoud.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.zksg.lib_api.beans.MemeBaseEntry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
public class CollectUtils {

    //priceMap 是请求网络得到的
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setPricesForItems(List<MemeBaseEntry> collection1, Map<String, Double> priceMap) {
        collection1.forEach(item1 -> {
            // 获取当前项目的地址对应的价格
            Double price = priceMap.get(item1.getAddress());
            if (price != null) {
                // 如果找到对应的价格，则设置价格属性
                item1.setPrice(price);
            }
        });
    }


    public static Map<String, Double> parseJsonToPriceMap(String json) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        Map<String, Double> priceMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            JSONObject innerObject = (JSONObject) entry.getValue();
            double price = innerObject.getDoubleValue("value");
            priceMap.put(entry.getKey(), price);
        }
        return priceMap;
    }

}
