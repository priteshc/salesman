package com.example.dec.mylocation.rximpl;

import com.example.dec.mylocation.holder.Visit_Holder;
import com.example.dec.mylocation.pojo.LocationPojo;
import com.example.dec.mylocation.pojo.VisitDetailsPojo;

import rx.Observable;

/**
 * Created by pritesh on 4/11/2017.
 */

public interface VisitDetailInterface {


    void onLoginComplete();
    void onLoginError(String s);
    void onLogin(VisitDetailsPojo visitDetailsPojo);
    Observable<VisitDetailsPojo> getVisitData(String reg);


}
