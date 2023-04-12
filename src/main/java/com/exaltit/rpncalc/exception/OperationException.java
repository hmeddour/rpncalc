package com.exaltit.rpncalc.exception;

public class OperationException extends RuntimeException {

    private String message;

    public OperationException(String message) {
        super(message);
    }
}
