package com.zksg.lib_api.beans;

//mEntity is target
public class DataResponse<T> {
    private int page;
    private int pageSize;
    private int total;
    private  T list;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPaseSize() {
        return pageSize;
    }

    public void setPaseSize(int paseSize) {
        this.pageSize = paseSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
    }
}
