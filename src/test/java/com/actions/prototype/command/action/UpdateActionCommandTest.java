package com.actions.prototype.command.action;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.actions.prototype.dao.ActionDao;
import com.actions.prototype.model.resource.action.Action;

/**
 * <p>
 * UpdateActionCommandTest class.
 * </p>
 * 
 * @author Omar Ortiz.
 */
@RunWith(MockitoJUnitRunner.class)
public class UpdateActionCommandTest {
	
	private UpdateActionCommand command;
	
	@Mock
	private ActionDao dao;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		command = new UpdateActionCommand();
		command.setDao(dao);
		
	}
	
	/**
	 * Test method for {@link com.actions.prototype.command.action.UpdateActionCommand#observe()}.
	 */
	@Test
	public void testObserve() {
		Action action = Action.builder().build();
		command.setAction(action);
		when(dao.update(action)).thenReturn(true);
		Boolean result = command.observe().toBlocking().single();
		assertNotNull(result);
		assertTrue(result);
	}
}
