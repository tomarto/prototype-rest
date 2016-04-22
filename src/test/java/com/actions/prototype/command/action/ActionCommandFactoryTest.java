package com.actions.prototype.command.action;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.actions.prototype.model.resource.action.Action;
import com.actions.prototype.request.action.ActionRequest;

/**
 * <p>
 * ActionCommandFactoryTest class.
 * </p>
 * 
 * @author Omar Ortiz.
 */
@RunWith(MockitoJUnitRunner.class)
public class ActionCommandFactoryTest {

	private ActionCommandFactory factory;
	
	@Mock
	private FindAllActionsCommand findAllActionsCommand;
	
	@Mock
	private InsertActionCommand insertActionCommand;
	
	@Mock
	private UpdateActionCommand updateActionCommand;
	
	@Mock
	private DeleteActionCommand deleteActionCommand;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		factory =  new ActionCommandFactory(findAllActionsCommand, insertActionCommand,
				updateActionCommand, deleteActionCommand);
	}
	
	/**
	 * Test method for {@link com.actions.prototype.command.action.ActionCommandFactory#createFindAllActionsCommand()}.
	 */
	@Test
	public void testCreateFindAllActionsCommand() {
		assertEquals(findAllActionsCommand, factory.createFindAllActionsCommand(new ActionRequest()));
	}
	
	/**
	 * Test method for {@link com.actions.prototype.command.action.ActionCommandFactory#createInsertActionCommand()}.
	 */
	@Test
	public void testCreateInsertActionCommand() {
		assertEquals(insertActionCommand, factory.createInsertActionCommand(Action.builder().build()));
	}
	
	/**
	 * Test method for {@link com.actions.prototype.command.action.ActionCommandFactory#createUpdateActionCommand()}.
	 */
	@Test
	public void testCreateUpdateActionCommand() {
		assertEquals(updateActionCommand, factory.createUpdateActionCommand(Action.builder().build()));
	}
	
	/**
	 * Test method for {@link com.actions.prototype.command.action.ActionCommandFactory#createDeleteActionCommand()}.
	 */
	@Test
	public void testCreateDeleteActionCommand() {
		assertEquals(deleteActionCommand, factory.createDeleteActionCommand(1));
	}
}
