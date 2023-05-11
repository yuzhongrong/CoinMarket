package com.zksg.kudoud.beans;

public enum MusicChannelEnum {

	CYCMUSIC("环境音", 0x01),
	MYMUSIC("我的音乐", 0x02),
	LOCALMUSIC("本地音乐", 0x03);

	//所有类型标识
	public static final int CYCMUSIC_ID = 0x01;
	public static final int MYMUSIC_ID = 0x02;
	public static final int LOCALMUSIC_ID = 0x03;


	private final String key;
	private final int value;

	MusicChannelEnum(String key, int value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public int getValue() {
		return value;
	}
}
