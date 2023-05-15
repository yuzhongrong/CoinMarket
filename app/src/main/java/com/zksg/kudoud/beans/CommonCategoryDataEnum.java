package com.zksg.kudoud.beans;

import static com.blankj.utilcode.util.StringUtils.getString;

import com.zksg.kudoud.R;

public enum CommonCategoryDataEnum {

	WALLET(getString(R.string.str_wallet), 0x01),
	EXCHANGE(getString(R.string.str_exchange), 0x02),
	DEX(getString(R.string.str_dex), 0x03),
	OTHER(getString(R.string.str_category), 0x04);
	//所有类型标识
	public static final int WALLET_ID = 0x01;
	public static final int EXCHANGE_ID = 0x02;
	public static final int DEX_ID = 0x03;
	public static final int OTHER_ID = 0x04;

	private final String key;
	private final int value;

	CommonCategoryDataEnum(String key, int value) {
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
