package com.rcggs.sample.controller;

//import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rcggs.sample.controller.util.StatusResponse;
import com.rcggs.sample.dto.AuditLog;
import com.rcggs.sample.dto.Userdto;
import com.rcggs.sample.exception.UserNotFoundException;
import com.rcggs.sample.model.User;
import com.rcggs.sample.service.Loggerservice;
import com.rcggs.sample.service.SampleResource;
import com.rcggs.sample.util.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

// TODO: Auto-generated Javadoc
//	Point 4: Consistent naming within the API.
/**
 * The Class SampleController.
 */
@RestController
@RequestMapping("/sample")
@Api(value = "User Management System", description = "Operations pertaining to user in user Management System")
public class SampleController {

	/** The service. */
	@Autowired
	private SampleResource service;
	@Autowired
	private Loggerservice loggerService;

	/**
	 * Adds the new users.
	 *
	 * @param user the user
	 * @return the response entity
	 * @throws UserNotFoundException
	 */
	@ApiOperation(value = "Add a user")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully added User"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/users")
	public ResponseEntity<?> addUsers(
			@ApiParam(value = "User object store in database table", required = true) @RequestBody User user)
			throws UserNotFoundException {
		AuditLog auditLogBefore = new AuditLog("Add new user", "buisinessTxnNumber", 15,
				new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(new Date()), "INFO", "Started Adding User",
				"REST API");
		loggerService.sendToLog(auditLogBefore);
		service.createUser(user);
		AuditLog auditLogAfter = new AuditLog("Add new user", "buisinessTxnNumber", 15,
				new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(new Date()), "INFO", "Completed Adding User",
				"REST API");
		loggerService.sendToLog(auditLogAfter);
		return ResponseEntity.ok(new StatusResponse<User>(HttpStatus.OK.value(), HttpStatus.OK, Constants.SUCCESS));

	}
//	Point 10:A well-developed JavaDoc must be kept.

	/**
	 * Modify users.
	 *
	 * @param user   the user
	 * @param userID the user ID
	 * @return the string
	 * @throws UserNotFoundException
	 */
	@ApiOperation(value = "Update the user")
	@PutMapping(value = "/users")
	public ResponseEntity<StatusResponse<User>> updateUser(
			@ApiParam(value = "Update user object", required = true) @RequestBody Userdto user,
			@ApiParam(value = "user ID to update user object", required = true) @RequestParam("userID") Long userID)
			throws UserNotFoundException {
		AuditLog auditLogBefore = new AuditLog("Update user", "buisinessTxnNumber", 15,
				new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(new Date()), "INFO", "Started Updating User",
				"REST API");
		loggerService.sendToLog(auditLogBefore);
		service.updateUser(user, userID);
		AuditLog auditLogAfter = new AuditLog("Update user", "buisinessTxnNumber", 15,
				new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(new Date()), "INFO", "Completed Updating User",
				"REST API");
		loggerService.sendToLog(auditLogAfter);
		return ResponseEntity.ok(new StatusResponse<User>(HttpStatus.OK.value(), HttpStatus.OK, Constants.SUCCESS));
	}

	/**
	 * For obtaining the user details by providing user ID.
	 *
	 * @param userID the user ID
	 * @return the users
	 * @throws UserNotFoundException the user not found exception
	 */
//	Point 15:Not to return Null for an API.
	@ApiOperation(value = "Get the user by Id")
	@GetMapping(value = "/users")
	public ResponseEntity<StatusResponse<User>> getUsers(
			@ApiParam(value = "User ID from which user object will retrieve", required = true) @RequestParam("userID") Long userID)
			throws UserNotFoundException {
		AuditLog auditLogBefore = new AuditLog("Get user details", "buisinessTxnNumber", 15,
				new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(new Date()), "INFO",
				"Started Fetching User details", "REST API");
		loggerService.sendToLog(auditLogBefore);
		User user = service.getUser(userID);
		AuditLog auditLogAfter = new AuditLog("Get user details", "buisinessTxnNumber", 15,
				new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(new Date()), "INFO",
				"Completed Fetching User details", "REST API");
		loggerService.sendToLog(auditLogAfter);
//		return new ResponseEntity<User>(user, HttpStatus.OK);
		return ResponseEntity
				.ok(new StatusResponse<User>(HttpStatus.OK.value(), HttpStatus.OK, Constants.SUCCESS, user));

	}

}
