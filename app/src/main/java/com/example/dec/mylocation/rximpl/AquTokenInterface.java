package com.example.dec.mylocation.rximpl;

import com.example.dec.mylocation.pojo.AquLoginPojo;
import com.example.dec.mylocation.pojo.LocationPojo;

import rx.Observable;

/**
 * Created by pritesh on 4/11/2017.
 */

public interface AquTokenInterface {


    void onTLoginComplete();
    void onTLoginError(String s);
    void onTLogin(AquLoginPojo locationPojo);
    Observable<AquLoginPojo> getTdetail(String id, String token);


}
