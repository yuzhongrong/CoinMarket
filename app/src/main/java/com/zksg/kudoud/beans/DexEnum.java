package com.zksg.kudoud.beans;

public enum DexEnum {

	ORCA("orca", 0x01),
	METEORA("meteora", 0x02),
	RAYDIUM("raydium", 0x03);

	//所有类型标识
	public static final int ORCA_ID = 0x01;
	public static final int METEORA_ID = 0x02;
	public static final int RAYDIUM_ID = 0x03;


	private final String key;
	private final int value;

	DexEnum(String key, int value) {
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
