package com.actions.prototype.command.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.actions.prototype.command.ObservableCommand;
import com.actions.prototype.dao.UserDao;
import com.actions.prototype.model.User;

import lombok.Setter;
import rx.Observable;

/**
 * <p>
 * FindUserCommand class.
 * </p>
 * 
 * @author Omar Ortiz.
 */
@Setter
@Service
@Scope("prototype")
public class FindUserCommand implements ObservableCommand<User> {
	
	@Autowired
	private UserDao dao;
	
	private String username;
	
	/** {@inheritDoc} */
	@Override
	public Observable<User> observe() {
		return Observable.just(dao.findByUsername(username));
	}
}
