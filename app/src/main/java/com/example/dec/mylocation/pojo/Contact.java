package com.example.dec.mylocation.pojo;

/**
 * Created by pritesh on 5/31/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contact {

    @SerializedName("Cont_Id")
    @Expose
    private String contId;
    @SerializedName("Cust_Id")
    @Expose
    private String custId;
    @SerializedName("Person_Name")
    @Expose
    private String personName;
    @SerializedName("Designation")
    @Expose
    private String designation;
    @SerializedName("MobileNo1")
    @Expose
    private String mobileNo1;
    @SerializedName("MobileNo2")
    @Expose
    private String mobileNo2;
    @SerializedName("Email_Id")
    @Expose
    private String emailId;

    public String getContId() {
        return contId;
    }

    public void setContId(String contId) {
        this.contId = contId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getMobileNo1() {
        return mobileNo1;
    }

    public void setMobileNo1(String mobileNo1) {
        this.mobileNo1 = mobileNo1;
    }

    public String getMobileNo2() {
        return mobileNo2;
    }

    public void setMobileNo2(String mobileNo2) {
        this.mobileNo2 = mobileNo2;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}