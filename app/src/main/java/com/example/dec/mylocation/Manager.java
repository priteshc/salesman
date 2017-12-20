package com.example.dec.mylocation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dec.mylocation.BackTask.RetrofitBuild;
import com.example.dec.mylocation.custom.SweetAlertDialog;
import com.example.dec.mylocation.gpstracking.PathGoogleMapActivity;
import com.example.dec.mylocation.pojo.AquLoginPojo;
import com.example.dec.mylocation.rximpl.MagrInterface;
import com.example.dec.mylocation.rximpl.Magr_Presenter;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import rx.Observable;

/**
 * Created by pritesh on 7/26/2017.
 */

public class Manager extends AppCompatActivity implements MagrInterface,DatePickerDialog.OnDateSetListener {


    private MaterialSpinner spinner1;
    private List<String> status = new ArrayList<>();

    Button button;
    private SharedPreferences loginPreferences;
    private String loginid;
    private SweetAlertDialog progressDialog;
    private Toolbar toolbar;
    private RetrofitBuild retrofitBuild;
    private Magr_Presenter magrPresenter;
    private TextView datetext;
    private Calendar calendar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.manager);


        loginPreferences = this.getSharedPreferences("Login",0);

        loginid = loginPreferences.getString("Id","");


        progressDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        progressDialog.getProgressHelper().setBarColor(Color.parseColor("#2289DC"));
        progressDialog.setTitleText("Please  wait...");
        progressDialog.setCancelable(false);


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

        spinner1 = (MaterialSpinner) findViewById(R.id.spinner2);

        button = (Button) findViewById(R.id.button2);

        datetext = (TextView) findViewById(R.id.date);

        calendar = Calendar.getInstance();

        retrofitBuild = new RetrofitBuild();

        magrPresenter = new Magr_Presenter(this);

        datetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dpd = DatePickerDialog.newInstance(Manager.this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

                dpd.show(getFragmentManager(), "datePicker");
                dpd.setAccentColor(getResources().getColor(R.color.DeepSkyBlue));

            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int i1 = spinner1.getSelectedIndex();

                String cdate = datetext.getText().toString();


                String   mystatus = status.get(i1).toString();


                if(mystatus.equals("Select Employee")){

                    Toast.makeText(Manager.this,"please select employee",Toast.LENGTH_SHORT).show();

                }
                else {

                    if(cdate.equals("please select date")){


                        Toast.makeText(Manager.this,"plzz select one of the field",Toast.LENGTH_SHORT).show();


                    }
                    else {


                        Intent intent = new Intent(Manager.this, PathGoogleMapActivity.class);

                        intent.putExtra("Name", mystatus);
                        intent.putExtra("Date", cdate);
                        startActivity(intent);

                    }
                }

            }
        });


        magrPresenter.getData(loginid);


        progressDialog.show();




    }

    @Override
    public void onMLoginComplete() {

        progressDialog.dismiss();

    }

    @Override
    public void onMLoginError(String s) {

        progressDialog.dismiss();

        Toast.makeText(Manager.this,"server error",Toast.LENGTH_SHORT).show();



    }

    @Override
    public void onMLogin(AquLoginPojo aquLoginPojo) {


        if(aquLoginPojo.getSuccess() == 1){


            for(int i =0 ;i < aquLoginPojo.getData().size(); i++){


                status.add(aquLoginPojo.getData().get(i).getLoginName());


            }

            status.add(0,"Select Employee");



            spinner1.setItems(status);


        }



    }

    @Override
    public Observable<AquLoginPojo> getLogin(String lid) {
        return retrofitBuild.allApi().getReportname(lid);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

        int mon = monthOfYear + 1;

        String date = year + "-" + mon + "-" + dayOfMonth;


        datetext.setText(date);


    }
}
