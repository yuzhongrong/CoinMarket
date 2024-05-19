package com.netease.lib_network.entitys;

import com.google.gson.annotations.SerializedName;

public class CommitTransation {


  @SerializedName("txid")
  private String txid;
  @SerializedName("committime")
  private long committime;
  @SerializedName("status")
  private String status;

  public String getTxid() {
    return txid;
  }

  public void setTxid(String txid) {
    this.txid = txid;
  }

  public long getCommittime() {
    return committime;
  }

  public void setCommittime(long committime) {
    this.committime = committime;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
