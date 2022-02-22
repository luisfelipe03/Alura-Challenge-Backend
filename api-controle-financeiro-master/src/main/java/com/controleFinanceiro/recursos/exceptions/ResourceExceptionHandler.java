package com.controleFinanceiro.recursos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.controleFinanceiro.servicos.exceptions.NegocionException;
import com.controleFinanceiro.servicos.exceptions.NoContentException;
import com.controleFinanceiro.servicos.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e){
		
		ValidationError validationError = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Request inválido.");
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
	}
	
	@ExceptionHandler(NegocionException.class)
	public ResponseEntity<ValidationError> validation(NegocionException e){
		
		ValidationError validationError = new ValidationError(HttpStatus.BAD_REQUEST.value(), e.getMensagem());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
	}
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<ValidationError> objectNotFound(ObjectNotFoundException e){
		ValidationError validationError = new ValidationError(HttpStatus.NOT_FOUND.value(), e.getMensagem());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(validationError);
	}
	
	@ExceptionHandler(NoContentException.class)
	public ResponseEntity<HttpStatus> noContentException(NoContentException e){
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
