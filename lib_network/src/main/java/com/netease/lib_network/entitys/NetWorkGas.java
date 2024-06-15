package com.netease.lib_network.entitys;

import java.util.Map;

public class NetWorkGas {
    private Map<String, TokenPrice> data;

    public Map<String, TokenPrice> getData() {
        return data;
    }

    public void setData(Map<String, TokenPrice> data) {
        this.data = data;
    }

    public static class TokenPrice {
        private double price;
        private int decimal;

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getDecimal() {
            return decimal;
        }

        public void setDecimal(int decimal) {
            this.decimal = decimal;
        }

        @Override
        public String toString() {
            return "TokenPrice{" +
                    "price=" + price +
                    ", decimal=" + decimal +
                    '}';
        }


    }
}
