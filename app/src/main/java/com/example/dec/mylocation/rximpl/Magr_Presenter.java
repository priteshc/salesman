package com.example.dec.mylocation.rximpl;

import com.example.dec.mylocation.pojo.AquLoginPojo;

import rx.Observer;

/**
 * Created by pritesh on 3/27/2017.
 */

public class Magr_Presenter extends BasePresentre implements Observer<AquLoginPojo> {


    private MagrInterface visitInterface;

    public Magr_Presenter(MagrInterface visitInterface) {
        this.visitInterface = visitInterface;
    }

    @Override
    public void onCompleted() {


        visitInterface.onMLoginComplete();
    }

    @Override
    public void onError(Throwable e) {

        visitInterface.onMLoginError(e.getMessage());

    }

    @Override
    public void onNext(AquLoginPojo aquLoginPojo) {

        visitInterface.onMLogin(aquLoginPojo);

    }


    public void getData(String lid){

        subscribe(visitInterface.getLogin(lid),Magr_Presenter.this);

    }


}
