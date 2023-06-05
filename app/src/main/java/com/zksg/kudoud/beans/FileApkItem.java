package com.zksg.kudoud.beans;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.FileUtils;

import java.io.File;

public class FileApkItem {
    public File file;
    public String size;

    public AppUtils.AppInfo appInfo;


    public FileApkItem(File file) {
        this.file = file;
        this.size = FileUtils.getSize(file);
        appInfo=AppUtils.getApkInfo(file);

    }

    public FileApkItem(String path) {
        this.file = new File(path);
        this.size = FileUtils.getSize(file);
        appInfo=AppUtils.getApkInfo(file);

    }
    public String getSize() {
        return size;
    }

    public AppUtils.AppInfo getAppInfo(){
        return appInfo;
    }




}
