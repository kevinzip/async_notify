package com.qbao.pay.notify.exception;

public class DataValidateException extends Exception {
	private static final long serialVersionUID = 3603011847944138021L;
	private String message;
    private Integer errorCode;

	public DataValidateException() {
		this.message = "";
	}

	public DataValidateException(String message) {
		this.message = message;
	}

	public DataValidateException(String message, Throwable t) {
		super(message, t);
		this.message = message;
	}

    public DataValidateException(String message,Integer errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

	public String getMessage() {
		return message;
	}

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}
