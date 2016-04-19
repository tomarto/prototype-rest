package com.actions.prototype.command.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.actions.prototype.command.ObservableCommand;
import com.actions.prototype.model.User;

/**
 * <p>
 * UserCommandFactory class.
 * </p>
 * 
 * @author Omar Ortiz.
 */
@Service
public class UserCommandFactory {
	
	private FindUserCommand findUserCommand;
	private InsertUserCommand insertUserCommand;
	private UpdateUserCommand updateUserCommand;
	
	/**
	 * <p>
	 * Constructor for UserCommandFactory.
	 * </p>
	 * 
	 * @param findUserCommand
	 *            a {@link com.actions.prototype.command.user.FindUserCommand} object.
	 * @param insertUserCommand
	 *            a {@link com.actions.prototype.command.user.InsertUserCommand} object.
	 * @param updateUserCommand
	 *            a {@link com.actions.prototype.command.user.UpdateUserCommand} object.
	 */
	@Autowired
	public UserCommandFactory(FindUserCommand findUserCommand, InsertUserCommand insertUserCommand,
			UpdateUserCommand updateUserCommand) {
		this.findUserCommand = findUserCommand;
		this.insertUserCommand = insertUserCommand;
		this.updateUserCommand = updateUserCommand;
	}
	
	/**
	 * <p>
	 * Creates a new ObservableCommand for find specific user in DB.
	 * </p>
	 * 
	 * @param username
	 *            a {@link java.lang.String} object.
	 *            
	 * @return an {@link com.actions.prototype.command.ObservableCommand<User>} object.
	 */
	public ObservableCommand<User> createFindUserCommand(String username) {
		findUserCommand.setUsername(username);
		return findUserCommand;
	}
	
	/**
	 * <p>
	 * Creates a new ObservableCommand for inserting a new User to DB.
	 * </p>
	 * 
	 * @param user
	 *            a {@link com.actions.prototype.model.User} object.
	 *            
	 * @return an {@link com.actions.prototype.command.ObservableCommand<User>} object.
	 */
	public ObservableCommand<User> createInsertActionCommand(User user) {
		insertUserCommand.setUser(user);
		return insertUserCommand;
	}
	
	/**
	 * <p>
	 * Creates a new ObservableCommand for updating an existing User in DB.
	 * </p>
	 * 
	 * @param user
	 *            a {@link com.actions.prototype.model.User} object.
	 *            
	 * @return an {@link com.actions.prototype.command.ObservableCommand<User>} object.
	 */
	public ObservableCommand<User> createUpdateActionCommand(User user) {
		updateUserCommand.setUser(user);
		return updateUserCommand;
	}
}
