package com.rcggs.sample.util;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.http.HttpStatus;

public class Constants {
	
	
	public static final String SUCCESS = "Success";
	public static final String OK = "OK";
	public static final String ERROR = "Error";
	public static final HashSet<String> VALIDATION_ERROR_TYPE = new HashSet<>(
            Arrays.asList("NotEmpty", "NotNull", "Email", "Size"));
	public static final String WARN = "Warning";
	public static final String NOT_FOUND = "Not Found";
	public static final int WARN_CODE = 501;
	public static final int ERROR_CODE = 500;
	public static final int SUCCESS_CODE = 200;




	

}
