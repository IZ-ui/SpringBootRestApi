package com.bell.dem.view;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO в качестве входного параметра для методов Office
 */
public class OfficeInView {

    /**
     * интерфейс-маркер для валидации OfficeInView в мапинге /list
     */
    public interface List {
    }

    /**
     * интерфейс-маркер для валидации OfficeInView в мапинге /update
     */
    public interface Update {
    }

    /**
     * интерфейс-маркер для валидации OfficeInView в мапинге /save
     */
    public interface Save {
    }

    /**
     * id
     */
    @NotNull(groups = {OfficeInView.Update.class}, message = "id не должен быть пустым")
    private Integer id;

    /**
     * orgId
     */
    @NotNull(groups = {OfficeInView.Save.class}, message = "ID организации не может быть пустым")
    private Integer orgId;

    /**
     * name
     */
    @NotEmpty(groups = {OfficeInView.Update.class}, message = "Название не должно быть пустым")
    @Size(max = 50, message = "Длина имени не более 50 символов")
    private String name;

    /**
     * address
     */
    @NotEmpty(groups = {OfficeInView.Update.class}, message = "Адрес должен быть заполнен")
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

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

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
