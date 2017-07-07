package com.qbao.pay.notify.enumeration;

public enum NotifyTypeEnum {

	NOTIFY_SUCCESS("10", "处理成功"), 
	NOTIFY_FAILE("20", "处理失败"), 
	NOTIFY_ING("30", "处理中");

	NotifyTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
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
