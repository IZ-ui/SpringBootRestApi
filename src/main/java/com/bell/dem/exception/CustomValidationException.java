package com.bell.dem.exception;

/**
 * Custom exception для валидации входных параметров в запросах
 */
public class CustomValidationException extends RuntimeException {

    private final String message;

    public CustomValidationException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
