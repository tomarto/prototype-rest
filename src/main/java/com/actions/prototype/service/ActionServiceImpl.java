package com.actions.prototype.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.actions.prototype.dao.ActionDao;
import com.actions.prototype.model.Action;
import com.actions.prototype.model.ActionRequest;

/**
 * <p>
 * ActionServiceImpl class.
 * </p>
 * 
 * @author Omar Ortiz.
 */
@Service
public class ActionServiceImpl implements ActionService {

	private ActionDao dao;

	/**
	 * <p>
	 * Constructor for ActionServiceImpl.
	 * </p>
	 * 
	 * @param dao
	 *            a {@link com.actions.prototype.dao.ActionDao} object.
	 */
	@Autowired
	public ActionServiceImpl(ActionDao dao) {
		this.dao = dao;
	}

	/** {@inheritDoc} */
	@Override
	public List<Action> findAll(ActionRequest request) {
		return dao.findAll(request);
	}

	/** {@inheritDoc} */
	@Override
	public void insert(Action action) {
		dao.insert(action);
	}
	
	/** {@inheritDoc} */
	@Override
	public void update(Action action) {
		dao.update(action);
	}

	/** {@inheritDoc} */
	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}
}
