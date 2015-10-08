package com.actions.prototype.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.actions.prototype.dao.UserDao;
import com.actions.prototype.exception.user.UserException;
import com.actions.prototype.model.User;

/**
 * <p>
 * UserServiceImpl class.
 * </p>
 * 
 * @author Rafael Ortiz.
 */
@Service
public class UserServiceImpl implements UserService {

	private UserDao dao;

	/**
	 * <p>
	 * Constructor for UserServiceImpl.
	 * </p>
	 * 
	 * @param dao
	 *            a {@link com.actions.prototype.dao.UserDao} object.
	 */
	@Autowired
	public UserServiceImpl(UserDao dao) {
		this.dao = dao;
	}
	
	/** {@inheritDoc} */
	@Override
	public User getUser(String username) {
		return dao.findByUsername(username);
	}

	/** {@inheritDoc} */
	@Override
	public User insert(User user) {
		if(dao.insert(user) != 1) {
			throw new UserException("An error occured while registering user.");
		}
		return user;
	}
	
	/** {@inheritDoc} */
	@Override
	public User update(String loggedUsername, User user) {
		final User old = dao.findByUsername(loggedUsername);
		if(old == null) {
			throw new UserException("User not found.");
		}
		if(!old.getType().equals(user.getType()) || dao.update(user) != 1) {
			throw new UserException("An error occured while updating user.");
		}
		return user;
	}
}
