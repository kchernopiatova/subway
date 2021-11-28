package com.solvd.subway.domain.exception;

public class DeleteDataException extends RuntimeException {
    public DeleteDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
