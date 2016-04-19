package com.actions.prototype.command.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.actions.prototype.dao.UserDao;
import com.actions.prototype.model.User;

/**
 * <p>
 * InsertUserCommandTest class.
 * </p>
 * 
 * @author Omar Ortiz.
 */
@RunWith(MockitoJUnitRunner.class)
public class InsertUserCommandTest {
	
	private InsertUserCommand command;
	
	@Mock
	private UserDao dao;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		command = new InsertUserCommand();
		command.setDao(dao);
	}
	
	/**
	 * Test method for {@link com.actions.prototype.command.user.InsertUserCommand#observe()}.
	 */
	@Test
	public void testObserve() {
		User user = User.builder().build();
		command.setUser(user);
		when(dao.insert(user)).thenReturn(true);
		User result = command.observe().toBlocking().single();
		assertNotNull(result);
		assertEquals(user, result);
	}
}
