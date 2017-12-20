package com.example.dec.mylocation.rximpl;


import com.example.dec.mylocation.pojo.AllVisitDetailsPojo;

import rx.Observer;

/**
 * Created by pritesh on 3/27/2017.
 */

public class FollowupDetailsPresenter extends BasePresentre implements Observer<AllVisitDetailsPojo> {


    private FollowupDetailInterface visitInterface;

    public FollowupDetailsPresenter(FollowupDetailInterface visitInterface) {
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


    public void getData(String id,String date){

        subscribe(visitInterface.getAllFollowData(id,date),FollowupDetailsPresenter.this);

    }


}
