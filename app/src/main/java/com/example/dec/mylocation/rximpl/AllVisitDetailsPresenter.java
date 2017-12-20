package com.example.dec.mylocation.rximpl;


import com.example.dec.mylocation.pojo.AllVisitDetailsPojo;
import com.example.dec.mylocation.pojo.VisitDetailsPojo;

import rx.Observer;

/**
 * Created by pritesh on 3/27/2017.
 */

public class AllVisitDetailsPresenter extends BasePresentre implements Observer<AllVisitDetailsPojo> {


    private ALlVisitDetailInterface visitInterface;

    public AllVisitDetailsPresenter(ALlVisitDetailInterface visitInterface) {
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
    public void onNext(AllVisitDetailsPojo detailsPojo) {

        visitInterface.onLogin(detailsPojo);

    }


    public void getData(String id){

        subscribe(visitInterface.getAllVisitData(id),AllVisitDetailsPresenter.this);

    }


}
