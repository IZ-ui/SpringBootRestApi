package com.bell.dem.view;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @NotNull(groups = {UserInView.Update.class}, message = "id не должен быть пустым")
    private Integer id;

    /**
     * officeId
     */
    @NotNull(groups = {UserInView.List.class, UserInView.Save.class},
            message = "ID офиса не должен быть пустым")
    private Integer officeId;

    /**
     * firstName
     */
    @NotNull(groups = {UserInView.Update.class, UserInView.Save.class},
            message = "Имя не должно быть пустым")
    @Size(max = 30, message = "Длина имени не более 30 символов")
    private String firstName;

    /**
     * secondName
     */
    @Size(max = 30, message = "Длина фамилии не более 30 символов")
    private String secondName;

    /**
     * middleName
     */
    @Size(max = 30, message = "Длина отчества не более 30 символов")
    private String middleName;

    /**
     * position
     */
    @NotNull(groups = {UserInView.Update.class, UserInView.Save.class},
            message = "Должность не должна быть пустой")
    @Size(max = 20, message = "Длина должности не более 20 символов")
    private String position;

    /**
     * phone
     */
    @Size(max = 11, message = "Длина телефона не более 11 цифр")
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
    private Boolean isIdentified;

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

    public Boolean getIsIdentified() {
        return isIdentified;
    }

    public void setIsIdentified(Boolean isIdentified) {
        this.isIdentified = isIdentified;
    }
}
