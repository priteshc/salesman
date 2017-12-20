package com.example.dec.mylocation.rximpl;

import com.example.dec.mylocation.pojo.LocationPojo;

import rx.Observer;

/**
 * Created by pritesh on 3/27/2017.
 */

public class ExpensePresenter extends BasePresentre implements Observer<LocationPojo> {


    private ExpenseInterface visitInterface;

    public ExpensePresenter(ExpenseInterface visitInterface) {
        this.visitInterface = visitInterface;
    }

    @Override
    public void onCompleted() {


        visitInterface.onELoginComp();
    }

    @Override
    public void onError(Throwable e) {

        visitInterface.onELoginErr(e.getMessage());

    }

    @Override
    public void onNext(LocationPojo locationPojo) {

        visitInterface.onELogin(locationPojo);

    }


    public void getData(String id, String date, String company, String description, String km, String amount){

        subscribe(visitInterface.getExpense(id,date,company,description,km,amount),ExpensePresenter.this);

    }


}
