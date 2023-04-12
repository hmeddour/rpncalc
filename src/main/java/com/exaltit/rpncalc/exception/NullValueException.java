package com.exaltit.rpncalc.exception;

public class NullValueException extends RuntimeException {

    private String message;

    public NullValueException(String message) {
        super(message);
    }
}
