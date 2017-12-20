package com.example.dec.mylocation.pojo;

/**
 * Created by pritesh on 5/24/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Visit {

    @SerializedName("Cust_Id")
    @Expose
    private String custId;
    @SerializedName("Cust_Name")
    @Expose
    private String custName;
    @SerializedName("Cust_Add")
    @Expose
    private String custAdd;
    @SerializedName("Cust_State")
    @Expose
    private String custState;
    @SerializedName("Cust_City")
    @Expose
    private String custCity;
    @SerializedName("PinCode")
    @Expose
    private String pinCode;
    @SerializedName("Off_Tel")
    @Expose
    private String offTel;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Region")
    @Expose
    private String region;
    @SerializedName("Visit_Date")
    @Expose
    private String visitDate;
    @SerializedName("Visit_Time")
    @Expose
    private String visitTime;

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustAdd() {
        return custAdd;
    }

    public void setCustAdd(String custAdd) {
        this.custAdd = custAdd;
    }

    public String getCustState() {
        return custState;
    }

    public void setCustState(String custState) {
        this.custState = custState;
    }

    public String getCustCity() {
        return custCity;
    }

    public void setCustCity(String custCity) {
        this.custCity = custCity;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getOffTel() {
        return offTel;
    }

    public void setOffTel(String offTel) {
        this.offTel = offTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }
}