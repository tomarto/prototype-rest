package com.actions.prototype.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.actions.prototype.model.Response;
import com.actions.prototype.model.User;
import com.actions.prototype.model.UserRequest;
import com.actions.prototype.service.UserService;

/**
 * <p>
 * UserController class. Controller defining a RESTful API.
 * </p>
 * 
 * @author Rafael Ortiz.
 */
@Controller
public class UserController {

	private UserService service;
	
	/**
	 * <p>
	 * Constructor for UserController.
	 * </p>
	 * 
	 * @param service
	 *            a {@link com.actions.prototype.service.UserService} object.
	 */
	@Autowired
	public UserController(UserService service) {
		this.service = service;
	}
	
	/**
	 * <p>
	 * Retrieves a single User.
	 * </p>
	 * 
	 * @return a {@link com.actions.prototype.model.Response<User>} object.
	 */
	@RequestMapping(value = "/user", method = POST, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	public @ResponseBody Response<User> find() {
		return new Response<>(service.getUser(getLoggedUsername()));
	}
	
	/**
	 * <p>
	 * Inserts a User.
	 * </p>
	 * 
	 * @return a {@link com.actions.prototype.model.Response<User>} object.
	 */
	@RequestMapping(value = "/user/register", method = POST, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	public @ResponseBody Response<User> register(@Valid @RequestBody UserRequest request) {
		return new Response<>(service.insert(request.buildUser()));
	}
	
	/**
	 * <p>
	 * Updates a User.
	 * </p>
	 * 
	 * @return a {@link com.actions.prototype.model.Response<User>} object.
	 */
	@RequestMapping(value = "/user/update", method = PUT, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	public @ResponseBody Response<User> update(@Valid @RequestBody UserRequest request) {
		return new Response<>(service.update(getLoggedUsername(), request.buildUser()));
	}
	
	/**
	 * <p>
	 * Retrieved the logged username.
	 * </p>
	 * 
	 * @return a {@link java.lang.String} object.
	 */
	private String getLoggedUsername() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}
