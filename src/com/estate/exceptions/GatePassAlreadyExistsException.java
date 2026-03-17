package com.estate.exceptions;

public class GatePassAlreadyExistsException extends RuntimeException {

    public GatePassAlreadyExistsException(String message) {
        super(message);
    }
}
