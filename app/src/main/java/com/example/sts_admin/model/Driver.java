package com.example.sts_admin.model;

import com.google.gson.annotations.SerializedName;

public class Driver {

    @SerializedName("contact")
    private String contact;
    @SerializedName("employeeNo")
    private String employeeNo;
    @SerializedName("firstname")
    private String firstname;
    @SerializedName("gender")
    private String gender;
    @SerializedName("lastname")
    private String lastname;
    @SerializedName("licenseNo")
    private String licenseNo;
    @SerializedName("empId")
    private Integer empId;

    public Driver(String contact, String employeeNo, String firstname, String gender, String lastname, String licenseNo, Integer empId) {
        this.contact = contact;
        this.employeeNo = employeeNo;
        this.firstname = firstname;
        this.gender = gender;
        this.lastname = lastname;
        this.licenseNo = licenseNo;
        this.empId = empId;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }
}
