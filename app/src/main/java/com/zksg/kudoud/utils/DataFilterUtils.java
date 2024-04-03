package com.zksg.kudoud.utils;

import com.zksg.lib_api.beans.MemeBaseEntry;

import java.util.List;
import java.util.stream.Collectors;

public class DataFilterUtils {

    public static List<MemeBaseEntry> filterNonNullName(List<MemeBaseEntry> dataList) {
        return dataList.stream()
                .filter(entry -> (entry.getSymbol() != null))
                .collect(Collectors.toList());
    }

}
