package com.example.dec.mylocation.rximpl;

import com.example.dec.mylocation.pojo.AquLoginPojo;

import rx.Observable;

/**
 * Created by pritesh on 4/11/2017.
 */

public interface MagrInterface {


    void onMLoginComplete();
    void onMLoginError(String s);
    void onMLogin(AquLoginPojo aquLoginPojo);
    Observable<AquLoginPojo> getLogin(String lid);


}
