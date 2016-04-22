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

import com.actions.prototype.command.user.FindUserCommand;
import com.actions.prototype.command.user.InsertUserCommand;
import com.actions.prototype.command.user.UpdateUserCommand;
import com.actions.prototype.command.user.UserCommandFactory;
import com.actions.prototype.model.resource.Response;
import com.actions.prototype.model.resource.user.User;
import com.actions.prototype.request.user.UserRequest;

import rx.Observable;

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
	private UserCommandFactory factory;
	
	@Mock
	private FindUserCommand findUserCommand;
	
	@Mock
	private InsertUserCommand insertUserCommand;
	
	@Mock
	private UpdateUserCommand updateUserCommand;
	
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
		ctrl = new UserController(factory);
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
		when(factory.createFindUserCommand(USERNAME)).thenReturn(findUserCommand);
		when(findUserCommand.observe()).thenReturn(Observable.just(User.builder().build()));
		final Response<User> result = ctrl.find();
		assertNotNull(result);
		assertNull(result.getErrorTime());
	}

	/**
	 * Test method for {@link com.actions.prototype.controller.UserControllerTest#register(com.actions.prototype.request.user.UserRequest)}.
	 */
	@Test
	public void testRegister() {
		final User user = request.buildUser();
		when(factory.createInsertActionCommand(user)).thenReturn(insertUserCommand);
		when(insertUserCommand.observe()).thenReturn(Observable.just(user));
		final Response<User> result = ctrl.register(request);
		assertNotNull(result);
		assertNull(result.getErrorTime());
	}

	/**
	 * Test method for {@link com.actions.prototype.controller.UserControllerTest#update(com.actions.prototype.request.user.UserRequest)}.
	 */
	@Test
	public void testUpdate() {
		final User user = request.buildUser();
		when(factory.createUpdateActionCommand(user)).thenReturn(updateUserCommand);
		when(updateUserCommand.observe()).thenReturn(Observable.just(user));
		final Response<User> result = ctrl.update(request);
		assertNotNull(result);
		assertNull(result.getErrorTime());
	}
}
