package com.zksg.kudoud.utils;

import com.zksg.kudoud.entitys.UiWalletToken;

import java.util.ArrayList;
import java.util.List;

public class SearchTokenUtils {

    // 执行模糊查询的方法
    public static List<UiWalletToken> searchTokens(List<UiWalletToken> tokens, String searchText) {
        List<UiWalletToken> searchResults = new ArrayList<>();

        // 遍历集合中的每个 UiWalletToken 对象
        for (UiWalletToken token : tokens) {
            // 如果对象的 name 或 symbol 中包含搜索文本，则将该对象添加到结果列表中
            if (token.getName().toLowerCase().contains(searchText.toLowerCase()) ||
                    token.getSymbol().toLowerCase().contains(searchText.toLowerCase())||token.getMint().equalsIgnoreCase(searchText.toLowerCase())) {
                searchResults.add(token);
            }
        }

        return searchResults;
    }

}
