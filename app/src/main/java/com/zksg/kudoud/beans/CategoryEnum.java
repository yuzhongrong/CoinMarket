package com.zksg.kudoud.beans;

import static com.blankj.utilcode.util.StringUtils.getString;

import com.zksg.kudoud.R;

public enum CategoryEnum {

//	TRENDING='hot',
//	NEW='new',
//	GAINER='gainer',
//	INCLUSION='inclusion',
//	GOLDENDOG='goldendog',
//	ALPHA='alpha',
//	PUMP_IN_HOT='pump_in_hot',//有问题
//	PUMP_OUT_HOT='pump_out_hot',
//	PUMP_IN_ALMOST='pump_in_almost'//有问题

	LIKE(getString(R.string.str_zx), "like"),
	NEW(getString(R.string.str_new_pair), "new"),
	GAINER(getString(R.string.str_24up), "gainer"),
	INCLUSION(getString(R.string.str_collect), "inclusion"),
	ALPHA(getString(R.string.str_alpha),"alpha"),
	GOLDENDOG(getString(R.string.str_golddoge),"goldendog"),
	PUMP_IN_HOT(getString(R.string.str_pumpin),"pump_in_hot"),
	PUMP_OUT_HOT(getString(R.string.str_pumpin),"pump_out_hot"),
	PUMP_IN_ALMOST(getString(R.string.str_pumpin),"pump_in_almost");
	//所有类型标识
	public final  int like_id=0;
	public final  int new_id=1;
	public final  int gainer_id=2;
	public final  int alpha_id=3;
	public final  int goldendog_id=4;
	public final  int pump_in_hot_id=5;
	public final  int pump_out_hot_id=6;
	public final  int pump_in_almost_id=7;



	private final String key;
	private final String value;

	CategoryEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
}
