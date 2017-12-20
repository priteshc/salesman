package com.example.dec.mylocation.rximpl;


import com.example.dec.mylocation.pojo.VisitDetailsPojo;

import rx.Observer;

/**
 * Created by pritesh on 3/27/2017.
 */

public class VisitDetailsPresenter extends BasePresentre implements Observer<VisitDetailsPojo> {


    private VisitDetailInterface visitInterface;

    public VisitDetailsPresenter(VisitDetailInterface visitInterface) {
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
    public void onNext(VisitDetailsPojo detailsPojo) {

        visitInterface.onLogin(detailsPojo);

    }


    public void getData(String reg){

        subscribe(visitInterface.getVisitData(reg),VisitDetailsPresenter.this);

    }


}
