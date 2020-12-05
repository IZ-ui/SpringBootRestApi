package com.bell.dem.view;

/**
 * DTO для методов getOrganizationByFilter, getOfficeByFilter
 */
public class OrgOffShortView {

    /**
     * id
     */
    private Integer id;

    /**
     * name
     */
    private String name;

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

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
