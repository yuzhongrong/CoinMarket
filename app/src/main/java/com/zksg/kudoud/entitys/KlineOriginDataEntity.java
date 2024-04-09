package com.zksg.kudoud.entitys;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KlineOriginDataEntity {

    @SerializedName("items")
    private List<ItemsDTO> items;

    public List<ItemsDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemsDTO> items) {
        this.items = items;
    }

    public static class ItemsDTO {
        @SerializedName("address")
        private String address;
        @SerializedName("unixTime")
        private long unixTime;
        @SerializedName("value")
        private double value;

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

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }
    }
}
