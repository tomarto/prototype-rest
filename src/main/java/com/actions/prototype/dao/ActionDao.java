package com.actions.prototype.dao;

import java.util.List;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.actions.prototype.model.Action;
import com.actions.prototype.model.ActionRequest;

/**
 * <p>
 * ActionDao interface.
 * </p>
 * 
 * @author Omar Ortiz.
 */
public interface ActionDao {
	
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
	 * 
	 * @return a {@link java.lang.Boolean} object.
	 */
	Boolean insert(Action action);
	
	/**
	 * <p>
	 * Update an Action record from database.
	 * </p>
	 * 
	 * @param action
	 *            a {@link com.actions.prototype.model.Action} object.
	 * 
	 * @return a {@link java.lang.Boolean} object.
	 */
	Boolean update(Action action);
	
	/**
	 * <p>
	 * Delete an Action record from database.
	 * </p>
	 * 
	 * @param id
	 *            a {@link java.lang.Integer} object.
	 * 
	 * @return a {@link java.lang.Boolean} object.
	 */
	Boolean delete(Integer id);
	
	/**
	 * @param name the name to set. For unit test purposes.
	 */
	void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate);
}
