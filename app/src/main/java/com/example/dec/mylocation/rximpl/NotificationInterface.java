package com.example.dec.mylocation.rximpl;

import com.example.dec.mylocation.pojo.LocationPojo;
import com.example.dec.mylocation.pojo.NotificationPojo;

import rx.Observable;

/**
 * Created by pritesh on 4/11/2017.
 */

public interface NotificationInterface {


    void onNLoginComplete();
    void onNLoginError(String s);
    void onNLogin(NotificationPojo notificationPojo);
    Observable<NotificationPojo> getConf(String desc, String token,String id,String utoken,String name,String type,String uid);


}
