package com.example.dec.mylocation.rximpl;

import com.example.dec.mylocation.pojo.LocationPojo;

import rx.Observer;

/**
 * Created by pritesh on 3/27/2017.
 */

public class TestPresenter extends BasePresentre implements Observer<LocationPojo> {


    private TestInterface testInterface;

    public TestPresenter(TestInterface testInterface) {
        this.testInterface = testInterface;
    }

    @Override
    public void onCompleted() {


        testInterface.onLoginComplete();
    }

    @Override
    public void onError(Throwable e) {

        testInterface.onLoginError(e.getMessage());

    }

    @Override
    public void onNext(LocationPojo locationPojo) {

        testInterface.onLogin(locationPojo);

    }


    public void getData(String name, Double lat, Double lng, String date, String login, String time){

        subscribe(testInterface.getConf(name,lat,lng,date,login,time),TestPresenter.this);

    }


}
