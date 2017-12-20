package com.example.dec.mylocation.rximpl;

import com.example.dec.mylocation.pojo.LocationPojo;

import rx.Observable;

/**
 * Created by pritesh on 4/11/2017.
 */

public interface ExpenseInterface {


    void onELoginComp();
    void onELoginErr(String s);
    void onELogin(LocationPojo locationPojo);
    Observable<LocationPojo> getExpense(String id, String date, String company, String description, String km, String amount);


}
