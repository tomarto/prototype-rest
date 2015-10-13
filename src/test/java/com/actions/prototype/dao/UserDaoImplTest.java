package com.actions.prototype.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.actions.prototype.exception.user.UserException;
import com.actions.prototype.model.User;

/**
 * <p>
 * UserDaoImplTest class.
 * </p>
 * 
 * @author Rafael Ortiz.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserDaoImplTest {

	private static final String USERNAME = "Username";
	private UserDao dao;
	
	@Mock
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Mock
	private DataSource dataSource;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dao = new UserDaoImpl(dataSource);
		dao.setJdbcTemplate(jdbcTemplate);
	}

	/**
	 * Test method for {@link com.actions.prototype.dao.UserDaoImpl#findByUsername(java.lang.String)}.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testFindByUsername() {
		when(jdbcTemplate.queryForObject(anyString(), any(MapSqlParameterSource.class), any(RowMapper.class)))
				.thenReturn(new User());
		assertNotNull(dao.findByUsername(USERNAME));
		when(jdbcTemplate.queryForObject(anyString(), any(MapSqlParameterSource.class), any(RowMapper.class)))
				.thenThrow(new IncorrectResultSizeDataAccessException(0));
		assertNull(dao.findByUsername(USERNAME));
	}

	/**
	 * Test method for {@link com.actions.prototype.dao.UserDaoImpl#insert(com.actions.prototype.model.User)}.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testInsert() {
		final User user = new User();
		user.setUsername(USERNAME);
		when(jdbcTemplate.queryForObject(anyString(), any(MapSqlParameterSource.class), any(RowMapper.class)))
				.thenReturn(null);
		when(jdbcTemplate.update(anyString(), any(MapSqlParameterSource.class))).thenReturn(0).thenReturn(1);
		
		assertEquals(0, dao.insert(user));
		assertEquals(1, dao.insert(user));
	}
	
	/**
	 * Test method for {@link com.actions.prototype.dao.UserDaoImpl#insert(com.actions.prototype.model.User)}.
	 */
	@SuppressWarnings("unchecked")
	@Test(expected = UserException.class)
	public void testInsertUserException() {
		final User user = new User();
		user.setUsername(USERNAME);
		when(jdbcTemplate.queryForObject(anyString(), any(MapSqlParameterSource.class), any(RowMapper.class)))
				.thenReturn(user);
		dao.insert(user);
	}

	/**
	 * Test method for {@link com.actions.prototype.dao.UserDaoImpl#update(com.actions.prototype.model.User)}.
	 */
	@Test
	public void testUpdate() {
		when(jdbcTemplate.update(anyString(), any(MapSqlParameterSource.class))).thenReturn(1);
		assertEquals(1, dao.update(new User()));
	}
}