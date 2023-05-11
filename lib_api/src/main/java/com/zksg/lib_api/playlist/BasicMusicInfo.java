package com.zksg.lib_api.playlist;

/** TODO:tip
 * isplay:播放状态
 * name：歌曲名称
 * leng:播放时长
 */
public class BasicMusicInfo {
    private boolean isplay;
    private String name;
    private String leng;

    public BasicMusicInfo(boolean isplay, String name, String leng) {
        this.isplay = isplay;
        this.name = name;
        this.leng = leng;
    }

    public boolean isIsplay() {
        return isplay;
    }

    public void setIsplay(boolean isplay) {
        this.isplay = isplay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeng() {
        return leng;
    }

    public void setLeng(String leng) {
        this.leng = leng;
    }
}
