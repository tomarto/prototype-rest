package com.actions.prototype.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.actions.prototype.model.Action;
import com.actions.prototype.model.ActionRequest;
import com.actions.prototype.model.Response;
import com.actions.prototype.service.ActionService;

/**
 * <p>
 * ActionController class. Controller defining a RESTful API.
 * </p>
 * 
 * @author Omar Ortiz.
 */
@Controller
public class ActionController {

	private ActionService service;
	
	/**
	 * <p>
	 * Constructor for ActionService.
	 * </p>
	 * 
	 * @param service
	 *            a {@link com.actions.prototype.service.ActionService} object.
	 */
	@Autowired
	public ActionController(ActionService service) {
		this.service = service;
	}

	/**
	 * <p>
	 * Retrieves all the Action records from database.
	 * </p>
	 * 
	 * @return a {@link com.actions.prototype.model.Response<List<Action>>} object.
	 */
	@RequestMapping(value = "/actions", method = GET, produces = APPLICATION_JSON_VALUE)
	public @ResponseBody Response<List<Action>> findAll(@Valid ActionRequest request) {
		return new Response<>(service.findAll(request));
	}
}
