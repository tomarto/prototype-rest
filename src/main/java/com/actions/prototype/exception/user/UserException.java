package com.actions.prototype.exception.user;

public class UserException extends RuntimeException {

	/** Constant <code>serialVersionUID=-1169184238225423021L</code> */
	private static final long serialVersionUID = -1169184238225423021L;

	/**
	 * <p>
	 * Constructor for UserException.
	 * </p>
	 * 
	 * @param message
	 *            a {@link java.lang.String} object.
	 */
	public UserException(String message) {
        super(message);
    }
}
