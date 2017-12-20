package com.example.dec.mylocation.rximpl;

import com.example.dec.mylocation.pojo.LocationPojo;

import rx.Observable;

/**
 * Created by pritesh on 4/11/2017.
 */

public interface ContactInterface {


    void onCLoginComplete();
    void onCLoginError(String s);
    void onCLogin(LocationPojo locationPojo);
    Observable<LocationPojo> getCont(String id, String name, String desg, String mobile,String mobile2, String email);


}
