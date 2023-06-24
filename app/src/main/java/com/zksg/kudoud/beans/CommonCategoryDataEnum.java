package com.zksg.kudoud.beans;

import static com.blankj.utilcode.util.StringUtils.getString;

import com.zksg.kudoud.R;

public enum CommonCategoryDataEnum {

	WALLET(getString(R.string.str_wallet), 0),
	EXCHANGE(getString(R.string.str_exchange), 1),
	DEX(getString(R.string.str_dex), 2),
	OTHER(getString(R.string.str_category), -1);
	//所有类型标识
	public static final int WALLET_ID = 0;
	public static final int EXCHANGE_ID = 1;
	public static final int DEX_ID = 2;
	public static final int OTHER_ID = -1;

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
