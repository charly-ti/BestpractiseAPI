package com.rcggs.sample.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rcggs.sample.dto.ErrorDetails;
import com.rcggs.sample.controller.util.StatusResponse;
import com.rcggs.sample.dto.AuditLog;
import com.rcggs.sample.dto.Error;
import com.rcggs.sample.exception.UserNotFoundException;
import com.rcggs.sample.exception.ConstraintsException;
import com.rcggs.sample.model.User;
import com.rcggs.sample.service.Loggerservice;
import com.rcggs.sample.util.Constants;

@ControllerAdvice
@RestController
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {
	
	@Autowired
	private Loggerservice loggerService;

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<StatusResponse<Error>> handleInvalidInputException(UserNotFoundException ex,
			WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(ex.getCode(), ex.getMessage());
		Error error = new Error(errorDetails);
		return ResponseEntity.ok(
				new StatusResponse<Error>(Constants.ERROR_CODE, Constants.NOT_FOUND, error.getError().getMessage()));
	}

	@SuppressWarnings("rawtypes")
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		FieldError fieldError = ex.getBindingResult().getFieldError();
		String errorMessage = fieldError.getDefaultMessage();
		String annoTationType = ex.getBindingResult().getAllErrors().get(0).getCode();
		if (Constants.VALIDATION_ERROR_TYPE.contains(annoTationType)) {
			String fieldName = fieldError.getField();
			errorMessage = fieldName + " " + errorMessage;
		}
		AuditLog auditLogAfter = new AuditLog("Invalid User Input", "buisinessTxnNumber", 15,
				new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(new Date()), "WARN", errorMessage,
				"REST API");
		loggerService.sendToLog(auditLogAfter);
		return ResponseEntity.ok(new StatusResponse<Error>(Constants.WARN_CODE, Constants.WARN, errorMessage));

	}

}
