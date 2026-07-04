package com.abhishek.employeeservice.exception;

import com.abhishek.employeeservice.Service.EmployeeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> emailAlreadyExistsException(EmailAlreadyExistsException e) {
        log.info(e.getMessage());
        Map<String, String> error = new HashMap<>();
        error.put("Message", e.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler({EmployeeNotFoundException.class})
    public ResponseEntity<Map<String, String>> employeeNotFoundException(EmployeeNotFoundException e) {
        log.info(e.getMessage());
        Map<String, String> map = new HashMap<>();
        map.put("Message", e.getMessage());
        return ResponseEntity.badRequest().body(map);
    }
}