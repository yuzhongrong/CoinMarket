package com.zksg.kudoud.beans;

import static com.blankj.utilcode.util.StringUtils.getString;

import com.zksg.kudoud.R;

public enum CommonMarketsDataEnum {

	WALLET(getString(R.string.str_wallet), 0),
	EXCHANGE(getString(R.string.str_exchange), 1),
	CHAT(getString(R.string.str_chat), 4),
	//自选
	ZX(getString(R.string.str_zx), 5),
	UP24(getString(R.string.str_24up), 6),
	DOWN24(getString(R.string.str_24down), 7),

	EX24(getString(R.string.str_24ex), 8),
	OTHER(getString(R.string.str_category), -1);
	//所有类型标识
	public static final int WALLET_ID = 0;
	public static final int EXCHANGE_ID = 1;
	public static final int CHAT_ID = 4;
	public static final int CHAT_ZX = 5;
	public static final int CHAT_UP_24 = 6;
	public static final int CHAT_DOWN_24 = 7;

	public static final int CHAT_EX_24 = 8;

	public static final int OTHER_ID = -1;



	private final String key;
	private final int value;

	CommonMarketsDataEnum(String key, int value) {
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
