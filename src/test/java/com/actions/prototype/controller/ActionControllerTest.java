package com.actions.prototype.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.actions.prototype.model.Action;
import com.actions.prototype.model.ActionRequest;
import com.actions.prototype.model.Response;
import com.actions.prototype.service.ActionService;

/**
 * <p>
 * ActionControllerTest class.
 * </p>
 * 
 * @author Omar Ortiz.
 */
@RunWith(MockitoJUnitRunner.class)
public class ActionControllerTest {

	private ActionController ctrl;
	
	@Mock
	private ActionService service;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ctrl = new ActionController(service);
	}
	
	/**
	 * Test method for {@link com.actions.prototype.controller.ActionControllerTest#findAll()}.
	 */
	@Test
	public void testFindAll() {
		final ActionRequest request = new ActionRequest();
		final List<Action> list = new ArrayList<>();
		list.add(Action.builder().build());
		when(service.findAll(request)).thenReturn(list);
		final Response<List<Action>> result = ctrl.findAll(request);
		assertNotNull(result);
		assertNull(result.getErrorTime());
		assertFalse(result.getResult().isEmpty());
	}
}
