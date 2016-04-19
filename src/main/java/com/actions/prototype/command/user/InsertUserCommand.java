package com.actions.prototype.command.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.actions.prototype.command.ObservableCommand;
import com.actions.prototype.dao.UserDao;
import com.actions.prototype.exception.user.UserException;
import com.actions.prototype.model.User;

import lombok.Setter;
import rx.Observable;

/**
 * <p>
 * InsertUserCommand class.
 * </p>
 * 
 * @author Omar Ortiz.
 */
@Setter
@Service
@Scope("prototype")
public class InsertUserCommand implements ObservableCommand<User> {

	@Autowired
	private UserDao dao;
	
	private User user;
	
	/** {@inheritDoc} */
	@Override
	public Observable<User> observe() {
		if (dao.insert(user)) {
			return Observable.just(user); 
		}
		
		throw new UserException("An error occured while registering user.");
	}
}
