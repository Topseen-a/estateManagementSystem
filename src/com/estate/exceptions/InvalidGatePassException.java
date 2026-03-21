package com.estate.exceptions;

public class InvalidGatePassException extends RuntimeException {

    public InvalidGatePassException(String message) {
        super(message);
    }
}
