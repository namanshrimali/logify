package com.logify.api.exception;

public class DuplicateLogTimeException extends Error {
    public DuplicateLogTimeException(String multipleDataLog) {
        super(multipleDataLog);
    }
}
