package com.example.dec.mylocation.pojo;

/**
 * Created by pritesh on 5/26/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllData {

    @SerializedName("Visit_Id")
    @Expose
    private String visitId;
    @SerializedName("Visit_Time")
    @Expose
    private String visitTime;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Contact_Name")
    @Expose
    private String contactName;
    @SerializedName("Remarks")
    @Expose
    private String remarks;
    @SerializedName("Followup_Date")
    @Expose
    private String followupDate;
    @SerializedName("Followup_Time")
    @Expose
    private String followupTime;
    @SerializedName("Login_Name")
    @Expose
    private String loginName;
    @SerializedName("Login_Id")
    @Expose
    private String loginId;
    @SerializedName("Purpose_Id")
    @Expose
    private String purposeId;
    @SerializedName("Purpose")
    @Expose
    private String purpose;
    @SerializedName("Status_Id")
    @Expose
    private String statusId;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Product_Id")
    @Expose
    private String productId;
    @SerializedName("Product_Name")
    @Expose
    private String productName;
    @SerializedName("Cust_Id")
    @Expose
    private String custId;
    @SerializedName("Cust_Name")
    @Expose
    private String custName;

    @SerializedName("Visit_Date")
    @Expose
    private String visitDate;



    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }



    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getFollowupDate() {
        return followupDate;
    }

    public void setFollowupDate(String followupDate) {
        this.followupDate = followupDate;
    }

    public String getFollowupTime() {
        return followupTime;
    }

    public void setFollowupTime(String followupTime) {
        this.followupTime = followupTime;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPurposeId() {
        return purposeId;
    }

    public void setPurposeId(String purposeId) {
        this.purposeId = purposeId;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

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
}