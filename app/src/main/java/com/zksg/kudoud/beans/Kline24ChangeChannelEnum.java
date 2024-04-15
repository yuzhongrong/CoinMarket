package com.zksg.kudoud.beans;

public enum Kline24ChangeChannelEnum {

	K_5M("5M", 0x01),
	K_1H("1H", 0x02),
	K_6H("6H", 0x03),
	K_24H("24H", 0x04);

	//所有类型标识
	public static final int K_5M_ID = 0x01;
	public static final int K_1H_ID = 0x02;
	public static final int K_6H_ID = 0x03;
	public static final int K_24H_ID = 0x04;

	private final String key;
	private final int value;

	Kline24ChangeChannelEnum(String key, int value) {
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
