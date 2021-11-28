package com.solvd.subway.domain.exception;

public class SelectDataException extends RuntimeException{
    public SelectDataException(String message) {
        super(message);
    }

    public SelectDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
