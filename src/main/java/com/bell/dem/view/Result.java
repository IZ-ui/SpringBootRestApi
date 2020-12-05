package com.bell.dem.view;

/**
 * DTO для оборачивания успешного ответа void методов Controller
 */
public class Result {

    /**
     * Поле для оборачивания ответа
     */
    private String result;

    public Result() {
    }

    public Result(String result) {
        this.result = result;
    }

    public Object getResult() {
        return result;
    }
}
