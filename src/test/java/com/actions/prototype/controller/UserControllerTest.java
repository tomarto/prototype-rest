package com.actions.prototype.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.actions.prototype.model.Response;
import com.actions.prototype.model.User;
import com.actions.prototype.model.UserRequest;
import com.actions.prototype.service.UserService;

/**
 * <p>
 * UserControllerTest class.
 * </p>
 * 
 * @author Omar Ortiz.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	private static final String USERNAME = "Username";
	private static final String PASSWORD = "Password";
	private UserController ctrl;
	private UserRequest request;
	
	@Mock
	private UserService service;
	
	@Mock
	private Authentication authentication;
	
	@Mock
	private SecurityContext securityContext;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		when(authentication.getName()).thenReturn(USERNAME);
		when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);
		ctrl = new UserController(service);
		request = new UserRequest();
		request.setUsername(USERNAME);
		request.setFirstName("FN");
		request.setLastName("LN");
		request.setPassword(PASSWORD);
		request.setPasswordConfirmation(PASSWORD);
	}

	/**
	 * Test method for {@link com.actions.prototype.controller.UserControllerTest#find()}.
	 */
	@Test
	public void testFind() {
		when(service.getUser(USERNAME)).thenReturn(User.builder().build());
		final Response<User> result = ctrl.find();
		assertNotNull(result);
		assertNull(result.getErrorTime());
	}

	/**
	 * Test method for {@link com.actions.prototype.controller.UserControllerTest#register(com.actions.prototype.model.UserRequest)}.
	 */
	@Test
	public void testRegister() {
		final User user = request.buildUser();
		when(service.insert(user)).thenReturn(user);
		final Response<User> result = ctrl.register(request);
		assertNotNull(result);
		assertNull(result.getErrorTime());
	}

	/**
	 * Test method for {@link com.actions.prototype.controller.UserControllerTest#update(com.actions.prototype.model.UserRequest)}.
	 */
	@Test
	public void testUpdate() {
		final User user = request.buildUser();
		when(service.update(USERNAME, user)).thenReturn(user);
		final Response<User> result = ctrl.update(request);
		assertNotNull(result);
		assertNull(result.getErrorTime());
	}
}
