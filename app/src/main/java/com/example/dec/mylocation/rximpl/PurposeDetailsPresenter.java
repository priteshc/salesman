package com.example.dec.mylocation.rximpl;


import com.example.dec.mylocation.pojo.PurposePojo;
import com.example.dec.mylocation.pojo.VisitDetailsPojo;

import rx.Observer;

/**
 * Created by pritesh on 3/27/2017.
 */

public class PurposeDetailsPresenter extends BasePresentre implements Observer<PurposePojo> {


    private PurposeDetailInterface visitInterface;

    public PurposeDetailsPresenter(PurposeDetailInterface visitInterface) {
        this.visitInterface = visitInterface;
    }

    @Override
    public void onCompleted() {


        visitInterface.onPComplete();
    }

    @Override
    public void onError(Throwable e) {

        visitInterface.onPError(e.getMessage());

    }

    @Override
    public void onNext(PurposePojo detailsPojo) {

        visitInterface.onPurpose(detailsPojo);

    }


    public void getData(){

        subscribe(visitInterface.getPurposeData(),PurposeDetailsPresenter.this);

    }


}
