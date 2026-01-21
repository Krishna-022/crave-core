package com.crave.crave_backend.exception.exceptionHandler;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.crave.crave_backend.dto.out.ErrorResponseOutDto;
import com.crave.crave_backend.exception.EntityConflictException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(EntityConflictException.class)
	public ResponseEntity<ErrorResponseOutDto> handleEntityConflictException(EntityConflictException entityConflictException) {		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseOutDto(entityConflictException.getMessageList()));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponseOutDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
		List<FieldError> allErrors = methodArgumentNotValidException.getFieldErrors();
		List<String> messageList = new ArrayList<>();
		
		for (int i = 0; i < allErrors.size(); i++) {
			FieldError err = allErrors.get(i);
			String message = err.getField() + ": " + err.getDefaultMessage();
			messageList.add(message);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseOutDto(messageList));
	}
}
