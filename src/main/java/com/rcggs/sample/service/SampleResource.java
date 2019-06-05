package com.rcggs.sample.service;

import com.rcggs.sample.dto.Userdto;
import com.rcggs.sample.exception.ConstraintsException;
import com.rcggs.sample.exception.UserNotFoundException;
import com.rcggs.sample.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface SampleResource.
 */
public interface SampleResource {

	/**
	 * Gets the user.
	 *
	 * @param ID the id
	 * @return the user
	 * @throws UserNotFoundException the user not found exception
	 */
	public User getUser(Long ID) throws UserNotFoundException;

	/**
	 * Creates the user.
	 *
	 * @param user the user
	 * @return the string
	 * @throws UserNotFoundException 
	 */
	public String createUser(Userdto user) throws UserNotFoundException;

	/**
	 * Update user.
	 *
	 * @param user the user
	 * @param userID the user ID
	 * @return the string
	 * @throws UserNotFoundException 
	 */
	public User updateUser(Userdto user,Long userID) throws UserNotFoundException,ConstraintsException;


}
