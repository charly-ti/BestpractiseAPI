package com.rcggs.sample.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rcggs.sample.dto.ErrorDetails;
import com.rcggs.sample.controller.util.StatusResponse;
import com.rcggs.sample.dto.Error;
import com.rcggs.sample.exception.UserNotFoundException;
import com.rcggs.sample.model.User;
import com.rcggs.sample.util.Constants;

@ControllerAdvice
@RestController
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<StatusResponse<Error>> handleInvalidInputException(UserNotFoundException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(ex.getCode(), ex.getMessage());
		Error error = new Error(errorDetails);
		return ResponseEntity.ok(new StatusResponse<Error>(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND, Constants.ERROR,error));
	}

}
