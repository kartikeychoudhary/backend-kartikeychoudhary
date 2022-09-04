package com.kartikeychoudhary.exceptions;

public class CustomGenericRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String errorCode;
	
	public CustomGenericRuntimeException(String msg) {
		super(msg);
	}
	
	public CustomGenericRuntimeException(String msg, Throwable throwable) {
		super(msg, throwable);
	}
	
	public CustomGenericRuntimeException(String msg, Throwable throwable, String errorCode) {
		super(msg, throwable);
		this.setErrorCode(errorCode);
	}
	
	public CustomGenericRuntimeException(String msg, String errorCode) {
		super(msg);
		this.setErrorCode(errorCode);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
