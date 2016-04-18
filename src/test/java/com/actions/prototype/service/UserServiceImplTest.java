package com.actions.prototype.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.actions.prototype.dao.UserDao;
import com.actions.prototype.exception.user.UserException;
import com.actions.prototype.model.User;

/**
 * <p>
 * UserServiceImplTest class.
 * </p>
 * 
 * @author Omar Ortiz.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

	private static final String USERNAME = "Username";
	private static final String ADMIN = "ADMIN";
	private UserService service;
	private User user;
	
	@Mock
	private UserDao dao;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		user = User.builder().build();
		service = new UserServiceImpl(dao);
	}

	/**
	 * Test method for {@link com.actions.prototype.service.UserServiceImpl#getUser(java.lang.String)}.
	 */
	@Test
	public void testGetUser() {
		when(dao.findByUsername(USERNAME)).thenReturn(user);
		final User result = service.getUser(USERNAME);
		assertNotNull(result);
	}

	/**
	 * Test method for {@link com.actions.prototype.service.UserServiceImpl#insert(com.actions.prototype.model.User)}.
	 */
	@Test
	public void testInsert() {
		when(dao.insert(user)).thenReturn(1);
		User result = service.insert(user);
		assertNotNull(result);
		assertEquals(user, result);
	}
	
	/**
	 * Test method for {@link com.actions.prototype.service.UserServiceImpl#insert(com.actions.prototype.model.User)}.
	 */
	@Test(expected = UserException.class)
	public void testInsertUserException() {
		when(dao.insert(user)).thenReturn(0);
		service.insert(user);
	}

	/**
	 * Test method for {@link com.actions.prototype.service.UserServiceImpl#update(java.lang.String, com.actions.prototype.model.User)}.
	 */
	@Test
	public void testUpdate() {
		final User old = User.builder().build();
		old.setType(ADMIN);
		user.setType(ADMIN);
		when(dao.findByUsername(USERNAME)).thenReturn(old);
		when(dao.update(user)).thenReturn(1);
		final User result = service.update(USERNAME, user);
		assertNotNull(result);
		assertEquals(user, result);
	}
	
	/**
	 * Test method for {@link com.actions.prototype.service.UserServiceImpl#update(java.lang.String, com.actions.prototype.model.User)}.
	 */
	@Test(expected = UserException.class)
	public void testUpdateUserException() {
		when(dao.findByUsername(USERNAME)).thenReturn(null);
		service.update(USERNAME, user);
	}
	
	/**
	 * Test method for {@link com.actions.prototype.service.UserServiceImpl#update(java.lang.String, com.actions.prototype.model.User)}.
	 */
	@Test(expected = UserException.class)
	public void testUpdateUserException2() {
		final User old = User.builder().build();
		old.setType("USER");
		user.setType(ADMIN);
		when(dao.findByUsername(USERNAME)).thenReturn(old);
		when(dao.update(user)).thenReturn(1);
		service.update(USERNAME, user);
	}
}
