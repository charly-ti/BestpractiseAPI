package com.rcggs.sample.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rcggs.sample.dto.AuditLog;
import com.rcggs.sample.dto.Userdto;
import com.rcggs.sample.exception.ConstraintsException;
import com.rcggs.sample.exception.UserNotFoundException;
import com.rcggs.sample.model.User;
import com.rcggs.sample.repository.UserRepository;

import antlr.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class SampleResourceImpl.
 */
@Service
class SampleResourceImpl implements SampleResource {
	

	/** The user repository. */
//  Point 12:Preventing Leaks in the implementation classes while exposing the API.
	@Autowired
	Loggerservice loggerService;
	@Autowired
	UserRepository userRepository;


//  Point 16:Good practice to keep return type as optional.

	/**
	 * Gets the user.
	 *
	 * @param ID the id
	 * @return the user
	 * @throws UserNotFoundException the user not found exception
	 */
	@Override
	public User getUser(Long ID) throws UserNotFoundException {
		Optional<User> user = userRepository.findUserById(ID);
		if (!user.isPresent()) {
			AuditLog auditLog = new AuditLog("Get user details", "buisinessTxnNumber", 15,
					new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(new Date()), "ERROR", "Requested user not available for the given ID",
					"REST API");
			loggerService.sendToLog(auditLog);
			throw new UserNotFoundException(HttpStatus.NOT_FOUND.value(), "Requested user not available for the given ID");
		} else {
			return user.get();
		}
	}

	/**
	 * Creates the user.
	 *
	 * @param user the user
	 * @return the string
	 * @throws UserNotFoundException
	 */
	@Override
	public String createUser(Userdto user) throws UserNotFoundException {

			User mappedUser = new User();
			mappedUser = setUserDetails(mappedUser, user);
			userRepository.save(mappedUser);
			return String.valueOf(mappedUser.getId());

	}

	/**
	 * Update user.
	 *
	 * @param newuser the newuser
	 * @param userID  the user ID
	 * @return the string
	 * @throws UserNotFoundException 
	 */
	@Override
	public User updateUser(Userdto newUser, Long userID) throws UserNotFoundException, ConstraintsException {
		Long ID = userID.longValue();
		Optional<User> user = userRepository.findUserById(ID);
		if(!user.isPresent()) {
			AuditLog auditLog = new AuditLog("Update user", "buisinessTxnNumber", 15,
					new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(new Date()), "ERROR", "Requested user does not exist for the given ID",
					"REST API");
			loggerService.sendToLog(auditLog);
			throw new UserNotFoundException(HttpStatus.NOT_FOUND.value(), "Requested user does not exist for the given ID");
		}else {
			User mappedUser = setUserDetails(user.get(), newUser);
			userRepository.save(mappedUser);
			return mappedUser;
		}
		

	}

	/**
	 * Sets the user details.
	 *
	 * @param user    the user
	 * @param newUser the new user
	 * @return the user
	 */
//	Point 13:Intentional Inheritance by making the class final which makes it  difficult for extending or overriding.
	@Deprecated
	final User setUserDetails(User user, Userdto newUser) {
		user.setName(newUser.getName());
		user.setOrganization(newUser.getOrganization());
		user.setEmail(newUser.getEmail());
		user.setPhoneNo(newUser.getPhoneNo());
		user.setSalary(newUser.getSalary());
		return user;

	}

	final User mapUserDetails(User user, User newUser) {
//		User mappedUser = mapUserDetails(user, newUser);
		ModelMapper modelMapper = new ModelMapper();
		User userMapped = modelMapper.map(newUser, User.class);
		return userMapped;
	}

	

}
