package com.example.currencyexhange.currency.exception;

public class DataAccessException extends RuntimeException {

    private String message;
    private Throwable cause;

    public DataAccessException(String message, Throwable cause) {
        this.message = message;
        this.cause = cause;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Throwable getCause() {
        return cause;
    }
}
