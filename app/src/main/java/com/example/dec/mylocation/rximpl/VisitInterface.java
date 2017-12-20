package com.example.dec.mylocation.rximpl;

import com.example.dec.mylocation.pojo.LocationPojo;

import rx.Observable;

/**
 * Created by pritesh on 4/11/2017.
 */

public interface VisitInterface {


    void onLoginComplete();
    void onLoginError(String s);
    void onLogin(LocationPojo locationPojo);
    Observable<LocationPojo> getConf(String id,String name,String addr,String state,String city,String region,String pincode,String officetel,String email,String desc,String date,String time);


}
