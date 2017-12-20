package com.example.dec.mylocation.rximpl;

import com.example.dec.mylocation.pojo.Purpose;
import com.example.dec.mylocation.pojo.PurposePojo;
import com.example.dec.mylocation.pojo.VisitDetailsPojo;

import rx.Observable;

/**
 * Created by pritesh on 4/11/2017.
 */

public interface PurposeDetailInterface {


    void onPComplete();
    void onPError(String s);
    void onPurpose(PurposePojo visitDetailsPojo);
    Observable<PurposePojo> getPurposeData();


}
