package com.example.dec.mylocation.rximpl;

import com.example.dec.mylocation.pojo.AquLoginPojo;
import com.example.dec.mylocation.pojo.LocationPojo;

import rx.Observer;

/**
 * Created by pritesh on 3/27/2017.
 */

public class AquTokenPresenter extends BasePresentre implements Observer<AquLoginPojo> {


    private AquTokenInterface visitInterface;

    public AquTokenPresenter(AquTokenInterface visitInterface) {
        this.visitInterface = visitInterface;
    }

    @Override
    public void onCompleted() {


        visitInterface.onTLoginComplete();
    }

    @Override
    public void onError(Throwable e) {

        visitInterface.onTLoginError(e.getMessage());

    }

    @Override
    public void onNext(AquLoginPojo locationPojo) {

        visitInterface.onTLogin(locationPojo);

    }


    public void getData(String id,String token){

        subscribe(visitInterface.getTdetail(id,token),AquTokenPresenter.this);

    }


}
