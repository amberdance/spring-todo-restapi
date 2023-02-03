package ru.hard2code.todoapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.hard2code.todoapi.exception.ResourceNotFoundException;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(new ErrorInfo(e, HttpStatus.NOT_FOUND).getError(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, Object>> handleBadRequest(Exception e) {
        return new ResponseEntity<>(new ErrorInfo(e, HttpStatus.BAD_REQUEST).getError(), HttpStatus.BAD_REQUEST);
    }


    private static class ErrorInfo {
        private final Map<String, Object> error;

        public ErrorInfo(Throwable e, HttpStatus httpStatus) {
            error = new LinkedHashMap<>();
            error.put("code", httpStatus.value());
            error.put("status", httpStatus.getReasonPhrase());
            error.put("message", e.getMessage());
        }

        public Map<String, Object> getError() {
            return error;
        }
    }
}

