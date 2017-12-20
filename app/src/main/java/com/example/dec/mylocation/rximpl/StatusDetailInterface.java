package com.example.dec.mylocation.rximpl;

import com.example.dec.mylocation.pojo.PurposePojo;
import com.example.dec.mylocation.pojo.StatusPojo;

import rx.Observable;

/**
 * Created by pritesh on 4/11/2017.
 */

public interface StatusDetailInterface {


    void onSComplete();
    void onSError(String s);
    void onStatus(StatusPojo visitDetailsPojo);
    Observable<StatusPojo> getStatusData();


}
