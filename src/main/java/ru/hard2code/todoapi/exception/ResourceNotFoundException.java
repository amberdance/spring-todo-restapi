package ru.hard2code.todoapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

public class ResourceNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Resource not found";

    public ResourceNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public ResourceNotFoundException(long id) {
        super(String.format("%s with id %d", DEFAULT_MESSAGE, id));
    }


}
