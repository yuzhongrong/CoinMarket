package com.netease.lib_network.entitys;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class JupToken {
    @SerializedName("address")
    private String address;
    @SerializedName("chainId")
    private int chainId;
    @SerializedName("decimals")
    private int decimals;
    @SerializedName("name")
    private String name;
    @SerializedName("symbol")
    private String symbol;
    @SerializedName("logoURI")
    private String logoURI;
    @SerializedName("tags")
    private List<String> tags;
    @SerializedName("extensions")
    private ExtensionsDTO extensions;
    private boolean isShow;

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getChainId() {
        return chainId;
    }

    public void setChainId(int chainId) {
        this.chainId = chainId;
    }

    public int getDecimals() {
        return decimals;
    }

    public void setDecimals(int decimals) {
        this.decimals = decimals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getLogoURI() {
        return logoURI;
    }

    public void setLogoURI(String logoURI) {
        this.logoURI = logoURI;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public ExtensionsDTO getExtensions() {
        return extensions;
    }

    public void setExtensions(ExtensionsDTO extensions) {
        this.extensions = extensions;
    }

    public static class ExtensionsDTO {
        @SerializedName("coingeckoId")
        private String coingeckoId;

        public String getCoingeckoId() {
            return coingeckoId;
        }

        public void setCoingeckoId(String coingeckoId) {
            this.coingeckoId = coingeckoId;
        }
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        JupToken token = (JupToken) obj;
        return Objects.equals(address, token.address);
    }

}
