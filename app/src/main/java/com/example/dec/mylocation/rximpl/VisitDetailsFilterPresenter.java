package com.example.dec.mylocation.rximpl;


import com.example.dec.mylocation.pojo.AllVisitDetailsPojo;
import com.example.dec.mylocation.pojo.VisitDetailsPojo;

import rx.Observer;

/**
 * Created by pritesh on 3/27/2017.
 */

public class VisitDetailsFilterPresenter extends BasePresentre implements Observer<VisitDetailsPojo> {


    private VisitDetailsFilterInterface visitInterface;

    public VisitDetailsFilterPresenter(VisitDetailsFilterInterface visitInterface) {
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
    public void onNext(VisitDetailsPojo detailsPojo) {

        visitInterface.onVLogin(detailsPojo);

    }


    public void getData(String name){

        subscribe(visitInterface.getVisitfilter(name),VisitDetailsFilterPresenter.this);

    }


}
