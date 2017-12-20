package com.example.dec.mylocation.rximpl;

import com.example.dec.mylocation.pojo.LocationPojo;

import rx.Observer;

/**
 * Created by pritesh on 3/27/2017.
 */

public class VisitPresenter extends BasePresentre implements Observer<LocationPojo> {


    private VisitInterface visitInterface;

    public VisitPresenter(VisitInterface visitInterface) {
        this.visitInterface = visitInterface;
    }

    @Override
    public void onCompleted() {


        visitInterface.onLoginComplete();
    }

    @Override
    public void onError(Throwable e) {

        visitInterface.onLoginError(e.getMessage());

    }

    @Override
    public void onNext(LocationPojo locationPojo) {

        visitInterface.onLogin(locationPojo);

    }


    public void getData(String id,String name,String addr,String state,String city,String region,String pincode,String officetel,String desc,String email,String date,String time){

        subscribe(visitInterface.getConf(id,name,addr,state,city,region,pincode,officetel,email,desc,date,time),VisitPresenter.this);

    }


}
