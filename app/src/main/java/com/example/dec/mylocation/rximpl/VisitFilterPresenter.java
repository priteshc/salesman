package com.example.dec.mylocation.rximpl;


import com.example.dec.mylocation.pojo.AllVisitDetailsPojo;

import rx.Observer;

/**
 * Created by pritesh on 3/27/2017.
 */

public class VisitFilterPresenter extends BasePresentre implements Observer<AllVisitDetailsPojo> {


    private VisitFilterInterface visitInterface;

    public VisitFilterPresenter(VisitFilterInterface visitInterface) {
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
    public void onNext(AllVisitDetailsPojo detailsPojo) {

        visitInterface.onVLogin(detailsPojo);

    }


    public void getData(String date,String cuname){

        subscribe(visitInterface.getVisitfilter(date,cuname),VisitFilterPresenter.this);

    }


}
