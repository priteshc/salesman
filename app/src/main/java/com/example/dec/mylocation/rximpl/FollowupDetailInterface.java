package com.example.dec.mylocation.rximpl;

import com.example.dec.mylocation.pojo.AllVisitDetailsPojo;

import rx.Observable;

/**
 * Created by pritesh on 4/11/2017.
 */

public interface FollowupDetailInterface {


    void onLoginComplete();
    void onLoginError(String s);
    void onLogin(AllVisitDetailsPojo allVisitDetailsPojo);
    Observable<AllVisitDetailsPojo> getAllFollowData(String id,String date);


}
