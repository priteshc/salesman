package com.example.dec.mylocation.rximpl;

import com.example.dec.mylocation.pojo.AquLoginPojo;
import com.example.dec.mylocation.pojo.LeaveReportPojo;

import rx.Observer;

/**
 * Created by pritesh on 3/27/2017.
 */

public class LeaveReportPresenter extends BasePresentre implements Observer<LeaveReportPojo> {


    private LeaveReportInterface visitInterface;

    public LeaveReportPresenter(LeaveReportInterface visitInterface) {
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
    public void onNext(LeaveReportPojo aquLoginPojo) {

        visitInterface.onLogin(aquLoginPojo);

    }


    public void getData(String from,String to,String id){

        subscribe(visitInterface.getReport(from,to,id),LeaveReportPresenter.this);

    }


}
