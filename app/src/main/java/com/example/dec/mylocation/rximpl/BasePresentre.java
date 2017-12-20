package com.example.dec.mylocation.rximpl;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by pritesh on 3/27/2017.
 */

public class BasePresentre implements PresenterInterface {

    private CompositeSubscription compositeSubscription;


    @Override
    public void onCreate() {

        unSubscribeAll();
    }

    @Override
    public void onDestoy() {



    }

    @Override
    public void onpause() {

    }

    @Override
    public void onResume() {

        configureSubscription();
    }


    private CompositeSubscription configureSubscription(){

        if(compositeSubscription == null || compositeSubscription.isUnsubscribed()){

            compositeSubscription = new CompositeSubscription();

        }

        return  compositeSubscription;

    }

    protected void unSubscribeAll() {
        if (compositeSubscription != null) {
            compositeSubscription.unsubscribe();
            compositeSubscription.clear();
        }
    }


    protected <T> void subscribe(Observable<T> observable, Observer<T> observer) {
        Subscription subscription = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.computation())
                .subscribe(observer);
        configureSubscription().add(subscription);
    }
}
