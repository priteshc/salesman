package com.example.dec.mylocation.rximpl;

import com.example.dec.mylocation.pojo.AllVisitDetailsPojo;
import com.example.dec.mylocation.pojo.MapPojo;

import rx.Observable;

/**
 * Created by pritesh on 4/11/2017.
 */

public interface ALlMapDetailInterface {


    void onMapComplete();
    void onMapError(String s);
    void onMap(MapPojo  mapPojo);
    Observable<MapPojo> getAllMapData(String user,String cdate);


}
