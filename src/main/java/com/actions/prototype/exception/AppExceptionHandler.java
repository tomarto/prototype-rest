package com.actions.prototype.exception;

import java.time.Instant;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.actions.prototype.model.resource.Response;

/**
 * <p>
 * AppExceptionHandler class.
 * </p>
 * 
 * @author Omar Ortiz.
 */
@ControllerAdvice
public class AppExceptionHandler {

	private static final Logger LOGGER = Logger.getLogger(AppExceptionHandler.class);
	/**
	 * <p>
	 * Intercepts exceptions and return a Response object with status 500.
	 * </p>
	 * 
	 * @return a {@link com.actions.prototype.model.Response<String>} object.
	 */
	@ExceptionHandler(value = RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	protected Response<String> defaultErrorHandler(RuntimeException ex) {
		final long error = Instant.now().toEpochMilli();
		final StringBuilder stackTrace = new StringBuilder();
		Arrays.stream(ex.getStackTrace()).forEach(e -> stackTrace.append(e + "\r\n"));
		LOGGER.warn(String.format("Error code: %s - Stack trace: %s", error, stackTrace));
		
		return new Response<>(ex.getMessage(), error);
	}
}
