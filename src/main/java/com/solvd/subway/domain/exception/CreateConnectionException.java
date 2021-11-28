package com.solvd.subway.domain.exception;

public class CreateConnectionException extends RuntimeException{
    public CreateConnectionException(String message) {
        super(message);
    }

    public CreateConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
