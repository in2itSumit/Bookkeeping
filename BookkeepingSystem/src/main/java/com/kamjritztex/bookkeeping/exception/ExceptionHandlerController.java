package com.kamjritztex.bookkeeping.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<?> exception(CustomerNotFoundException exception, WebRequest request) {

		ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
				HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage(), request.getDescription(false));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

	}

	@ExceptionHandler(DepartmentNotFoundException.class)
	public ResponseEntity<?> requiredFieldException(DepartmentNotFoundException exception, WebRequest request) {

		ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
				HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage(), request.getDescription(false));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

	}

	@ExceptionHandler(ContactNotFoundException.class)
	public ResponseEntity<?> validationException(ContactNotFoundException exception, WebRequest request) {

		ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
				HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage(), request.getDescription(false));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

	}

	@ExceptionHandler(ProjectNotFoundException.class)
	public ResponseEntity<?> emptyDataException(ProjectNotFoundException exception, WebRequest request) {

		ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
				HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage(), request.getDescription(false));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

	}

	@ExceptionHandler(RoleNotFoundException.class)
	public ResponseEntity<Object> validDateException(RoleNotFoundException exception, WebRequest request) {

		ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
				HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage(), request.getDescription(false));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

	}
		
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<Object> validDateException(UsernameNotFoundException exception, WebRequest request) {
		
		ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
				HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage(), request.getDescription(false));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		
	}
	
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> validDateException(IllegalArgumentException exception, WebRequest request) {
		
		ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
				HttpStatus.NO_CONTENT.getReasonPhrase(), exception.getMessage(), request.getDescription(false));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		
	}
}
