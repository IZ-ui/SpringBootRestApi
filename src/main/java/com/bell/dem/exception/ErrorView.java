package com.bell.dem.exception;

/**
 * DTO для оборачивания ошибок
 */
public class ErrorView {

    /**
     * Поле для оборачивания ответа
     */
    private final String error;

    public ErrorView(String error) {
        this.error = "Ошибка сервера " + error;
    }

    public String getError() {
        return error;
    }
}
