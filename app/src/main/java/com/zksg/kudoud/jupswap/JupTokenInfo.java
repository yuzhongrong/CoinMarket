
package com.zksg.kudoud.jupswap;
import com.google.gson.annotations.SerializedName;

public class JupTokenInfo {
    @SerializedName("id")
    private String id;

    @SerializedName("mintSymbol")
    private String mintSymbol;

    @SerializedName("vsToken")
    private String vsToken;

    @SerializedName("vsTokenSymbol")
    private String vsTokenSymbol;

    @SerializedName("price")
    private double price;

    // 添加构造函数、getter 和 setter 方法

    @Override
    public String toString() {
        return "Token ID: " + id + ", Mint Symbol: " + mintSymbol + ", VS Token: " + vsToken + ", VS Token Symbol: " + vsTokenSymbol + ", Price: " + price;
    }
}