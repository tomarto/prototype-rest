package com.actions.prototype.model;

import java.io.Serializable;

/**
 * <p>
 * Response class.
 * </p>
 * 
 * @author Rafael Ortiz.
 */
public class Response<T> implements Serializable {

	/** Constant <code>serialVersionUID=-688131667179924029L</code> */
	private static final long serialVersionUID = -688131667179924029L;
	
	private T result;
	private String errorMessage;
	private Long errorTime;
	
	/**
	 * <p>
	 * Constructor for Response.
	 * </p>
	 * 
	 * @param result
	 *            a {@link T} object.
	 */
	public Response(T result) {
		this.result = result;
	}
	
	/**
	 * <p>
	 * Constructor for Response.
	 * </p>
	 * 
	 * @param errorMessage
	 *            a {@link java.lang.String} object.
	 * @param errorTime
	 *            a {@link java.lang.Long} object.
	 */
	public Response(String errorMessage, Long errorTime) {
		this.errorMessage = errorMessage;
		this.errorTime = errorTime;
	}

	/**
	 * @return the result
	 */
	public T getResult() {
		return result;
	}
	
	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	
	/**
	 * @return the errorTime
	 */
	public Long getErrorTime() {
		return errorTime;
	}
}
