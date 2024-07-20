package com.zksg.kudoud.entitys;

public class SwapStateEntity {
    private UiWalletToken from;
    private UiWalletToken to;

    private String txId;



    private String state; //processed-->confirmed|fail



    public SwapStateEntity(UiWalletToken from, UiWalletToken to, String txId,String state) {
        this.from = from;
        this.to = to;
        this.txId=txId;
        this.state=state;
    }

    public UiWalletToken getFrom() {
        return from;
    }

    public void setFrom(UiWalletToken from) {
        this.from = from;
    }

    public UiWalletToken getTo() {
        return to;
    }

    public void setTo(UiWalletToken to) {
        this.to = to;
    }


    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
