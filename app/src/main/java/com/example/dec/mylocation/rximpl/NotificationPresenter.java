package com.example.dec.mylocation.rximpl;

import com.example.dec.mylocation.pojo.LocationPojo;
import com.example.dec.mylocation.pojo.NotificationPojo;

import rx.Observer;

/**
 * Created by pritesh on 3/27/2017.
 */

public class NotificationPresenter extends BasePresentre implements Observer<NotificationPojo> {


    private NotificationInterface visitInterface;

    public NotificationPresenter(NotificationInterface visitInterface) {
        this.visitInterface = visitInterface;
    }

    @Override
    public void onCompleted() {


        visitInterface.onNLoginComplete();
    }

    @Override
    public void onError(Throwable e) {

        visitInterface.onNLoginError(e.getMessage());

    }

    @Override
    public void onNext(NotificationPojo locationPojo) {

        visitInterface.onNLogin(locationPojo);

    }


    public void getData(String desc,String token,String id,String utoken,String name,String type,String uid){

        subscribe(visitInterface.getConf(desc,token,id,utoken,name,type,uid),NotificationPresenter.this);

    }


}
