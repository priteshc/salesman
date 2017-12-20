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
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dec.mylocation.BackTask.RetrofitBuild;
import com.example.dec.mylocation.adapter.AllVisit_Adapter;
import com.example.dec.mylocation.adapter.Visit_Adapter;
import com.example.dec.mylocation.custom.SweetAlertDialog;
import com.example.dec.mylocation.pojo.AllData;
import com.example.dec.mylocation.pojo.AllVisitDetailsPojo;
import com.example.dec.mylocation.pojo.Visit;
import com.example.dec.mylocation.rximpl.ALlVisitDetailInterface;
import com.example.dec.mylocation.rximpl.AllVisitDetailsPresenter;
import com.example.dec.mylocation.rximpl.ExpensePresenter;
import com.example.dec.mylocation.rximpl.VisitDetailsPresenter;
import com.example.dec.mylocation.rximpl.VisitFilterInterface;
import com.example.dec.mylocation.rximpl.VisitFilterPresenter;
import com.example.dec.mylocation.util.NetworkUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by pritesh on 5/26/2017.
 */

public class AllVisit extends AppCompatActivity implements ALlVisitDetailInterface,VisitFilterInterface{


    private RecyclerView recyclerView;

    private RetrofitBuild retrofitBuild;
    private AllVisitDetailsPresenter detailsPresenter;
    private VisitFilterPresenter filterPresenter;

    private AllVisit_Adapter visitAdapter;

    SweetAlertDialog progressDialog;


    List<AllData> visitList = new ArrayList<>();

    private TextView add,msgtext;

    private Toolbar toolbar;

    FloatingActionButton actionButton,clearButton;
    private SharedPreferences loginPreferences;
    private String loginid;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.allvisit);

        loginPreferences = this.getSharedPreferences("Login",0);

        loginid = loginPreferences.getString("Id","");


        progressDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        progressDialog.getProgressHelper().setBarColor(Color.parseColor("#2289DC"));
        progressDialog.setTitleText("Please  wait...");
        progressDialog.setCancelable(false);



        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        LinearLayoutManager llm = new LinearLayoutManager(AllVisit.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(llm);


        add = (TextView) findViewById(R.id.add);

        msgtext = (TextView) findViewById(R.id.msg);

        actionButton = (FloatingActionButton) findViewById(R.id.fab1);

        clearButton = (FloatingActionButton) findViewById(R.id.fab2);

        retrofitBuild = new RetrofitBuild();

        detailsPresenter = new AllVisitDetailsPresenter(this);
        filterPresenter = new VisitFilterPresenter(this);


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



        if(getIntent().getExtras()!= null){

            String dat = getIntent().getExtras().getString("Date","");
            String cname = getIntent().getExtras().getString("Cname","");

            if(!dat.equals("please select date")){


             filterPresenter.getData(dat,"");

            }
            else {

                if(!cname.equals("Select Customer")){


                    filterPresenter.getData("",cname);

                }


            }

            progressDialog.show();




        }
        else {


            if (NetworkUtil.getConnectivityStatus(this.getApplicationContext()) != 0) {


                detailsPresenter.getData(loginid);

                progressDialog.show();


            } else {


                new SweetAlertDialog(AllVisit.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Network")
                        .setContentText("Please check internet connection")
                        .setConfirmText("Ok")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {

                                sweetAlertDialog.dismiss();

                                Intent intent = new Intent(AllVisit.this, Menu.class);
                                startActivity(intent);
                                finish();
                                overridePendingTransition(R.anim.left_right, R.anim.right_left);


                            }
                        }).show();

            }

        }

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(AllVisit.this,Visit_search.class);
                startActivity(intent);

                overridePendingTransition(R.anim.enter,R.anim.exit);

            }
        });



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(AllVisit.this,AddVisit.class);
                startActivity(intent);
         //       finish();
                overridePendingTransition(R.anim.left_right,R.anim.right_left);


            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AllVisit.this,Menu.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.left_right,R.anim.right_left);

            }
        });


    }

    @Override
    public void onLoginComplete() {

        progressDialog.dismiss();

    }

    @Override
    public void onLoginError(String s) {

        progressDialog.dismiss();

        msgtext.setVisibility(View.VISIBLE);

        recyclerView.setVisibility(View.GONE);

        Toast.makeText(this, "server error", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLogin(AllVisitDetailsPojo allVisitDetailsPojo) {

        if(allVisitDetailsPojo.getSuccess() == 1){


            visitList = allVisitDetailsPojo.getData();

            visitAdapter = new AllVisit_Adapter(AllVisit.this,visitList);

            recyclerView.setAdapter(visitAdapter);

        }
        else {
            if(allVisitDetailsPojo.getSuccess() == 0){


                msgtext.setVisibility(View.VISIBLE);

                recyclerView.setVisibility(View.GONE);

            }
            else {

                if(visitList.size() <= 0 ){

                    msgtext.setVisibility(View.VISIBLE);

                    recyclerView.setVisibility(View.GONE);

                }

            }

        }


    }

    @Override
    public Observable<AllVisitDetailsPojo> getAllVisitData(String id) {
        return retrofitBuild.allApi().getAllVisitdata(id);
    }

    @Override
    public void onVLoginComplete() {

    }

    @Override
    public void onVLoginError(String s) {


        progressDialog.dismiss();

        msgtext.setVisibility(View.VISIBLE);

        recyclerView.setVisibility(View.GONE);

        Toast.makeText(this, "server error", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onVLogin(AllVisitDetailsPojo allVisitDetailsPojo) {

          progressDialog.dismiss();

        if(allVisitDetailsPojo.getSuccess() == 1){


            visitList = allVisitDetailsPojo.getData();

            visitAdapter = new AllVisit_Adapter(AllVisit.this,visitList);

            recyclerView.setAdapter(visitAdapter);

        }
        else {
            if(allVisitDetailsPojo.getSuccess() == 0){


                msgtext.setVisibility(View.VISIBLE);

                recyclerView.setVisibility(View.GONE);

            }
            else {

                if(visitList.size() <= 0 ){

                    msgtext.setVisibility(View.VISIBLE);

                    recyclerView.setVisibility(View.GONE);


                }



            }


        }


    }

    @Override
    public Observable<AllVisitDetailsPojo> getVisitfilter(String date, String cuname) {
        return retrofitBuild.allApi().getVisitFilter(date,cuname);
    }
}
