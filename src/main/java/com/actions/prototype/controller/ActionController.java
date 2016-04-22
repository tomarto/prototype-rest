package com.actions.prototype.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.actions.prototype.command.action.ActionCommandFactory;
import com.actions.prototype.model.resource.Response;
import com.actions.prototype.model.resource.action.ActionListResponse;
import com.actions.prototype.request.action.ActionRequest;

/**
 * <p>
 * ActionController class. Controller defining a RESTful API.
 * </p>
 * 
 * @author Omar Ortiz.
 */
@Controller
public class ActionController {

	private ActionCommandFactory factory;
	
	/**
	 * <p>
	 * Constructor for ActionService.
	 * </p>
	 * 
	 * @param factory
	 *            a {@link com.actions.prototype.command.action.ActionCommandFactory} object.
	 */
	@Autowired
	public ActionController(ActionCommandFactory factory) {
		this.factory = factory;
	}

	/**
	 * <p>
	 * Retrieves all the Action records from database.
	 * </p>
	 * 
	 * @return a {@link com.actions.prototype.model.Response<List<Action>>} object.
	 */
	@RequestMapping(value = "/actions", method = GET, produces = APPLICATION_JSON_VALUE)
	public @ResponseBody Response<ActionListResponse> findAll(@Valid ActionRequest request) {
		return new Response<>(factory.createFindAllActionsCommand(request).observe().toBlocking().single());
	}
}
