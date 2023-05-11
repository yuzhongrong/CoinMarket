package com.zksg.lib_api.baby;

public class FeedTip {
    private String id;
    private String tiptime;
    private int rule;

    public FeedTip(String id, String tiptime, int rule) {
        this.id = id;
        this.tiptime = tiptime;
        this.rule=rule;
    }
    public String getTiptime() {
        return tiptime;
    }

    public void setTiptime(String tiptime) {
        this.tiptime = tiptime;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setRule(int rule) {
        this.rule = rule;
    }
    public int getRule(){
        return rule;
    }


}
