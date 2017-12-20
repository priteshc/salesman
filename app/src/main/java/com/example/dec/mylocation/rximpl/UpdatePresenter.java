package com.example.dec.mylocation.rximpl;

import com.example.dec.mylocation.pojo.LocationPojo;

import rx.Observer;

/**
 * Created by pritesh on 3/27/2017.
 */

public class UpdatePresenter extends BasePresentre implements Observer<LocationPojo> {


    private UpdateInterface visitInterface;

    public UpdatePresenter(UpdateInterface visitInterface) {
        this.visitInterface = visitInterface;
    }

    @Override
    public void onCompleted() {


        visitInterface.onELoginComp();
    }

    @Override
    public void onError(Throwable e) {

        visitInterface.onELoginErr(e.getMessage());

    }

    @Override
    public void onNext(LocationPojo locationPojo) {

        visitInterface.onELogin(locationPojo);

    }


    public void getData(String id, String status){

        subscribe(visitInterface.getExpense(id,status),UpdatePresenter.this);

    }


}
