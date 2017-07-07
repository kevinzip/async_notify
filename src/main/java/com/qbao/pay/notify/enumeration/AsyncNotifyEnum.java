package com.qbao.pay.notify.enumeration;

public enum AsyncNotifyEnum {

	ASYNC_NOTIFY_BALANCE("C10000", "结算收单渠道编码");
	
	AsyncNotifyEnum(String code, String desc)
	{
		this.code = code;
		this.desc= desc;
	}

	private String code;

	private String desc;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
