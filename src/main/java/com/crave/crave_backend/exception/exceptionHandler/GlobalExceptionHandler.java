package com.crave.crave_backend.exception.exceptionHandler;

import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.crave.crave_backend.dto.out.ErrorResponseOutDto;
import com.crave.crave_backend.exception.ContactNumberAlreadyExistsException;
import com.crave.crave_backend.exception.EmailAlreadyExistsException;
import com.crave.crave_backend.exception.PersistenceUnknownException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler({EmailAlreadyExistsException.class, ContactNumberAlreadyExistsException.class})
	public ResponseEntity<ErrorResponseOutDto> handleContactNumberAlreadyExistsException(Exception ex) {		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseOutDto(List.of(ex.getMessage())));
	}
	
	@ExceptionHandler({DataIntegrityViolationException.class, PersistenceUnknownException.class})
	public ResponseEntity<ErrorResponseOutDto> handleDataIntegrityViolationException(Exception ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseOutDto(List.of(ex.getMessage())));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponseOutDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		List<FieldError> allErrors = ex.getFieldErrors();
		List<String> messageList = new ArrayList<>();
		
		for (int i = 0; i < allErrors.size(); i++) {
			FieldError err = allErrors.get(i);
			String message = err.getField() + ": " + err.getDefaultMessage();
			messageList.add(message);
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseOutDto(messageList));
	}
}
