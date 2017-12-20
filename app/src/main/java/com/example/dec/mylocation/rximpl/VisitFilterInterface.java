package com.example.dec.mylocation.rximpl;

import com.example.dec.mylocation.pojo.AllVisitDetailsPojo;

import rx.Observable;

/**
 * Created by pritesh on 4/11/2017.
 */

public interface VisitFilterInterface {


    void onVLoginComplete();
    void onVLoginError(String s);
    void onVLogin(AllVisitDetailsPojo allVisitDetailsPojo);
    Observable<AllVisitDetailsPojo> getVisitfilter(String date,String cuname);


}
