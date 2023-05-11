package com.zksg.kudoud.test;

import android.os.Handler;

import java.io.File;

public class CommonExample {

    public boolean callArgumentInstance(File file) {
        return file.exists();
    }

    public boolean callArgumentInstance(String path) {
        File file = new File(path);
        return file.exists();
    }

    public Runnable testRunnable() {
        Runnable runnable= new Runnable() {
            @Override
            public void run() {
                System.out.print("test111");
            }
        };
        return runnable;

    }
}
