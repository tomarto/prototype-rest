package com.actions.prototype.command.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.actions.prototype.command.ObservableCommand;
import com.actions.prototype.model.Action;
import com.actions.prototype.model.ActionRequest;

/**
 * <p>
 * ActionCommandFactory class.
 * </p>
 * 
 * @author Omar Ortiz.
 */
@Service
public class ActionCommandFactory {
	
	private FindAllActionsCommand findAllActionsCommand;
	private InsertActionCommand insertActionCommand;
	private UpdateActionCommand updateActionCommand;
	private DeleteActionCommand deleteActionCommand;
	
	/**
	 * <p>
	 * Constructor for ActionCommandFactory.
	 * </p>
	 * 
	 * @param findAllActionsCommand
	 *            a {@link com.actions.prototype.command.action.FindAllActionsCommand} object.
	 * @param insertActionCommand
	 *            a {@link com.actions.prototype.command.action.InsertActionCommand} object.
	 * @param updateActionCommand
	 *            a {@link com.actions.prototype.command.action.UpdateActionCommand} object.
	 * @param deleteActionCommand
	 *            a {@link com.actions.prototype.command.action.DeleteActionCommand} object.
	 */
	@Autowired
	public ActionCommandFactory(FindAllActionsCommand findAllActionsCommand, InsertActionCommand insertActionCommand,
			UpdateActionCommand updateActionCommand, DeleteActionCommand deleteActionCommand) {
		this.findAllActionsCommand = findAllActionsCommand;
		this.insertActionCommand = insertActionCommand;
		this.updateActionCommand = updateActionCommand;
		this.deleteActionCommand = deleteActionCommand;
	}
	
	/**
	 * <p>
	 * Creates a new ObservableCommand for find all Actions from DB.
	 * </p>
	 * 
	 * @param request
	 *            a {@link com.actions.prototype.model.ActionRequest} object.
	 *            
	 * @return an {@link com.actions.prototype.command.ObservableCommand<List<Action>>} object.
	 */
	public ObservableCommand<List<Action>> createFindAllActionsCommand(ActionRequest request) {
		findAllActionsCommand.setRequest(request);
		return findAllActionsCommand;
	}
	
	/**
	 * <p>
	 * Creates a new ObservableCommand for inserting a new Action to DB.
	 * </p>
	 * 
	 * @param action
	 *            a {@link com.actions.prototype.model.Action} object.
	 *            
	 * @return an {@link com.actions.prototype.command.ObservableCommand<Boolean>} object.
	 */
	public ObservableCommand<Boolean> createInsertActionCommand(Action action) {
		insertActionCommand.setAction(action);
		return insertActionCommand;
	}
	
	/**
	 * <p>
	 * Creates a new ObservableCommand for updating an existing Action in DB.
	 * </p>
	 * 
	 * @param action
	 *            a {@link com.actions.prototype.model.Action} object.
	 *            
	 * @return an {@link com.actions.prototype.command.ObservableCommand<Boolean>} object.
	 */
	public ObservableCommand<Boolean> createUpdateActionCommand(Action action) {
		updateActionCommand.setAction(action);
		return updateActionCommand;
	}
	
	/**
	 * <p>
	 * Creates a new ObservableCommand for deleting an existing Action in DB.
	 * </p>
	 * 
	 * @param id
	 *            a {@link java.lang.Integer} object.
	 *            
	 * @return an {@link com.actions.prototype.command.ObservableCommand<Boolean>} object.
	 */
	public ObservableCommand<Boolean> createDeleteActionCommand(Integer id) {
		deleteActionCommand.setId(id);
		return deleteActionCommand;
	}
}
