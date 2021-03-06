package com.bell.dem.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.persistence.Column;

/**
 * entity Country
 */
@Entity
@Table(name = "Country")
public class Country {
    /**
     * Код
     */
    @Id
    @Column(name = "code", nullable = false, unique = true)
    private Integer code;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Страна
     */
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
