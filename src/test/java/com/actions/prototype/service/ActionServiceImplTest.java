package com.actions.prototype.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.actions.prototype.dao.ActionDao;
import com.actions.prototype.model.Action;
import com.actions.prototype.model.ActionRequest;

/**
 * <p>
 * ActionServiceImplTest class.
 * </p>
 * 
 * @author Rafael Ortiz.
 */
@RunWith(MockitoJUnitRunner.class)
public class ActionServiceImplTest {

	private ActionService service;
	
	@Mock
	private ActionDao dao;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		service = new ActionServiceImpl(dao);
	}

	/**
	 * Test method for {@link com.actions.prototype.service.ActionServiceImpl#findAll()}.
	 */
	@Test
	public void testFindAll() {
		final ActionRequest request = new ActionRequest();
		final List<Action> list = new ArrayList<>();
		list.add(new Action());
		when(dao.findAll(request)).thenReturn(list);
		final List<Action> result = service.findAll(request);
		assertNotNull(result);
		assertFalse(result.isEmpty());
	}

	/**
	 * Test method for {@link com.actions.prototype.service.ActionServiceImpl#insert(com.actions.prototype.model.Action)}.
	 */
	@Test
	public void testInsert() {
		final Action action = new Action();
		service.insert(action);
		verify(dao).insert(action);
	}

	/**
	 * Test method for {@link com.actions.prototype.service.ActionServiceImpl#update(com.actions.prototype.model.Action)}.
	 */
	@Test
	public void testUpdate() {
		final Action action = new Action();
		service.update(action);
		verify(dao).update(action);
	}

	/**
	 * Test method for {@link com.actions.prototype.service.ActionServiceImpl#delete(java.lang.Integer)}.
	 */
	@Test
	public void testDelete() {
		service.delete(1);
		verify(dao).delete(1);
	}
}
