package com.bell.dem.view;

import javax.validation.constraints.NotNull;

/**
 * DTO в качестве входного параметра для методов User
 */
public class UserInView {

    /**
     * интерфейс-маркер для валидации UserInView в мапинге /list
     */
    public interface List {
    }

    /**
     * интерфейс-маркер для валидации UserInView в мапинге /update
     */
    public interface Update {
    }

    /**
     * интерфейс-маркер для валидации UserInView в мапинге /save
     */
    public interface Save {
    }

    /**
     * id
     */
    @NotNull(message = "ID не может быть пустым")
    @NotNull(groups = {UserInView.Update.class}, message = "id не должен быть пустым")
    private Integer id;

    /**
     * officeId
     */
    @NotNull(groups = {UserInView.List.class, UserInView.Update.class, UserInView.Save.class},
            message = "ID офиса не должен быть пустым")
    private Integer officeId;

    /**
     * firstName
     */
    @NotNull(groups = {UserInView.Update.class, UserInView.Save.class},
            message = "Имя не должно быть пустым")
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
    @NotNull(groups = {UserInView.Update.class, UserInView.Save.class},
            message = "Должность не должна быть пустой")
    private String position;

    /**
     * phone
     */
    private String phone;

    /**
     * docCode
     */
    private Integer docCode;

    /**
     * docName
     */
    private String docName;

    /**
     * docNumber
     */
    private String docNumber;

    /**
     * docDate
     */
    private String docDate;

    /**
     * citizenshipCode
     */
    private Integer citizenshipCode;

    /**
     * isIdentified
     */
    private boolean isIdentified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getDocCode() {
        return docCode;
    }

    public void setDocCode(Integer docCode) {
        this.docCode = docCode;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public String getDocDate() {
        return docDate;
    }

    public void setDocDate(String docDate) {
        this.docDate = docDate;
    }

    public Integer getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(Integer citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public boolean getIsIdentified() {
        return isIdentified;
    }

    public void setIsIdentified(boolean isIdentified) {
        this.isIdentified = isIdentified;
    }
}
