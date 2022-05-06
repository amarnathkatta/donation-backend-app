package com.wellsfargo.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.wellsfargo.test.dto.ExceptionResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidDataException.class)
	public ResponseEntity<ExceptionResponse> handler(InvalidDataException ex) {
		ExceptionResponse exception = new ExceptionResponse(ex.getMessage(), System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DonarNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handler(DonarNotFoundException ex) {
		ExceptionResponse exception = new ExceptionResponse(ex.getMessage(), System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
	}

}
