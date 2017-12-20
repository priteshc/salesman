package com.example.dec.mylocation.pojo;

/**
 * Created by pritesh on 8/2/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("Product_Id")
    @Expose
    private String productId;
    @SerializedName("Product_Name")
    @Expose
    private String productName;
    @SerializedName("Product_Category")
    @Expose
    private String productCategory;

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

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

}