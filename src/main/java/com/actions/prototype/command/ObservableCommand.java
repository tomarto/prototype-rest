package com.actions.prototype.command;

import rx.Observable;

/**
 * <p>
 * Command interface.
 * </p>
 * 
 * @author Omar Ortiz.
 */
public interface ObservableCommand<T> {
	/**
	 * <p>
	 * Runs the implemented command
	 * </p>
	 * 
	 * @return an {@link rx.Observable<T>} object.
	 */
	Observable<T> observe();
}
