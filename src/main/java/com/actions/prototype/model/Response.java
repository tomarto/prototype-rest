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
	private long errorTime;
	
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
	 * @param result
	 *            a {@link T} object.
	 * @param errorTime
	 *            a {@link T} object.
	 */
	public Response(T result, long errorTime) {
		this.result = result;
		this.errorTime = errorTime;
	}

	/**
	 * @return the result
	 */
	public T getResult() {
		return result;
	}
	
	/**
	 * @return the errorTime
	 */
	public long getErrorTime() {
		return errorTime;
	}
}
