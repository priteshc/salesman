package com.example.dec.mylocation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.dec.mylocation.BackTask.RetrofitBuild;
import com.example.dec.mylocation.adapter.Visit_Adapter;
import com.example.dec.mylocation.custom.SweetAlertDialog;
import com.example.dec.mylocation.pojo.Visit;
import com.example.dec.mylocation.pojo.VisitDetailsPojo;
import com.example.dec.mylocation.rximpl.VisitDetailInterface;
import com.example.dec.mylocation.rximpl.VisitDetailsFilterInterface;
import com.example.dec.mylocation.rximpl.VisitDetailsFilterPresenter;
import com.example.dec.mylocation.rximpl.VisitDetailsPresenter;
import com.example.dec.mylocation.rximpl.VisitPresenter;
import com.example.dec.mylocation.util.NetworkUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by pritesh on 5/24/2017.
 */

public class Visit_Details extends AppCompatActivity implements VisitDetailInterface,VisitDetailsFilterInterface{


    private RecyclerView recyclerView;

    private RetrofitBuild retrofitBuild;
    private VisitDetailsPresenter detailsPresenter;

    private Visit_Adapter visitAdapter;

    SweetAlertDialog progressDialog;


    List<Visit> visitList = new ArrayList<>();

    private TextView add;

    private Toolbar toolbar;
    private TextView msgtext;
    private SharedPreferences loginPreferences;
    private String loginid;
    private FloatingActionButton actionButton;
    private FloatingActionButton clearButton;

    private VisitDetailsFilterPresenter visitDetailsFilterPresenter;
    private String region;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.visitdetails);



        loginPreferences = this.getSharedPreferences("Login",0);

        loginid = loginPreferences.getString("Id","");

        region = loginPreferences.getString("Region","");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        LinearLayoutManager llm = new LinearLayoutManager(Visit_Details.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(llm);



        actionButton = (FloatingActionButton) findViewById(R.id.fab1);

        clearButton = (FloatingActionButton) findViewById(R.id.fab2);

        add = (TextView) findViewById(R.id.add);

        msgtext = (TextView) findViewById(R.id.msg);



        retrofitBuild = new RetrofitBuild();

        detailsPresenter = new VisitDetailsPresenter(this);

        visitDetailsFilterPresenter = new VisitDetailsFilterPresenter(this);

        progressDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        progressDialog.getProgressHelper().setBarColor(Color.parseColor("#2289DC"));
        progressDialog.setTitleText("Please  wait...");
        progressDialog.setCancelable(false);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                if (dy > 0 ||dy<0 && actionButton.isShown())
                {
                    actionButton.hide();

                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                {
                    actionButton.show();
                }

                super.onScrollStateChanged(recyclerView, newState);
            }
        });



        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Visit_Details.this,Customer_search.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.enter,R.anim.exit);

            }
        });


        if(getIntent().getExtras()!= null){


          String name = getIntent().getExtras().getString("Date");

   visitDetailsFilterPresenter.getData(name);

            progressDialog.show();


        }

        else
            {


        if (NetworkUtil.getConnectivityStatus(this.getApplicationContext()) != 0) {


            detailsPresenter.getData(region);

            progressDialog.show();

        }

        else {

            new SweetAlertDialog(Visit_Details.this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Network")
                    .setContentText("Please check internet connection")
                    .setConfirmText("Ok")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {

                            sweetAlertDialog.dismiss();

                            Intent intent = new Intent(Visit_Details.this, Menu.class);
                            startActivity(intent);
                            finish();
                            overridePendingTransition(R.anim.left_right, R.anim.right_left);

                        }
                    }).show();


        }
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Visit_Details.this,Menu.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.left_right,R.anim.right_left);



            }
        });



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Visit_Details.this,MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.enter,R.anim.exit);


            }
        });

    }

    @Override
    public void onLoginComplete() {

        System.out.println("Msg:" + visitList.size());


        progressDialog.dismiss();
    }

    @Override
    public void onLoginError(String s) {


        System.out.println("Msg:" + s);

        progressDialog.dismiss();

        msgtext.setVisibility(View.VISIBLE);

        recyclerView.setVisibility(View.GONE);

    }

    @Override
    public void onLogin(VisitDetailsPojo visitDetailsPojo) {

        System.out.println("Msg:" + visitDetailsPojo.getSuccess());

        if(visitDetailsPojo.getSuccess() == 1){


            visitList = visitDetailsPojo.getData();

            visitAdapter = new Visit_Adapter(Visit_Details.this,visitList);

            recyclerView.setAdapter(visitAdapter);

        }
        else {

            if(visitList.size() <= 0) {

                msgtext.setVisibility(View.VISIBLE);

                recyclerView.setVisibility(View.GONE);
            }

        }



    }

    @Override
    public Observable<VisitDetailsPojo> getVisitData(String reg) {
        return retrofitBuild.allApi().getVisitdata(reg);
    }

    @Override
    public void onVLoginComplete() {

        progressDialog.dismiss();
    }

    @Override
    public void onVLoginError(String s) {


        progressDialog.dismiss();

        msgtext.setVisibility(View.VISIBLE);

        recyclerView.setVisibility(View.GONE);

    }

    @Override
    public void onVLogin(VisitDetailsPojo allVisitDetailsPojo) {

        if(allVisitDetailsPojo.getSuccess() == 1){


            visitList = allVisitDetailsPojo.getData();

            visitAdapter = new Visit_Adapter(Visit_Details.this,visitList);

            recyclerView.setAdapter(visitAdapter);

        }
        else {

            if(visitList.size() <= 0) {

                msgtext.setVisibility(View.VISIBLE);

                recyclerView.setVisibility(View.GONE);
            }

        }




    }

    @Override
    public Observable<VisitDetailsPojo> getVisitfilter(String name) {
        return retrofitBuild.allApi().getVisitdatafilter(name);

    }



}
