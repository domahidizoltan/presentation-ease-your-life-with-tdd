package com.example.currencyexhange.currency;

public class DataAccessException extends RuntimeException {

    private String message;
    private Throwable cause;

    public DataAccessException(String message, Throwable cause) {
        this.message = message;
        this.cause = cause;
    }

}
