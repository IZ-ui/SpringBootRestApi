package com.bell.dem.view;

/**
 * DTO для метода UserController.getUserByFilter
 */
public class UserShortOutView {

    /**
     * id
     */
    private Integer id;

    /**
     * firstName
     */
    private String firstName;

    /**
     * secondName
     */
    private String secondName;

    /**
     * middleName
     */
    private String middleName;

    /**
     * position
     */
    private String position;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
