package com.logify.api.exception;

public class SessionAlreadyActiveException extends Throwable {
    private String message;
    public SessionAlreadyActiveException(String message) {
        super(message);
        this.message = message;
    }
}
