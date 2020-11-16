package com.bell.dem.model;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * entity Document
 */
@Entity
@Table(name = "Document")
public class Document {
    /**
     * Уникальный идентификатор
     */
    @Id
    private Integer id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Идентификатор справочника документа
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_id", nullable = false)
    private Doc doc;

    /**
     * Номер документа
     */
    @Column(name = "doc_number", length = 20, nullable = false)
    private String number;

    /**
     * Дата документа
     */
    @Column(name = "doc_date", nullable = false)
    private LocalDate date;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Doc getDoc() {
        return doc;
    }

    public void setDoc(Doc doc) {
        this.doc = doc;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
