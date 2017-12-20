package com.example.dec.mylocation.rximpl;

import com.example.dec.mylocation.pojo.AquLoginPojo;
import com.example.dec.mylocation.pojo.LocationPojo;

import rx.Observable;

/**
 * Created by pritesh on 4/11/2017.
 */

public interface LoginInterface {


    void onLoginComplete();
    void onLoginError(String s);
    void onLogin(AquLoginPojo aquLoginPojo);
    Observable<AquLoginPojo> getLogin(String email,String password);


}
