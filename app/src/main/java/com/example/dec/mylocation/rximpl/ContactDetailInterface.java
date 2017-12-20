package com.example.dec.mylocation.rximpl;

import com.example.dec.mylocation.pojo.AllVisitDetailsPojo;
import com.example.dec.mylocation.pojo.ContactPersonPojo;

import rx.Observable;

/**
 * Created by pritesh on 4/11/2017.
 */

public interface ContactDetailInterface {


    void onCLoginComplete();
    void onCLoginError(String s);
    void onCLogin(ContactPersonPojo contactPersonPojo);
    Observable<ContactPersonPojo> getAllContact();


}
