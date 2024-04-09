package com.netease.lib_network.entitys;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KlineOriginDataEntity {

    @SerializedName("items")
    private List<DataItem> items;

    public List<DataItem> getItems() {
        return items;
    }

    public void setItems(List<DataItem> items) {
        this.items = items;
    }

    public static class DataItem {
        @SerializedName("address")
        private String address;
        @SerializedName("unixTime")
        private long unixTime;
        @SerializedName("value")
        private float value;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public long getUnixTime() {
            return unixTime;
        }

        public void setUnixTime(long unixTime) {
            this.unixTime = unixTime;
        }

        public float getValue() {
            return value;
        }

        public void setValue(float value) {
            this.value = value;
        }
    }
}
