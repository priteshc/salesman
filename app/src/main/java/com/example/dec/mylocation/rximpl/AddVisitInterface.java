package com.example.dec.mylocation.rximpl;

import com.example.dec.mylocation.pojo.LocationPojo;

import rx.Observable;

/**
 * Created by pritesh on 4/11/2017.
 */

public interface AddVisitInterface {


    void onLoginComp();
    void onLoginErr(String s);
    void onLogin(LocationPojo locationPojo);
    Observable<LocationPojo> getvisit(String vid,String date, String time, String purposeid,String statusid, String prodid, String desc, String custid, String contname, String remark, String fdate,String ftime,String logname,String logid,String frmtime,String ttime,String hrs);


}
