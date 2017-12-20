package com.example.dec.mylocation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dec.mylocation.BackTask.RetrofitBuild;
import com.example.dec.mylocation.custom.SweetAlertDialog;
import com.example.dec.mylocation.pojo.VisitDetailsPojo;
import com.example.dec.mylocation.rximpl.VisitDetailInterface;
import com.example.dec.mylocation.rximpl.VisitDetailsPresenter;
import com.example.dec.mylocation.rximpl.VisitInterface;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

import rx.Observable;

/**
 * Created by pritesh on 8/5/2017.
 */

public class Visit_search extends AppCompatActivity implements VisitDetailInterface,DatePickerDialog.OnDateSetListener{


    private RetrofitBuild retrofitBuild;
    private VisitDetailsPresenter detailsPresenter;


    ArrayList<String> strings = new ArrayList<>();

    MaterialSpinner  customer;

    private TextView datetext;
    private Calendar calendar;
    private SweetAlertDialog progressDialog;

    private Button submit;
    private String cuname;
    private Toolbar toolbar;
    private SharedPreferences loginPreferences;
    private String region;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.visit_search);


        customer = (MaterialSpinner) findViewById(R.id.spinner);

        datetext = (TextView) findViewById(R.id.date);

        submit = (Button) findViewById(R.id.button);

        calendar = Calendar.getInstance();

        loginPreferences = this.getSharedPreferences("Login",0);



        region = loginPreferences.getString("Region","");


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        retrofitBuild = new RetrofitBuild();

        detailsPresenter = new VisitDetailsPresenter(this);

        progressDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        progressDialog.getProgressHelper().setBarColor(Color.parseColor("#2289DC"));
        progressDialog.setTitleText("Please  wait...");
        progressDialog.setCancelable(false);


        detailsPresenter.getData(region);

        progressDialog.show();


        datetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dpd = DatePickerDialog.newInstance(Visit_search.this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

                dpd.show(getFragmentManager(), "datePicker");
                dpd.setAccentColor(getResources().getColor(R.color.DeepSkyBlue));

            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });




        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int i1 = customer.getSelectedIndex();

                cuname = strings.get(i1).toString();

                String cdate = datetext.getText().toString();


                if(cdate.equals("please select date") && cuname.equals("Select Customer")){


                    Toast.makeText(Visit_search.this,"plzz select one of the field",Toast.LENGTH_SHORT).show();


                }
                else {

                    Intent intent = new Intent(Visit_search.this,AllVisit.class);
                    intent.putExtra("Date",cdate);
                    intent.putExtra("Cname",cuname);
                    startActivity(intent);
                    overridePendingTransition(R.anim.left_right,R.anim.right_left);

                }






            }
        });

    }

    @Override
    public void onLoginComplete() {

    }

    @Override
    public void onLoginError(String s) {

        progressDialog.dismiss();
    }

    @Override
    public void onLogin(VisitDetailsPojo visitDetailsPojo) {

        progressDialog.dismiss();

        if (visitDetailsPojo.getSuccess() == 1) {



            for (int i = 0; i < visitDetailsPojo.getData().size(); i++) {

                strings.add(visitDetailsPojo.getData().get(i).getCustName());

            }


            strings.add(0,"Select Customer");
            customer.setItems(strings);


        }




    }

    @Override
    public Observable<VisitDetailsPojo> getVisitData(String reg) {
        return retrofitBuild.allApi().getVisitdata(reg);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

        int mon = monthOfYear + 1;

        String date = year + "-" + mon + "-" + dayOfMonth;


        datetext.setText(date);


    }
}
