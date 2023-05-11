package com.zksg.kudoud.test;

import java.io.File;

public class Directory {

    public boolean create(String path) {
        File file = new File(path);

        if (file.exists()) {
            throw new IllegalArgumentException("path already exists");
        }
        return file.mkdirs();
    }
}
