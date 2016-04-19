package com.actions.prototype.command.action;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
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
 * FindAllActionsCommandTest class.
 * </p>
 * 
 * @author Omar Ortiz.
 */
@RunWith(MockitoJUnitRunner.class)
public class FindAllActionsCommandTest {
	
	private FindAllActionsCommand command;
	private Action action;
	
	@Mock
	private ActionDao dao;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		action = Action.builder().build();
		command = new FindAllActionsCommand();
		command.setDao(dao);
		
	}
	
	/**
	 * Test method for {@link com.actions.prototype.command.action.FindAllActionsCommand#observe()}.
	 */
	@Test
	public void testObserve() {
		final ActionRequest request = new ActionRequest();
		command.setRequest(request);
		final List<Action> list = new ArrayList<>();
		list.add(action);
		when(dao.findAll(request)).thenReturn(list);
		final List<Action> result = command.observe().toBlocking().single();
		assertNotNull(result);
		assertFalse(result.isEmpty());
	}
}
