package com.rcggs.sample.dto;

public class Error {
	
	private ErrorDetails error;

	public Error(ErrorDetails error) {
		this.error = error;
	}

	public ErrorDetails getError() {
		return error;
	}

	public void setError(ErrorDetails error) {
		this.error = error;
	}
	
	

}
