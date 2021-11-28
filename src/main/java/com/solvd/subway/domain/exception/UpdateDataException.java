package com.solvd.subway.domain.exception;

public class UpdateDataException extends RuntimeException{
    public UpdateDataException(String message) {
        super(message);
    }

    public UpdateDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
