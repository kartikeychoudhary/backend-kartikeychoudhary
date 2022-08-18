package com.kartikeychoudhary.exceptions;

public class CustomWebsiteRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String errorCode;
	
	public CustomWebsiteRuntimeException(String msg) {
		super(msg);
	}
	
	public CustomWebsiteRuntimeException(String msg, Throwable throwable) {
		super(msg, throwable);
	}
	
	public CustomWebsiteRuntimeException(String msg, Throwable throwable, String errorCode) {
		super(msg, throwable);
		this.errorCode = errorCode;
	}
	
	public CustomWebsiteRuntimeException(String msg, String errorCode) {
		super(msg);
		this.errorCode=errorCode;
	}

}
