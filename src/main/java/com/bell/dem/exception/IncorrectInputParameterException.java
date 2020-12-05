package com.bell.dem.exception;

/**
 * Custom exception для валидации входных параметров в запросах
 */
public class IncorrectInputParameterException extends RuntimeException{
    public IncorrectInputParameterException(String param) {
        super(String.format("%s must be not empty", param));
    }
    public IncorrectInputParameterException(String entity, String param) {
        super(String.format("Incorrect %s param for %s", param, entity));
    }
}
