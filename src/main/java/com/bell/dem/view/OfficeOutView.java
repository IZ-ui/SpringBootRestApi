package com.bell.dem.view;

/**
 * DTO для метода OfficeController.getOfficeById
 */
public class OfficeOutView {

    /**
     * id
     */
    private Integer id;

    /**
     * name
     */
    private String name;

    /**
     * address
     */
    private String address;

    /**
     * phone
     */
    private String phone;

    /**
     * isActive
     */
    private boolean isActive;

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

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
