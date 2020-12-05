package com.bell.dem.view;

/**
 * DTO для метода UserController.getUserById
 */
public class UserOutView {

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

    /**
     * phone
     */
    private String phone;

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
     * citizenshipName
     */
    private String citizenshipName;

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

    public String getCitizenshipName() {
        return citizenshipName;
    }

    public void setCitizenshipName(String citizenshipName) {
        this.citizenshipName = citizenshipName;
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
