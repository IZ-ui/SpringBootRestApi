package com.bell.dem.view;

/**
 * DTO для ответа
 */
public class DataView {

    /**
     * Поле для оборачивания ответа контроллеров
     */
    private Object data;

    public DataView() {
    }

    public DataView(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
