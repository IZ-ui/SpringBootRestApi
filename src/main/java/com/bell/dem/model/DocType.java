package com.bell.dem.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.persistence.Column;

/**
 * entity Doc
 */
@Entity
@Table(name = "Doc_type")
public class DocType {
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
     * Документ
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
