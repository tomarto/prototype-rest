package com.actions.prototype.command.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.actions.prototype.command.ObservableCommand;
import com.actions.prototype.dao.ActionDao;
import com.actions.prototype.model.Action;

import lombok.Setter;
import rx.Observable;

/**
 * <p>
 * UpdateActionCommand class.
 * </p>
 * 
 * @author Omar Ortiz.
 */
@Setter
@Service
@Scope("prototype")
public class UpdateActionCommand implements ObservableCommand<Boolean> {

	@Autowired
	private ActionDao dao;

	private Action action;

	/** {@inheritDoc} */
	@Override
	public Observable<Boolean> observe() {
		return Observable.just(dao.update(action));
	}
}
