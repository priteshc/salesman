package com.example.dec.mylocation.pojo;

/**
 * Created by pritesh on 8/5/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Expense {

    @SerializedName("Exp_Id")
    @Expose
    private String expId;
    @SerializedName("Login_Id")
    @Expose
    private String loginId;
    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("Company")
    @Expose
    private String company;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Drive_Km")
    @Expose
    private String driveKm;
    @SerializedName("Amount")
    @Expose
    private String amount;

    public String getExpId() {
        return expId;
    }

    public void setExpId(String expId) {
        this.expId = expId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDriveKm() {
        return driveKm;
    }

    public void setDriveKm(String driveKm) {
        this.driveKm = driveKm;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}