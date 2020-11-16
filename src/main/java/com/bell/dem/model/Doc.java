package com.bell.dem.model;

import javax.persistence.*;

/**
 * entity Doc
 */
@Entity
@Table(name = "Doc")
public class Doc {
    /**
     * Уникальный идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Код
     */
    @Column(name = "code", nullable = false)
    private Integer code;

    /**
     * Документ
     */
    @Column(name = "name", length = 50, nullable = false)
    private String name;
}
