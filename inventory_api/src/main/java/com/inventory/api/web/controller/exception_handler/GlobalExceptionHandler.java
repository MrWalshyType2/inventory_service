package com.inventory.api.web.controller.exception_handler;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.inventory.api.service.exception.ItemNotFoundException;
import com.inventory.api.service.exception.NoItemsFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List<?>> validationErrorHandler(ConstraintViolationException e) {
		List<String> errors = new ArrayList<>(e.getConstraintViolations().size());
		
		e.getConstraintViolations().forEach(violation -> {
			errors.add(violation.getPropertyPath() + " : " + violation.getMessage());
		});
		
		return new ResponseEntity<List<?>>(errors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BindException.class)
	public ResponseEntity<List<?>> handleBindException(BindException e) {
		return new ResponseEntity<List<?>>(e.getAllErrors(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoItemsFoundException.class)
	public ResponseEntity<?> handleNoItemsFoundException(NoItemsFoundException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(ItemNotFoundException.class)
	public ResponseEntity<?> handleItemNotFoundException(ItemNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
