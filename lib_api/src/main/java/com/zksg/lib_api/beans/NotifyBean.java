package com.zksg.lib_api.beans;

public class NotifyBean {
    private String title="";
    private String subtitle="";
//    private String time="";
    private String CreatedAt;

    public NotifyBean(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTime() {
        return CreatedAt;
    }

    public void setTime(String time) {
        this.CreatedAt = time;
    }
}
