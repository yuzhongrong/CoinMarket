package com.zksg.kudoud.entitys;

import androidx.databinding.ObservableField;

import com.kunminx.architecture.domain.message.MutableResult;

public class WebViewParmarsEntity {

    private String html;
    private MutableResult<Integer> progressField=new MutableResult<>(0);

    public WebViewParmarsEntity(String html) {
        this.html = html;

    }

    public String getHtml() {
        return html;
    }


    public MutableResult<Integer> getProgress() {
        return progressField;
    }

}
