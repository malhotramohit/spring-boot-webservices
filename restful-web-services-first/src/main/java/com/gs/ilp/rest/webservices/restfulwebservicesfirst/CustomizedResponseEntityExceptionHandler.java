package com.gs.ilp.rest.webservices.restfulwebservicesfirst;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		ResponseEntity entity =  new ResponseEntity(exceptionResponse,HttpStatus.NOT_FOUND);
		return entity ;
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleNullException(Exception ex, WebRequest request) throws Exception {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		ResponseEntity entity =  new ResponseEntity(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		return entity ;
	}
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getBindingResult().getFieldError().getDefaultMessage(), request.getDescription(false));
		ResponseEntity entity =  new ResponseEntity(exceptionResponse,HttpStatus.BAD_REQUEST);
		return entity ;
	}

}
