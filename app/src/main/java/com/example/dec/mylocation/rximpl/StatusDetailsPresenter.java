package com.example.dec.mylocation.rximpl;


import com.example.dec.mylocation.pojo.PurposePojo;
import com.example.dec.mylocation.pojo.Status;
import com.example.dec.mylocation.pojo.StatusPojo;

import rx.Observer;

/**
 * Created by pritesh on 3/27/2017.
 */

public class StatusDetailsPresenter extends BasePresentre implements Observer<StatusPojo> {


    private StatusDetailInterface visitInterface;

    public StatusDetailsPresenter(StatusDetailInterface visitInterface) {
        this.visitInterface = visitInterface;
    }

    @Override
    public void onCompleted() {


        visitInterface.onSComplete();
    }

    @Override
    public void onError(Throwable e) {

        visitInterface.onSError(e.getMessage());

    }

    @Override
    public void onNext(StatusPojo detailsPojo) {

        visitInterface.onStatus(detailsPojo);

    }


    public void getData(){

        subscribe(visitInterface.getStatusData(),StatusDetailsPresenter.this);

    }


}
