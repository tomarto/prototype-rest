package com.actions.prototype.command.user;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.actions.prototype.model.User;

/**
 * <p>
 * UserCommandFactoryTest class.
 * </p>
 * 
 * @author Omar Ortiz.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserCommandFactoryTest {
	
	private UserCommandFactory factory;
	
	@Mock
	private FindUserCommand findUserCommand;
	
	@Mock
	private InsertUserCommand insertUserCommand;
	
	@Mock
	private UpdateUserCommand updateUserCommand;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		factory =  new UserCommandFactory(findUserCommand, insertUserCommand,
				updateUserCommand);
	}
	
	/**
	 * Test method for {@link com.actions.prototype.command.user.UserCommandFactory#createFindUserCommand()}.
	 */
	@Test
	public void testCreateFindUserCommand() {
		assertEquals(findUserCommand, factory.createFindUserCommand("Username"));
	}
	
	/**
	 * Test method for {@link com.actions.prototype.command.user.UserCommandFactory#createInsertActionCommand()}.
	 */
	@Test
	public void testCreateInsertActionCommand() {
		assertEquals(insertUserCommand, factory.createInsertActionCommand(User.builder().build()));
	}
	
	/**
	 * Test method for {@link com.actions.prototype.command.user.UserCommandFactory#createUpdateActionCommand()}.
	 */
	@Test
	public void testCreateUpdateActionCommand() {
		assertEquals(updateUserCommand, factory.createUpdateActionCommand(User.builder().build()));
	}
}
