package com.rcggs.sample.controller.util;

import org.springframework.http.HttpStatus;

import com.rcggs.sample.dto.Error;
import com.rcggs.sample.model.User;

public class StatusResponse<T> {
    
	private int code;
      
    private String message;
    
    private T data;
    
    private String status;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
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

	public StatusResponse(int hashCode, String status, String success, T data) {
		this.code = hashCode;
		this.status = status;
		this.message = success;
		this.data = data;
		}

	public StatusResponse(int hashCode, String status, String errorMessage) {
		this.code = hashCode;
		this.status = status;
		this.message = errorMessage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatusString(String status) {
		this.status = status;
	}

	
    
}
