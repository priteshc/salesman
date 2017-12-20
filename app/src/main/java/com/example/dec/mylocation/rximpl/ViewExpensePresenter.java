package com.example.dec.mylocation.rximpl;


import com.example.dec.mylocation.pojo.AllVisitDetailsPojo;
import com.example.dec.mylocation.pojo.ExpenseAllpojo;

import rx.Observer;

/**
 * Created by pritesh on 3/27/2017.
 */

public class ViewExpensePresenter extends BasePresentre implements Observer<ExpenseAllpojo> {


    private ViewExpenseInterface visitInterface;

    public ViewExpensePresenter(ViewExpenseInterface visitInterface) {
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
    public void onNext(ExpenseAllpojo detailsPojo) {

        visitInterface.onLogin(detailsPojo);

    }


    public void getData(String id){

        subscribe(visitInterface.getAllVisitData(id),ViewExpensePresenter.this);

    }


}
