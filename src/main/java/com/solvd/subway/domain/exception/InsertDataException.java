package com.solvd.subway.domain.exception;

public class InsertDataException extends RuntimeException {

    public InsertDataException(String message) {
        super(message);
    }

    public InsertDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
