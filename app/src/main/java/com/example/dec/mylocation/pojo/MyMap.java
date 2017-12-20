package com.example.dec.mylocation.pojo;

/**
 * Created by pritesh on 6/21/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyMap {

    @SerializedName("LOC_ID")
    @Expose
    private String lOCID;
    @SerializedName("LOC_NAME")
    @Expose
    private String lOCNAME;
    @SerializedName("Latitude")
    @Expose
    private String latitude;
    @SerializedName("Longitude")
    @Expose
    private String longitude;
    @SerializedName("V_Date")
    @Expose
    private String vDate;

    @SerializedName("V_Time")
    @Expose
    private String vTime;



    public String getvTime() {
        return vTime;
    }

    public void setvTime(String vTime) {
        this.vTime = vTime;
    }

    public String getLOCID() {
        return lOCID;
    }

    public void setLOCID(String lOCID) {
        this.lOCID = lOCID;
    }

    public String getLOCNAME() {
        return lOCNAME;
    }

    public void setLOCNAME(String lOCNAME) {
        this.lOCNAME = lOCNAME;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getVDate() {
        return vDate;
    }

    public void setVDate(String vDate) {
        this.vDate = vDate;
    }

}