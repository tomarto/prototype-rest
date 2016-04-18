package com.actions.prototype.service;

import com.actions.prototype.model.User;

/**
 * <p>
 * UserService interface.
 * </p>
 * 
 * @author Omar Ortiz.
 */
public interface UserService {

	/**
	 * <p>
	 * Retrieves a User matching its username and password.
	 * </p>
	 * 
	 * @param username
	 *            a {@link java.lang.String} object.
	 *            
	 * @return a {@link com.actions.prototype.model.User} object.
	 */
	User getUser(String username);
	
	/**
	 * <p>
	 * Inserts a User record.
	 * </p>
	 * 
	 * @param user
	 *            a {@link com.actions.prototype.model.User} object.
	 * 
	 * @return a {@link com.actions.prototype.model.User} object.
	 */
	User insert(User user);
	
	/**
	 * <p>
	 * Updates a User record.
	 * </p>
	 * 
	 * @param loggedUsername
	 *            a {@link java.lang.String} object.
	 * @param user
	 *            a {@link com.actions.prototype.model.User} object.
	 * 
	 * @return a {@link com.actions.prototype.model.User} object.
	 */
	User update(String loggedUsername, User user);
}
