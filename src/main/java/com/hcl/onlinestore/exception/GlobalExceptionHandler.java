package com.hcl.onlinestore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorResponse> globalExceptionHandler(ProductNotFoundException productNotFoundException,
			WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(productNotFoundException.getMessage(),
				HttpStatus.NOT_FOUND.value(), request.getDescription(false));
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ErrorResponse> globalExceptionHandler(NullPointerException nullPointerException,
			WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(nullPointerException.getMessage(), HttpStatus.NOT_FOUND.value(),
				request.getDescription(false));
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

}
