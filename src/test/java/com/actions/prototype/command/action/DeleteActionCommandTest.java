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

/**
 * <p>
 * DeleteActionCommandTest class.
 * </p>
 * 
 * @author Omar Ortiz.
 */
@RunWith(MockitoJUnitRunner.class)
public class DeleteActionCommandTest {
	
	private DeleteActionCommand command;
	
	@Mock
	private ActionDao dao;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		command = new DeleteActionCommand();
		command.setDao(dao);
		
	}
	
	/**
	 * Test method for {@link com.actions.prototype.command.action.DeleteActionCommand#observe()}.
	 */
	@Test
	public void testObserve() {
		command.setId(1);
		when(dao.delete(1)).thenReturn(true);
		Boolean result = command.observe().toBlocking().single();
		assertNotNull(result);
		assertTrue(result);
	}
}
