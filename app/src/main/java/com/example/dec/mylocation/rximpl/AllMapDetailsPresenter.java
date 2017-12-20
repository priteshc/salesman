package com.example.dec.mylocation.rximpl;


import com.example.dec.mylocation.pojo.AllVisitDetailsPojo;
import com.example.dec.mylocation.pojo.MapPojo;

import rx.Observer;

/**
 * Created by pritesh on 3/27/2017.
 */

public class AllMapDetailsPresenter extends BasePresentre implements Observer<MapPojo> {


    private ALlMapDetailInterface visitInterface;

    public AllMapDetailsPresenter(ALlMapDetailInterface visitInterface) {
        this.visitInterface = visitInterface;
    }

    @Override
    public void onCompleted() {


        visitInterface.onMapComplete();
    }

    @Override
    public void onError(Throwable e) {

        visitInterface.onMapError(e.getMessage());

    }

    @Override
    public void onNext(MapPojo detailsPojo) {

        visitInterface.onMap(detailsPojo);

    }


    public void getData(String user, String cdate){

        subscribe(visitInterface.getAllMapData(user,cdate),AllMapDetailsPresenter.this);

    }


}
