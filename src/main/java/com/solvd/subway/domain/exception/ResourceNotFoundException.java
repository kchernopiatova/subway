package com.solvd.subway.domain.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
