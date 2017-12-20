package com.example.dec.mylocation.rximpl;

import com.example.dec.mylocation.pojo.AllVisitDetailsPojo;
import com.example.dec.mylocation.pojo.VisitDetailsPojo;

import rx.Observable;

/**
 * Created by pritesh on 4/11/2017.
 */

public interface VisitDetailsFilterInterface {


    void onVLoginComplete();
    void onVLoginError(String s);
    void onVLogin(VisitDetailsPojo allVisitDetailsPojo);
    Observable<VisitDetailsPojo> getVisitfilter(String name);


}
