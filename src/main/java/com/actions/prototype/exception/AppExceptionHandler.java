package com.actions.prototype.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.actions.prototype.exception.user.UserException;
import com.actions.prototype.model.Response;

/**
 * <p>
 * AppExceptionHandler class.
 * </p>
 * 
 * @author Rafael Ortiz.
 */
@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * <p>
	 * Intercepts exceptions and return a Response object with status 500.
	 * </p>
	 * 
	 * @return a {@link com.actions.prototype.model.Response<String>} object.
	 */
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error in operation")
	@ExceptionHandler({ UserException.class })
	protected Response<String> handleException(RuntimeException e, WebRequest request) {
		return new Response<>(e.getMessage(), Instant.now().toEpochMilli());
	}
}
