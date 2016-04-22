package com.actions.prototype.command.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.actions.prototype.command.ObservableCommand;
import com.actions.prototype.dao.ActionDao;
import com.actions.prototype.model.resource.action.ActionListResponse;
import com.actions.prototype.request.action.ActionRequest;

import lombok.Setter;
import rx.Observable;

/**
 * <p>
 * FindAllActionsCommand class.
 * </p>
 * 
 * @author Omar Ortiz.
 */
@Setter
@Service
@Scope("prototype")
public class FindAllActionsCommand implements ObservableCommand<ActionListResponse> {
	
	@Autowired
	private ActionDao dao;
	
	private ActionRequest request;
	
	/** {@inheritDoc} */
	@Override
	public Observable<ActionListResponse> observe() {
		return Observable.just(dao.findAll(request));
	}
}
