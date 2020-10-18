package com.rest.cofeeandelk.common;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rest.cofeeandelk.api.response.ErrorResponse;
import com.rest.cofeeandelk.exception.IllegalApiParamException;

@RestControllerAdvice
public class MyGlobalExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(MyGlobalExceptionHandler.class);
	@ExceptionHandler(value = IllegalApiParamException.class)
	public ResponseEntity<ErrorResponse> handleIllegalApiParamException(IllegalApiParamException e) {

		var message = "Exception API Param from GlobalExceptionHandler : " + e.getMessage();
		LOG.warn(message);
		var errorResponse = new ErrorResponse(message, LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

	}

}
