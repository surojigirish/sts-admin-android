package com.example.sts_admin.model;

import com.google.gson.annotations.SerializedName;

public class PassengerDetail {

    @SerializedName("address")
    private String address;

    @SerializedName("category")
    private String category;

    @SerializedName("contact")
    private String contact;

    @SerializedName("dob")
    private String dob;

    @SerializedName("first-name")
    private  String firstName;

    @SerializedName("gender")
    private String gender;

    @SerializedName("id")
    private Integer id;

    @SerializedName("last-name")
    private String lastName;

    @SerializedName("photo")
    private  String photo;

    public PassengerDetail(String address, String category, String contact, String dob, String firstName, String gender, Integer id, String lastName, String photo) {
        this.address = address;
        this.category = category;
        this.contact = contact;
        this.dob = dob;
        this.firstName = firstName;
        this.gender = gender;
        this.id = id;
        this.lastName = lastName;
        this.photo = photo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
