package com.actions.prototype.service;

import java.util.List;

import com.actions.prototype.model.Action;
import com.actions.prototype.model.ActionRequest;

/**
 * <p>
 * ActionService interface.
 * </p>
 * 
 * @author Rafael Ortiz.
 */
public interface ActionService {
	
	/**
	 * <p>
	 * Retrieves all the Action records from database.
	 * </p>
	 * 
	 * @param request
	 *            a {@link com.actions.prototype.model.ActionRequest} object.
	 *            
	 * @return a {@link java.util.List<Action>} object.
	 */
	List<Action> findAll(ActionRequest request);
	
	/**
	 * <p>
	 * Insert an Action record to database.
	 * </p>
	 * 
	 * @param action
	 *            a {@link com.actions.prototype.model.Action} object.
	 */
	void insert(Action action);
	
	/**
	 * <p>
	 * Update an Action record from database.
	 * </p>
	 * 
	 * @param action
	 *            a {@link com.actions.prototype.model.Action} object.
	 */
	void update(Action action);
	
	/**
	 * <p>
	 * Delete an Action record from database.
	 * </p>
	 * 
	 * @param id
	 *            a {@link java.lang.Integer} object.
	 */
	void delete(Integer id);
}
