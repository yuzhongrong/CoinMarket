package com.zksg.kudoud.beans;

public enum FeedTipEnum {

	ONE("周一", 0x01),
	TWO("周二", 0x02),
	THREE("周三", 0x03),
	FOUR("周四", 0x04),
	FIVE("周五", 0x05),
	ONE_FIVE("周一到周五", 0x06),
	EVERY_DAY("每一天", 0x07);
	//所有类型标识
	public static final int ONE_ID = 0x01;
	public static final int TWO_ID = 0x02;
	public static final int THREE_ID = 0x03;
	public static final int FOUR_ID = 0x04;
	public static final int FIVE_ID = 0x05;
	public static final int ONE_FIVE_ID = 0x06;
	public static final int EVERY_DAY_ID = 0x07;

	private final String key;
	private final int value;

	FeedTipEnum(String key, int value) {
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
