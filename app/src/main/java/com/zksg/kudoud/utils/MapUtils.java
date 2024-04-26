package com.zksg.kudoud.utils;

import com.tencent.mmkv.MMKV;
import com.zksg.kudoud.utils.manager.SimpleWallet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapUtils {
    public static List<SimpleWallet> mapToList(String group,String[] allkeys) throws Exception {
        List<SimpleWallet> list = new ArrayList<>();
        for (String key : allkeys) {
            byte[] simplewalletbytes = MMKV.mmkvWithID(group).getBytes(key,null);
           SimpleWallet simpleWallet= (SimpleWallet) ObjectSerializationUtils.deserializeObject(simplewalletbytes);
            list.add(simpleWallet);
        }
        return list;
    }

}
