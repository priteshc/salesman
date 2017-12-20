package com.example.dec.mylocation;

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
import com.example.dec.mylocation.adapter.Followup_Adapter;
import com.example.dec.mylocation.custom.SweetAlertDialog;
import com.example.dec.mylocation.pojo.AllData;
import com.example.dec.mylocation.pojo.AllVisitDetailsPojo;
import com.example.dec.mylocation.rximpl.AllVisitDetailsPresenter;
import com.example.dec.mylocation.rximpl.FollowupDetailInterface;
import com.example.dec.mylocation.rximpl.FollowupDetailsPresenter;
import com.example.dec.mylocation.rximpl.VisitFilterPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import rx.Observable;

/**
 * Created by pritesh on 8/16/2017.
 */

public class Followup_Data extends AppCompatActivity implements FollowupDetailInterface {


    private SharedPreferences loginPreferences;
    private String loginid;
    private SweetAlertDialog progressDialog;
    private RecyclerView recyclerView;
    private TextView msgtext;
    private FloatingActionButton actionButton;
    private FloatingActionButton clearButton;
    private RetrofitBuild retrofitBuild;
    private Toolbar toolbar;
    private FollowupDetailsPresenter followupDetailsPresenter;
    private Followup_Adapter followup_adapter;
    List<AllData> visitList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.followup);



        loginPreferences = this.getSharedPreferences("Login",0);

        loginid = loginPreferences.getString("Id","");


        progressDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        progressDialog.getProgressHelper().setBarColor(Color.parseColor("#2289DC"));
        progressDialog.setTitleText("Please  wait...");
        progressDialog.setCancelable(false);



        Calendar c = Calendar.getInstance();


        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        String formattedDate = df.format(c.getTime());



        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        LinearLayoutManager llm = new LinearLayoutManager(Followup_Data.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(llm);


        msgtext = (TextView) findViewById(R.id.msg);

        actionButton = (FloatingActionButton) findViewById(R.id.fab1);

        clearButton = (FloatingActionButton) findViewById(R.id.fab2);

        retrofitBuild = new RetrofitBuild();

         followupDetailsPresenter = new FollowupDetailsPresenter(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });




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


                Intent intent = new Intent(Followup_Data.this,Followup_Filter.class);
                startActivity(intent);

                overridePendingTransition(R.anim.enter,R.anim.exit);

            }
        });


        if(getIntent().getExtras()!= null) {

            String dat = getIntent().getExtras().getString("Date", "");


            followupDetailsPresenter.getData(loginid,dat);

            progressDialog.show();


        }
        else
            {

            followupDetailsPresenter.getData(loginid, formattedDate);

            progressDialog.dismiss();

        }

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

            followup_adapter = new Followup_Adapter(Followup_Data.this,visitList);

            recyclerView.setAdapter(followup_adapter);

        }
        else {
            if (allVisitDetailsPojo.getSuccess() == 0) {


                msgtext.setVisibility(View.VISIBLE);

                recyclerView.setVisibility(View.GONE);

            }

        }
    }

    @Override
    public Observable<AllVisitDetailsPojo> getAllFollowData(String id, String date) {
        return retrofitBuild.allApi().getFollowupData(id,date);

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
