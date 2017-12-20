package com.example.dec.mylocation.rximpl;


import com.example.dec.mylocation.pojo.AllVisitDetailsPojo;
import com.example.dec.mylocation.pojo.ContactPersonPojo;

import rx.Observer;

/**
 * Created by pritesh on 3/27/2017.
 */

public class ContactDetailsPresenter extends BasePresentre implements Observer<ContactPersonPojo> {


    private ContactDetailInterface visitInterface;

    public ContactDetailsPresenter(ContactDetailInterface visitInterface) {
        this.visitInterface = visitInterface;
    }

    @Override
    public void onCompleted() {


        visitInterface.onCLoginComplete();
    }

    @Override
    public void onError(Throwable e) {

        visitInterface.onCLoginError(e.getMessage());

    }

    @Override
    public void onNext(ContactPersonPojo detailsPojo) {

        visitInterface.onCLogin(detailsPojo);

    }


    public void getData(){

        subscribe(visitInterface.getAllContact(),ContactDetailsPresenter.this);

    }


}
