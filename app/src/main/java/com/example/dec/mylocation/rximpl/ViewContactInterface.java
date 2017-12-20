package com.example.dec.mylocation.rximpl;

import com.example.dec.mylocation.pojo.ContactPojo;
import com.example.dec.mylocation.pojo.LocationPojo;

import rx.Observable;

/**
 * Created by pritesh on 4/11/2017.
 */

public interface ViewContactInterface {


    void onVLoginComplete();
    void onVLoginError(String s);
    void onVLogin(ContactPojo contactPojo);
    Observable<ContactPojo> getAllCont(String id);


}
