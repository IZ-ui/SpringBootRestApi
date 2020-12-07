package com.bell.dem.view;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO для метода Organization
 */
public class OrganizationView {

    /**
     * интерфейс-маркер для валидации OrganizationView в мапинге /list
     */
    public interface List {
    }

    /**
     * интерфейс-маркер для валидации OrganizationView в мапинге /update
     */
    public interface Update {
    }

    /**
     * интерфейс-маркер для валидации OrganizationView в мапинге /save
     */
    public interface Save {
    }

    /**
     * id
     */
    @NotNull(groups = {Update.class}, message = "id не должен быть пустым")
    private Integer id;

    /**
     * name
     */
    @NotEmpty(groups = {List.class, Update.class, Save.class},
            message = "Название не должно быть пустым")
    @Size(max = 20, message = "Длина имени не более 20 символов")
    private String name;

    /**
     * fullName
     */
    @NotEmpty(groups = {Update.class, Save.class},
            message = "Полное название не должно быть пустым")
    @Size(max = 50, message = "Длина полного имени не более 50 символов")
    private String fullName;

    /**
     * inn
     */
    @NotEmpty(groups = {Update.class, Save.class}, message = "ИНН не должно быть пустым")
    @Size(min = 10, max = 10, groups = {Update.class, Save.class},
            message = "Длина ИНН ровно 10 цифр")
    private String inn;

    /**
     * kpp
     */
    @NotEmpty(groups = {Update.class, Save.class}, message = "КПП не должно быть пустым")
    @Size(min = 9, max = 9, groups = {Update.class, Save.class},
            message = "Длина КПП ровно 9 цифр")
    private String kpp;

    /**
     * address
     */
    @NotEmpty(groups = {Update.class, Save.class}, message = "Адрес должен быть заполнен")
    @Size(max = 50, message = "Длина адреса не более 50 символов")
    private String address;

    /**
     * phone
     */
    @Size(max = 11, message = "Длина телефона не более 11 цифр")
    private String phone;

    /**
     * isActive
     */
    private Boolean isActive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}
