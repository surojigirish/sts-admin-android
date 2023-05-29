package com.example.sts_admin.apiservice.request;

import com.google.gson.annotations.SerializedName;

public class DriverLoginRequest {
    @SerializedName("email")
    private String email;

       @SerializedName("password")
    private  String password;

       @SerializedName("ipaddress")
       private String ipaddress;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }
}
