package com.qbao.pay.notify.view;

import java.io.Serializable;

public class BaseView<T> implements Serializable {
	/**
	 * 版本号
	 */
	private static final long serialVersionUID = -4554833262321240454L;
	private String message;
	private T result;

	public BaseView() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public BaseView(String message, T result) {
		super();
		this.message = message;
		this.result = result;
	}

}
