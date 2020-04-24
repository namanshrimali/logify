package com.logify.api.exception;

public class DataNotPresentException extends Error {
    private String message;
    public DataNotPresentException(String message) {
        super(message);
        this.message = message;
    }
}
