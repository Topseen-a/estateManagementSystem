package com.estate.exceptions;

public class GatePassDoesNotExistException extends RuntimeException {

    public GatePassDoesNotExistException(String message) {
        super(message);
    }
}
