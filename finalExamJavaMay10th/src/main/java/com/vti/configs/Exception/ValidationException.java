package com.vti.configs.Exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@ControllerAdvice
public class ValidationException {
    // đối tượng kh hợp lệ  T
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(objectError -> {
            String key = ((FieldError) objectError).getField();
            String message = objectError.getDefaultMessage();
            errors.put(key, message);
        });
        return ResponseEntity.badRequest().body(errors);
    }

    // vi phạm ràng buộc
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> validationException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()){
            String key = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            errors.put(key, message);
        }
        return ResponseEntity.badRequest().body(errors);
    }

}
