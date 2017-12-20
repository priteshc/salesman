package com.example.dec.mylocation;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dec.mylocation.BackTask.RetrofitBuild;
import com.example.dec.mylocation.custom.SweetAlertDialog;
import com.example.dec.mylocation.pojo.LocationPojo;
import com.example.dec.mylocation.pojo.NotificationPojo;
import com.example.dec.mylocation.rximpl.NotificationInterface;
import com.example.dec.mylocation.rximpl.NotificationPresenter;
import com.example.dec.mylocation.rximpl.UpdateInterface;
import com.example.dec.mylocation.rximpl.UpdatePresenter;

import rx.Observable;

/**
 * Created by pritesh on 8/8/2017.
 */

public class LeaveAproved extends AppCompatActivity implements NotificationInterface,UpdateInterface {


    private Button approve,rejected;

    String token;
    private NotificationPresenter notificationPresenter;
    private UpdatePresenter updatePresenter;
    private SharedPreferences loginPreferences;
    private String magrid;
    private SweetAlertDialog progressDialog;
    private RetrofitBuild retrofitBuild;
    private String desci;
    private String name;
    private String type;

    private TextView requestfr,reqtyp,reqdesc;
    private String uid,approval;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.leavapprov);


        approve = (Button) findViewById(R.id.approve);

        rejected = (Button) findViewById(R.id.rej);


        loginPreferences = this.getSharedPreferences("Login",0);

        magrid = loginPreferences.getString("log","");

        notificationPresenter = new NotificationPresenter(this);

        updatePresenter = new UpdatePresenter(this);

        retrofitBuild = new RetrofitBuild();


        requestfr = (TextView) findViewById(R.id.name);

        reqtyp = (TextView) findViewById(R.id.leavetype);

        reqdesc = (TextView) findViewById(R.id.reeson);


        progressDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        progressDialog.getProgressHelper().setBarColor(Color.parseColor("#2289DC"));
        progressDialog.setTitleText("Please  wait...");
        progressDialog.setCancelable(false);

        if(getIntent().getExtras()!= null){

        token = getIntent().getExtras().getString("User");

            desci = getIntent().getExtras().getString("desc");
            name = getIntent().getExtras().getString("Name");
            type = getIntent().getExtras().getString("Type");

            uid = getIntent().getExtras().getString("Uid");


            requestfr.setText(name);

            reqtyp.setText(type);

            reqdesc.setText(desci);




        }


        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                updatePresenter.getData(uid,"Approved");

              //  notificationPresenter.getData("Approved",token,magrid,"ghhh","","","");

                approval = "Approved";

                   progressDialog.show();
            }
        });


        rejected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                updatePresenter.getData(uid,"Rejected");


            //    notificationPresenter.getData("Rejected",token,magrid,"ghhh","","","");

                approval = "Rejected";

                progressDialog.show();
            }
        });



    }

    @Override
    public void onNLoginComplete() {


        progressDialog.dismiss();

    }

    @Override
    public void onNLoginError(String s) {

        progressDialog.dismiss();

        Toast.makeText(LeaveAproved.this,"Success",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNLogin(NotificationPojo notificationPojo) {

        progressDialog.dismiss();

    }

    @Override
    public Observable<NotificationPojo> getConf(String desc, String token, String id, String utoken,String name,String type,String uid) {
        return retrofitBuild.allApi().getNotification(desc,token,id,utoken,name,type,uid);
    }

    @Override
    public void onELoginComp() {

    }

    @Override
    public void onELoginErr(String s) {

        progressDialog.dismiss();

        Toast.makeText(LeaveAproved.this,"error",Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onELogin(LocationPojo locationPojo) {


        if(locationPojo.getSuccess() == 1){


            notificationPresenter.getData(approval,token,magrid,"ghhh","","","");


        }else {

            progressDialog.dismiss();

            Toast.makeText(LeaveAproved.this,"please try again",Toast.LENGTH_SHORT).show();


        }


    }

    @Override
    public Observable<LocationPojo> getExpense(String id, String status) {
        return retrofitBuild.allApi().getUpdate(id,status);
    }
}
