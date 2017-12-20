package com.example.dec.mylocation.rximpl;

import com.example.dec.mylocation.pojo.LocationPojo;

import rx.Observer;

/**
 * Created by pritesh on 3/27/2017.
 */

public class ContactPresenter extends BasePresentre implements Observer<LocationPojo> {


    private ContactInterface visitInterface;

    public ContactPresenter(ContactInterface visitInterface) {
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
    public void onNext(LocationPojo locationPojo) {

        visitInterface.onCLogin(locationPojo);

    }


    public void getData(String id,String name,String desg,String mobile,String mobile2,String email){

        subscribe(visitInterface.getCont(id,name,desg,mobile,mobile2,email),ContactPresenter.this);

    }


}
