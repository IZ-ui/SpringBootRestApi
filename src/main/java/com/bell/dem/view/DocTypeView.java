package com.bell.dem.view;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO для DocType
 */
public class DocTypeView {

    /**
     * Документ
     */
    @Size(max = 50)
    @NotEmpty(message = "Название не может быть пустым")
    private String name;

    /**
     * Код документа
     */
    @NotNull(message = "Код не может быть пустым")
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
