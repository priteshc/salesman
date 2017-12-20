package com.example.dec.mylocation.rximpl;

import com.example.dec.mylocation.pojo.LocationPojo;

import rx.Observable;

/**
 * Created by pritesh on 4/11/2017.
 */

public interface TestInterface {


    void onLoginComplete();
    void onLoginError(String s);
    void onLogin(LocationPojo locationPojo);
    Observable<LocationPojo> getConf(String name,Double lat,Double lng,String date,String login,String time);


}
