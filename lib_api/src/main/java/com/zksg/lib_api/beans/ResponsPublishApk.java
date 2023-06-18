package com.zksg.lib_api.beans;

public class ResponsPublishApk {

    private String msg;
    private int code;
    private Object data;

    public Object getId() {
        return data;
    }

    public void setId(Object id) {
        this.data = id;
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
