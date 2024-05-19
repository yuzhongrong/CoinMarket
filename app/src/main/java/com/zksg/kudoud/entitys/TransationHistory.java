package com.zksg.kudoud.entitys;

import java.io.Serializable;

public class TransationHistory implements Serializable {
    String sender;
    String receiver;
    String gas;
    String number;
    String txid;
    long commitTime;
    String status;



    public TransationHistory(String sender, String receiver, String gas, String number, String txid,String status, long commitTime) {
        this.sender = sender;
        this.receiver = receiver;
        this.gas = gas;
        this.number = number;
        this.txid = txid;
        this.status=status;
        this.commitTime = commitTime;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getGas() {
        return gas;
    }

    public void setGas(String gas) {
        this.gas = gas;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }
    public long getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(long commitTime) {
        this.commitTime = commitTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
