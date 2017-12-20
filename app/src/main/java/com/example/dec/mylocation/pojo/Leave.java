package com.example.dec.mylocation.pojo;

/**
 * Created by pritesh on 8/10/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Leave {

    @SerializedName("Req_Id")
    @Expose
    private String reqId;
    @SerializedName("Req_Date")
    @Expose
    private String reqDate;
    @SerializedName("Login_Id")
    @Expose
    private String loginId;
    @SerializedName("Leave_Type")
    @Expose
    private String leaveType;
    @SerializedName("Leave_From")
    @Expose
    private String leaveFrom;
    @SerializedName("Leave_Upto")
    @Expose
    private String leaveUpto;
    @SerializedName("No_Of_Days")
    @Expose
    private String noOfDays;
    @SerializedName("Leave_Resone")
    @Expose
    private String leaveResone;
    @SerializedName("Reptmgr_Id")
    @Expose
    private String reptmgrId;
    @SerializedName("User_Token")
    @Expose
    private String userToken;
    @SerializedName("Mangr_Token")
    @Expose
    private String mangrToken;
    @SerializedName("Status_Type")
    @Expose
    private String statusType;
    @SerializedName("Remark")
    @Expose
    private String remark;

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getReqDate() {
        return reqDate;
    }

    public void setReqDate(String reqDate) {
        this.reqDate = reqDate;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getLeaveFrom() {
        return leaveFrom;
    }

    public void setLeaveFrom(String leaveFrom) {
        this.leaveFrom = leaveFrom;
    }

    public String getLeaveUpto() {
        return leaveUpto;
    }

    public void setLeaveUpto(String leaveUpto) {
        this.leaveUpto = leaveUpto;
    }

    public String getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(String noOfDays) {
        this.noOfDays = noOfDays;
    }

    public String getLeaveResone() {
        return leaveResone;
    }

    public void setLeaveResone(String leaveResone) {
        this.leaveResone = leaveResone;
    }

    public String getReptmgrId() {
        return reptmgrId;
    }

    public void setReptmgrId(String reptmgrId) {
        this.reptmgrId = reptmgrId;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getMangrToken() {
        return mangrToken;
    }

    public void setMangrToken(String mangrToken) {
        this.mangrToken = mangrToken;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}