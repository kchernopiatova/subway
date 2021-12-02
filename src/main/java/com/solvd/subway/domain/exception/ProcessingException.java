package com.solvd.subway.domain.exception;

public class ProcessingException extends RuntimeException{

    public ProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
