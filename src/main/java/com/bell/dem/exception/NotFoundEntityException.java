package com.bell.dem.exception;

/**
 * Custom exception для сообщения в случае, если entity не найдено
 */
public class NotFoundEntityException extends RuntimeException{
    public NotFoundEntityException(String entity, Integer id) {
        super(String.format("%s with ID %d not found", entity, id));
    }

    public NotFoundEntityException(String entity) {
        super(String.format("%s not found", entity));
    }
}
