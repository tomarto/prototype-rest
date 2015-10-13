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
 * @author Rafael Ortiz.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

	private static final String USERNAME = "Username";
	private static final String ADMIN = "ADMIN";
	private UserService service;
	
	@Mock
	private UserDao dao;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		service = new UserServiceImpl(dao);
	}

	/**
	 * Test method for {@link com.actions.prototype.service.UserServiceImpl#getUser(java.lang.String)}.
	 */
	@Test
	public void testGetUser() {
		when(dao.findByUsername(USERNAME)).thenReturn(new User());
		final User result = service.getUser(USERNAME);
		assertNotNull(result);
	}

	/**
	 * Test method for {@link com.actions.prototype.service.UserServiceImpl#insert(com.actions.prototype.model.User)}.
	 */
	@Test
	public void testInsert() {
		final User user = new User();
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
		final User user = new User();
		when(dao.insert(user)).thenReturn(0);
		service.insert(user);
	}

	/**
	 * Test method for {@link com.actions.prototype.service.UserServiceImpl#update(java.lang.String, com.actions.prototype.model.User)}.
	 */
	@Test
	public void testUpdate() {
		final User old = new User();
		old.setType(ADMIN);
		final User user = new User();
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
		service.update(USERNAME, new User());
	}
	
	/**
	 * Test method for {@link com.actions.prototype.service.UserServiceImpl#update(java.lang.String, com.actions.prototype.model.User)}.
	 */
	@Test(expected = UserException.class)
	public void testUpdateUserException2() {
		final User old = new User();
		old.setType("USER");
		final User user = new User();
		user.setType(ADMIN);
		when(dao.findByUsername(USERNAME)).thenReturn(old);
		when(dao.update(user)).thenReturn(1);
		service.update(USERNAME, user);
	}
}
