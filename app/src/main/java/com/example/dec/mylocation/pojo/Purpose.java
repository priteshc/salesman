package com.example.dec.mylocation.pojo;

/**
 * Created by pritesh on 8/1/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Purpose {

    @SerializedName("Purpose_Id")
    @Expose
    private String purposeId;
    @SerializedName("Name")
    @Expose
    private String name;

    public String getPurposeId() {
        return purposeId;
    }

    public void setPurposeId(String purposeId) {
        this.purposeId = purposeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}