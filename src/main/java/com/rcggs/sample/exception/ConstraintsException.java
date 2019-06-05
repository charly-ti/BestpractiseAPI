package com.rcggs.sample.exception;

public class ConstraintsException extends Exception{

private static final long serialVersionUID = -4163755588613030128L;
	
	private int code;
	
	public ConstraintsException(int code, String message) {
		super(message);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	
}
