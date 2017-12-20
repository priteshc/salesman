package com.example.dec.mylocation.rximpl;

import com.example.dec.mylocation.Login;
import com.example.dec.mylocation.pojo.AquLoginPojo;
import com.example.dec.mylocation.pojo.LocationPojo;

import rx.Observer;

/**
 * Created by pritesh on 3/27/2017.
 */

public class LoginPresenter extends BasePresentre implements Observer<AquLoginPojo> {


    private LoginInterface visitInterface;

    public LoginPresenter(LoginInterface visitInterface) {
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
    public void onNext(AquLoginPojo aquLoginPojo) {

        visitInterface.onLogin(aquLoginPojo);

    }


    public void getData(String email,String password){

        subscribe(visitInterface.getLogin(email,password),LoginPresenter.this);

    }


}
