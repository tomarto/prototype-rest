package com.actions.prototype.command.user;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.actions.prototype.dao.UserDao;
import com.actions.prototype.model.resource.user.User;

/**
 * <p>
 * UpdateUserCommandTest class.
 * </p>
 * 
 * @author Omar Ortiz.
 */
@RunWith(MockitoJUnitRunner.class)
public class UpdateUserCommandTest {
	
	private UpdateUserCommand command;
	
	@Mock
	private UserDao dao;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		command = new UpdateUserCommand();
		command.setDao(dao);
	}
	
	/**
	 * Test method for {@link com.actions.prototype.command.user.UpdateUserCommand#observe()}.
	 */
	@Test
	public void testObserve() {
		String username = "User";
		User user = User.builder().username(username).type("Type").build();
		command.setUser(user);
		when(dao.findByUsername(username)).thenReturn(user);
		when(dao.update(user)).thenReturn(true);
		User result = command.observe().toBlocking().single();
		assertNotNull(result);
		assertEquals(user, result);
	}
}
