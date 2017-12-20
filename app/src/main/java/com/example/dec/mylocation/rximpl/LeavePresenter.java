package com.example.dec.mylocation.rximpl;

import com.example.dec.mylocation.pojo.LocationPojo;

import rx.Observer;

/**
 * Created by pritesh on 3/27/2017.
 */

public class LeavePresenter extends BasePresentre implements Observer<LocationPojo> {


    private LeaveInterface visitInterface;

    public LeavePresenter(LeaveInterface visitInterface) {
        this.visitInterface = visitInterface;
    }

    @Override
    public void onCompleted() {


        visitInterface.onLoginComp();
    }

    @Override
    public void onError(Throwable e) {

        visitInterface.onLoginErr(e.getMessage());

    }

    @Override
    public void onNext(LocationPojo locationPojo) {

        visitInterface.onLogin(locationPojo);

    }


    public void getData(String date, String logid, String leavetype, String lfrom, String lupto, String nofday, String levresone, String reptmgrid, String status, String remark,String usertoken,String mgrtoken){

        subscribe(visitInterface.getleave( date,  logid,  leavetype,  lfrom,  lupto,  nofday,  levresone,  reptmgrid,  status,  remark,usertoken,mgrtoken),LeavePresenter.this);

    }


}
