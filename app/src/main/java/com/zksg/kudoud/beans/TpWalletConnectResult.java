package com.zksg.kudoud.beans;

import com.google.gson.annotations.SerializedName;

public class TpWalletConnectResult {


    @SerializedName("protocol")
    private String protocol;
    @SerializedName("version")
    private String version;
    @SerializedName("timestamp")
    private int timestamp;
    @SerializedName("sign")
    private String sign;
    @SerializedName("uuID")
    private String uuID;
    @SerializedName("actionId")
    private String actionId;
    @SerializedName("account")
    private String account;
    @SerializedName("wallet")
    private String wallet;
    @SerializedName("ref")
    private String ref;
    @SerializedName("publickey")
    private String publickey;
    @SerializedName("permissions")
    private PermissionsDTO permissions;
    @SerializedName("result")
    private int result;
    @SerializedName("action")
    private String action;
    @SerializedName("network")
    private String network;
    @SerializedName("chainId")
    private String chainId;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getUuID() {
        return uuID;
    }

    public void setUuID(String uuID) {
        this.uuID = uuID;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getPublickey() {
        return publickey;
    }

    public void setPublickey(String publickey) {
        this.publickey = publickey;
    }

    public PermissionsDTO getPermissions() {
        return permissions;
    }

    public void setPermissions(PermissionsDTO permissions) {
        this.permissions = permissions;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getChainId() {
        return chainId;
    }

    public void setChainId(String chainId) {
        this.chainId = chainId;
    }

    public static class PermissionsDTO {
    }
}
