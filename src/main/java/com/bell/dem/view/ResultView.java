package com.bell.dem.view;

/**
 * DTO для оборачивания успешного ответа void методов Controller
 */
public class ResultView {

    /**
     * Поле для оборачивания ответа
     */
    private String result;

    public ResultView() {
    }

    public ResultView(String result) {
        this.result = result;
    }

    public Object getResult() {
        return result;
    }
}
