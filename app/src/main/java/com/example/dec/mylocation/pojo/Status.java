package com.example.dec.mylocation.pojo;

/**
 * Created by pritesh on 8/1/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Status {

    @SerializedName("Status_Id")
    @Expose
    private String statusId;
    @SerializedName("Name")
    @Expose
    private String name;

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}