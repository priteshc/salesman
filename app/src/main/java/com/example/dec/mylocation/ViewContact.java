package com.example.dec.mylocation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.dec.mylocation.BackTask.RetrofitBuild;
import com.example.dec.mylocation.adapter.Contactt_Adapter;
import com.example.dec.mylocation.custom.SweetAlertDialog;
import com.example.dec.mylocation.pojo.Contact;
import com.example.dec.mylocation.pojo.ContactPojo;
import com.example.dec.mylocation.rximpl.ViewContactInterface;
import com.example.dec.mylocation.rximpl.ViewContactPresenter;
import com.example.dec.mylocation.util.NetworkUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by pritesh on 5/31/2017.
 */

public class ViewContact extends AppCompatActivity implements ViewContactInterface {


    private RecyclerView recyclerView;
    private SweetAlertDialog progressDialog;
    private Toolbar toolbar;
    private ViewContactPresenter viewContactPresenter;
    private RetrofitBuild retrofitBuild;

    List<Contact> contacts = new ArrayList<>();
    Contactt_Adapter contactt_adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.contact);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        LinearLayoutManager llm = new LinearLayoutManager(ViewContact.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(llm);

        progressDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        progressDialog.getProgressHelper().setBarColor(Color.parseColor("#2289DC"));
        progressDialog.setTitleText("Please  wait...");
        progressDialog.setCancelable(false);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        retrofitBuild = new RetrofitBuild();

        viewContactPresenter = new ViewContactPresenter(this);


        String id = getIntent().getExtras().getString("ID");

        if (NetworkUtil.getConnectivityStatus(this.getApplicationContext()) != 0) {


            viewContactPresenter.getData(id);

            progressDialog.show();
        }
        else {


            new SweetAlertDialog(ViewContact.this,SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Network")
                    .setContentText("Please check internet connection")
                    .setConfirmText("Ok")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {

                            sweetAlertDialog.dismiss();

                           finish();
                        }
                    }).show();

        }


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();


            }
        });



    }

    @Override
    public void onVLoginComplete() {

        progressDialog.dismiss();

        contactt_adapter = new Contactt_Adapter(ViewContact.this,contacts);

        recyclerView.setAdapter(contactt_adapter);

    }

    @Override
    public void onVLoginError(String s) {

        progressDialog.dismiss();




    }

    @Override
    public void onVLogin(ContactPojo contactPojo) {

        progressDialog.dismiss();

        if(contactPojo.getSuccess() == 1){


            contacts = contactPojo.getData();

        }


    }

    @Override
    public Observable<ContactPojo> getAllCont(String id) {
        return retrofitBuild.allApi().getAllcont(id);
    }
}
