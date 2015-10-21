package com.actions.prototype.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.actions.prototype.model.Action;
import com.actions.prototype.model.ActionRequest;

/**
 * <p>
 * ActionDaoImplTest class.
 * </p>
 * 
 * @author Rafael Ortiz.
 */
@RunWith(MockitoJUnitRunner.class)
public class ActionDaoImplTest {

	private ActionDao dao;
	
	@Mock
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Mock
	private DataSource dataSource;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dao = new ActionDaoImpl(dataSource);
		dao.setJdbcTemplate(jdbcTemplate);
	}

	/**
	 * Test method for {@link com.actions.prototype.dao.ActionDaoImpl#findAll()}.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testFindAll() {
		final List<Action> list = new ArrayList<>();
		list.add(new Action());
		when(jdbcTemplate.query(anyString(), any(MapSqlParameterSource.class), any(RowMapper.class))).thenReturn(list);
		final List<Action> result = dao.findAll(new ActionRequest());
		assertNotNull(result);
		assertFalse(result.isEmpty());
	}

	/**
	 * Test method for {@link com.actions.prototype.dao.ActionDaoImpl#insert(com.actions.prototype.model.Action)}.
	 */
	@Test
	public void testInsert() {
		dao.insert(new Action());
		verify(jdbcTemplate).update(anyString(), any(MapSqlParameterSource.class));
	}

	/**
	 * Test method for {@link com.actions.prototype.dao.ActionDaoImpl#update(com.actions.prototype.model.Action)}.
	 */
	@Test
	public void testUpdate() {
		dao.update(new Action());
		verify(jdbcTemplate).update(anyString(), any(MapSqlParameterSource.class));
	}

	/**
	 * Test method for {@link com.actions.prototype.dao.ActionDaoImpl#delete(java.lang.Integer)}.
	 */
	@Test
	public void testDelete() {
		dao.delete(1);
		verify(jdbcTemplate).update(anyString(), any(MapSqlParameterSource.class));
	}
}
