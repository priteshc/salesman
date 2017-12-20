package com.example.dec.mylocation.rximpl;

import com.example.dec.mylocation.pojo.ContactPojo;
import com.example.dec.mylocation.pojo.LocationPojo;

import rx.Observer;

/**
 * Created by pritesh on 3/27/2017.
 */

public class ViewContactPresenter extends BasePresentre implements Observer<ContactPojo> {


    private ViewContactInterface visitInterface;

    public ViewContactPresenter(ViewContactInterface visitInterface) {
        this.visitInterface = visitInterface;
    }

    @Override
    public void onCompleted() {


        visitInterface.onVLoginComplete();
    }

    @Override
    public void onError(Throwable e) {

        visitInterface.onVLoginError(e.getMessage());

    }

    @Override
    public void onNext(ContactPojo contactPojo) {

        visitInterface.onVLogin(contactPojo);

    }


    public void getData(String id){

        subscribe(visitInterface.getAllCont(id),ViewContactPresenter.this);

    }


}
