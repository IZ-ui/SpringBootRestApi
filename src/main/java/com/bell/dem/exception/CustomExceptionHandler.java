package com.bell.dem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * CustomExceptionHandler для отлавливания custom exception
 */
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = NotFoundEntityException.class)
    public ResponseEntity<Object> exception(NotFoundEntityException exception) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = IncorrectInputParameterException.class)
    public ResponseEntity<Object> exception(IncorrectInputParameterException exception) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
