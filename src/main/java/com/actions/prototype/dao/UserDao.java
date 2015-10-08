package com.actions.prototype.dao;

import com.actions.prototype.model.User;

/**
 * <p>
 * UserDao interface.
 * </p>
 * 
 * @author Rafael Ortiz.
 */
public interface UserDao {

	/**
	 * <p>
	 * Retrieves a User matching its username.
	 * </p>
	 * 
	 * @param username
	 *            a {@link java.lang.String} object.
	 * 
	 * @return a {@link com.actions.prototype.model.User} object.
	 */
	User findByUsername(String username);
	
	/**
	 * <p>
	 * Insert a User record to database.
	 * </p>
	 * 
	 * @param user
	 *            a {@link com.actions.prototype.model.User} object.
	 *            
	 * @return an int primitive type.
	 */
	int insert(User user);
	
	/**
	 * <p>
	 * Update a User record to database.
	 * </p>
	 * 
	 * @param user
	 *            a {@link com.actions.prototype.model.User} object.
	 *            
	 * @return an int primitive type.
	 */
	int update(User user);
}
