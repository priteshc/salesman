package com.example.dec.mylocation.rximpl;

import com.example.dec.mylocation.pojo.LocationPojo;

import rx.Observable;

/**
 * Created by pritesh on 4/11/2017.
 */

public interface LeaveInterface {


    void onLoginComp();
    void onLoginErr(String s);
    void onLogin(LocationPojo locationPojo);
    Observable<LocationPojo> getleave(String date, String logid, String leavetype, String lfrom, String lupto, String nofday, String levresone, String reptmgrid, String status, String remark,String usrtoken,String mgrtoken);


}
