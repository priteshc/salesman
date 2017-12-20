package com.example.dec.mylocation.rximpl;

import com.example.dec.mylocation.pojo.AllVisitDetailsPojo;
import com.example.dec.mylocation.pojo.ExpenseAllpojo;

import rx.Observable;

/**
 * Created by pritesh on 4/11/2017.
 */

public interface ViewExpenseInterface {


    void onLoginComplete();
    void onLoginError(String s);
    void onLogin(ExpenseAllpojo allVisitDetailsPojo);
    Observable<ExpenseAllpojo> getAllVisitData(String id);


}
