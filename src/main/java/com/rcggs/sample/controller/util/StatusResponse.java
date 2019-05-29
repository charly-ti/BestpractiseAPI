package com.rcggs.sample.controller.util;

import org.springframework.http.HttpStatus;

import com.rcggs.sample.dto.Error;
import com.rcggs.sample.model.User;

public class StatusResponse<T> {
    
	private int code;
    
//    private int internalCode;
    
    private HttpStatus status;
    
    private String message;
    
    private T data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

//	public int getInternalCode() {
//		return internalCode;
//	}
//
//	public void setInternalCode(int internalCode) {
//		this.internalCode = internalCode;
//	}

	

	public String getMessage() {
		return message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}


	public StatusResponse(int hashCode, HttpStatus ok, String success) {
		this.code = hashCode;
		this.status = ok;
		this.message = success;
	}

	public StatusResponse(int value, HttpStatus ok, String success, T data) {
		this.code = value;
		this.status = ok;
		this.message = success;
		this.data = data;
		}

	
    
}
