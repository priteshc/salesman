package com.example.dec.mylocation.pojo;

/**
 * Created by pritesh on 8/4/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Aqulogin {

    @SerializedName("Login_Id")
    @Expose
    private String loginId;
    @SerializedName("Login_Name")
    @Expose
    private String loginName;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("Emp_Name")
    @Expose
    private String empName;
    @SerializedName("Emp_Mobile")
    @Expose
    private String empMobile;
    @SerializedName("Emp_EmailId")
    @Expose
    private String empEmailId;
    @SerializedName("Emp_Active")
    @Expose
    private String empActive;
    @SerializedName("Rep_MgrId")
    @Expose
    private String repMgrId;
    @SerializedName("Region")
    @Expose
    private String region;
    @SerializedName("Product_Category")
    @Expose
    private String productCategory;
    @SerializedName("Expense_Category")
    @Expose
    private String expenseCategory;
    @SerializedName("User_Token")
    @Expose
    private String userToken;
    @SerializedName("Magr_Token")
    @Expose
    private String magrToken;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpMobile() {
        return empMobile;
    }

    public void setEmpMobile(String empMobile) {
        this.empMobile = empMobile;
    }

    public String getEmpEmailId() {
        return empEmailId;
    }

    public void setEmpEmailId(String empEmailId) {
        this.empEmailId = empEmailId;
    }

    public String getEmpActive() {
        return empActive;
    }

    public void setEmpActive(String empActive) {
        this.empActive = empActive;
    }

    public String getRepMgrId() {
        return repMgrId;
    }

    public void setRepMgrId(String repMgrId) {
        this.repMgrId = repMgrId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(String expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getMagrToken() {
        return magrToken;
    }

    public void setMagrToken(String magrToken) {
        this.magrToken = magrToken;
    }
}