package com.zksg.lib_api.beans;

public class UpgradeBean {
    private int versioncode;
    private String versionname;
    private String url;
    private String content;




    public int getVersioncode() {
        return versioncode;
    }

    public void setVersioncode(int versioncode) {
        this.versioncode = versioncode;
    }

    public String getVersionname() {
        return versionname;
    }

    public void setVersionname(String versionname) {
        this.versionname = versionname;
    }

    public String getDownloadurl() {
        return url;
    }

    public void setDownloadurl(String downloadurl) {
        this.url = downloadurl;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
