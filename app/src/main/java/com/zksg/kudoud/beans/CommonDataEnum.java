package com.zksg.kudoud.beans;

public enum CommonDataEnum {

	HEARTRATEDAY("日", 0x01),
	HEARTRATEWEEK("周", 0x02),
	HEARTRATEMONTH("月", 0x03),
	HEARTRATEYEAR("年", 0x04);
	//所有类型标识
	public static final int HEARTRATEDAY_ID = 0x01;
	public static final int HEARTRATEWEEK_ID = 0x02;
	public static final int HEARTRATEMONTH_ID = 0x03;
	public static final int HEARTRATEYEAR_ID = 0x04;

	private final String key;
	private final int value;

	CommonDataEnum(String key, int value) {
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
