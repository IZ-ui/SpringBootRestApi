package com.bell.dem.view;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO для Country
 */
public class CountryView {

    /**
     * Страна
     */
    @Size(max = 50)
    @NotEmpty(message = "Название страны не должно быть пустым")
    private String name;

    /**
     * Код страны
     */
    @NotNull(message = "Код страны не должен быть пустым")
    private Integer code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
