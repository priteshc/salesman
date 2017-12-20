package com.example.dec.mylocation.rximpl;

import com.example.dec.mylocation.pojo.LocationPojo;

import rx.Observer;

/**
 * Created by pritesh on 3/27/2017.
 */

public class AddVisitPresenter extends BasePresentre implements Observer<LocationPojo> {


    private AddVisitInterface visitInterface;

    public AddVisitPresenter(AddVisitInterface visitInterface) {
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


    public void getData(String vid,String date, String time, String purposeid,String statusid, String prodid, String desc, String custid, String contname, String remark, String fdate,String ftime,String logname,String logid,String frmtime,String ttime,String hrs){

        subscribe(visitInterface.getvisit(vid,date, time, purposeid, statusid, prodid, desc,custid,contname, remark,fdate,ftime,logname,logid, frmtime,ttime,hrs),AddVisitPresenter.this);

    }


}
