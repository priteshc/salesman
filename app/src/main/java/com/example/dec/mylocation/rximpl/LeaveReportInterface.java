package com.example.dec.mylocation.rximpl;

import com.example.dec.mylocation.pojo.AquLoginPojo;
import com.example.dec.mylocation.pojo.LeaveReportPojo;

import rx.Observable;

/**
 * Created by pritesh on 4/11/2017.
 */

public interface LeaveReportInterface {


    void onLoginComplete();
    void onLoginError(String s);
    void onLogin(LeaveReportPojo aquLoginPojo);
    Observable<LeaveReportPojo> getReport(String from, String to,String id);


}
