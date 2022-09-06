package com.kartikeychoudhary.response;

import java.util.Map;

import org.springframework.http.HttpStatus;


public class GenericResponse {
	
	private HttpStatus status;
	private Map<String, Object> payLoad;
	
	public GenericResponse() {
		// need empty constructor
	}

	public GenericResponse(HttpStatus status, Map<String, Object> payLoad) {
		super();
		this.status = status;
		this.payLoad = payLoad;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public Map<String, Object> getPayLoad() {
		return payLoad;
	}

	public void setPayLoad(Map<String, Object> payLoad) {
		this.payLoad = payLoad;
	}

}
