package com.bell.dem.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * CustomExceptionHandler для отлавливания custom exception
 */
@RestControllerAdvice
public class CustomExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);
    private static final String INCORRECT_INPUT_PARAMETER_ERROR_CODE = "422";
    private static final String ENTITY_NOT_FOUND_ERROR_CODE = "404";
    private static final String OPERATION_DENIED_ERROR_CODE = "403";

    /**
     * Обработка ошибок валидации входных параметров от пользователя
     */
    @ExceptionHandler(CustomValidationException.class)
    public ErrorView handleRequestDataValidationException(CustomValidationException exception) {
        log.error("Incorrect input parameter, code:" + INCORRECT_INPUT_PARAMETER_ERROR_CODE, exception);
        return new ErrorView(INCORRECT_INPUT_PARAMETER_ERROR_CODE);
    }

    @ExceptionHandler(value = NotFoundEntityException.class)
    public ErrorView exception(NotFoundEntityException exception) {
        log.error("Entity not found, code:" + ENTITY_NOT_FOUND_ERROR_CODE, exception);
        return new ErrorView(ENTITY_NOT_FOUND_ERROR_CODE);
    }

    @ExceptionHandler(value = IncorrectInputParameterException.class)
    public ErrorView exception(IncorrectInputParameterException exception) {
        log.error("Can't add this entity, code:" + OPERATION_DENIED_ERROR_CODE, exception);
        return new ErrorView(OPERATION_DENIED_ERROR_CODE);
    }
}
