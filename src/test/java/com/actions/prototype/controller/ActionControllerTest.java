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

import com.actions.prototype.command.action.ActionCommandFactory;
import com.actions.prototype.command.action.FindAllActionsCommand;
import com.actions.prototype.model.resource.Response;
import com.actions.prototype.model.resource.action.Action;
import com.actions.prototype.model.resource.action.ActionListResponse;
import com.actions.prototype.request.action.ActionRequest;

import rx.Observable;

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
	private ActionCommandFactory factory;
	
	@Mock
	private FindAllActionsCommand command;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ctrl = new ActionController(factory);
	}
	
	/**
	 * Test method for {@link com.actions.prototype.controller.ActionControllerTest#findAll()}.
	 */
	@Test
	public void testFindAll() {
		final ActionRequest request = new ActionRequest();
		final List<Action> list = new ArrayList<>();
		list.add(Action.builder().build());
		when(factory.createFindAllActionsCommand(request)).thenReturn(command);
		when(command.observe()).thenReturn(Observable.just(ActionListResponse.builder().actions(list).build()));
		final Response<ActionListResponse> result = ctrl.findAll(request);
		assertNotNull(result);
		assertNull(result.getErrorTime());
		assertFalse(result.getResult().getActions().isEmpty());
	}
}
