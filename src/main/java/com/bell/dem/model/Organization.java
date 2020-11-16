package com.bell.dem.model;

import javax.persistence.*;

/**
 * entity Organization
 */
@Entity
@Table(name = "Organization")
public class Organization {
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
     *  Имя
     */
    @Column(name = "name", length = 20, nullable = false)
    private String name;

    /**
     *  Полное имя
     */
    @Column(name = "full_name", length = 50, nullable = false)
    private String fullName;

    /**
     *  ИНН
     */
    @Column(name = "inn", length = 10, nullable = false)
    private String inn;

    /**
     *  КПП
     */
    @Column(name = "kpp", length = 9, nullable = false)
    private String kpp;

    /**
     *  Адрес
     */
    @Column(name = "address", length = 50, nullable = false)
    private String address;

    /**
     *  Телефон
     */
    @Column(name = "phone", length = 11)
    private String phone;

    /**
     *  Активность
     */
    @Column(name = "is_active")
    private Boolean isActive;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
