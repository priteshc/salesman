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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.dec.mylocation.BackTask.RetrofitBuild;
import com.example.dec.mylocation.custom.SweetAlertDialog;
import com.example.dec.mylocation.pojo.LeaveReportPojo;
import com.example.dec.mylocation.rximpl.LeaveReportInterface;
import com.example.dec.mylocation.rximpl.LeaveReportPresenter;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import rx.Observable;

/**
 * Created by pritesh on 8/10/2017.
 */

public class User_LeaveReport extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,LeaveReportInterface{

    private TextView from ,to,found;

    private Button submit;

    private Calendar calendar;
    private SharedPreferences preferencesDat;

    private LeaveReportPresenter leaveReportPresenter;
    private RetrofitBuild retrofitBuild;
    private SweetAlertDialog progressDialog;
    private TableLayout t1;
    private TableLayout t2;
    private Toolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.user_leave_search);



        preferencesDat = this.getSharedPreferences("FRDAT",0);


        from = (TextView) findViewById(R.id.fdate);

        to = (TextView) findViewById(R.id.tdate);

        found = (TextView) findViewById(R.id.found);


        submit = (Button) findViewById(R.id.button);

        calendar = Calendar.getInstance();

        t1 = (TableLayout) findViewById(R.id.main_table);

        t2 = (TableLayout) findViewById(R.id.main_table1);


        inittab();


        retrofitBuild = new RetrofitBuild();
          leaveReportPresenter = new LeaveReportPresenter(this);


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


        from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dpd1 = DatePickerDialog.newInstance(User_LeaveReport.this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

                dpd1.show(getFragmentManager(), "datePicker");
                dpd1.setAccentColor(getResources().getColor(R.color.DeepSkyBlue));

                preferencesDat.edit().clear().commit();

                preferencesDat.edit().putString("DATE","frdt").apply();


            }
        });



        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                DatePickerDialog dpd1 = DatePickerDialog.newInstance(User_LeaveReport.this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

                dpd1.show(getFragmentManager(), "datePicker");
                dpd1.setAccentColor(getResources().getColor(R.color.DeepSkyBlue));

                preferencesDat.edit().clear().commit();

                preferencesDat.edit().putString("DATE","todt").apply();

            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                t2.removeAllViewsInLayout();

                leaveReportPresenter.getData(from.getText().toString(),to.getText().toString(),"");

                  progressDialog.show();

            }
        });


    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {


        int mon = monthOfYear + 1;

        String date = year + "-" + mon + "-" + dayOfMonth;

        String dat = preferencesDat.getString("DATE","");

        if(dat.equals("frdt")){

            from.setText(date);

        }
        else {

            to.setText(date);

        }
    }

    @Override
    public void onLoginComplete() {

        progressDialog.dismiss();

    }

    @Override
    public void onLoginError(String s) {

        progressDialog.dismiss();

    }

    @Override
    public void onLogin(LeaveReportPojo aquLoginPojo) {

        if(aquLoginPojo.getSuccess() == 1){

         //   Integer count=0;


            for(int i = 0; i< aquLoginPojo.getData().size(); i++){


              String date = aquLoginPojo.getData().get(i).getLeaveFrom().toString();

              String weight_kg = aquLoginPojo.getData().get(i).getLeaveUpto().toString();
              String typ = aquLoginPojo.getData().get(i).getLeaveType().toString();
              String stus = aquLoginPojo.getData().get(i).getStatusType().toString();


// Create the table row
              TableRow tr = new TableRow(this);
                tr.setMinimumHeight(50);


                if(i%2!=0) {
                    tr.setBackgroundColor(getResources().getColor(R.color.SteelBlue));
                }
                else {
                    tr.setBackgroundColor(getResources().getColor(R.color.WhiteSmoke));
                }
             // tr.setId(100+count);
              tr.setLayoutParams(new TableLayout.LayoutParams(
                      TableLayout.LayoutParams.FILL_PARENT,
                      TableLayout.LayoutParams.WRAP_CONTENT));

//Create two columns to add as table data
              // Create a TextView to add date
              TextView labelDATE = new TextView(this);
            //  labelDATE.setId(200+count);
              labelDATE.setText(date);
              labelDATE.setPadding(2, 0, 5, 0);
              labelDATE.setTextColor(Color.BLACK);
              tr.addView(labelDATE);

              TextView labelWEIGHT = new TextView(this);
            //  labelWEIGHT.setId(200+count);
              labelWEIGHT.setText(weight_kg);
                labelWEIGHT.setPadding(0, 0, 20, 0);

                labelWEIGHT.setTextColor(Color.BLACK);
              tr.addView(labelWEIGHT);


              TextView labelWEIGHT1 = new TextView(this);
            //  labelWEIGHT1.setId(200+count);
              labelWEIGHT1.setText(typ);
              labelWEIGHT1.setPadding(0, 0, 40, 0);
              labelWEIGHT1.setTextColor(Color.BLACK);
              tr.addView(labelWEIGHT1);


              TextView labelWEIGHT2 = new TextView(this);
           //   labelWEIGHT2.setId(200+count);
              labelWEIGHT2.setText(stus);
              labelWEIGHT2.setTextColor(Color.BLACK);
                labelWEIGHT2.setPadding(0, 0, 10, 0);

                tr.addView(labelWEIGHT2);



// finally add this to the table row
              t2.addView(tr, new TableLayout.LayoutParams(
                      TableLayout.LayoutParams.FILL_PARENT,
                      TableLayout.LayoutParams.WRAP_CONTENT));
               // count++;

          }



             t1.setVisibility(View.VISIBLE);

            t2.setVisibility(View.VISIBLE);

        }
        else {


         found.setVisibility(View.VISIBLE);

            t1.setVisibility(View.GONE);

            t2.setVisibility(View.GONE);


        }



    }

    @Override
    public Observable<LeaveReportPojo> getReport(String from, String to,String id) {
        return retrofitBuild.allApi().getReport(from,to,id);
    }


    private void inittab(){



        TableRow tr_head = new TableRow(this);
        //  tr_head.setId(10);
        tr_head.setBackgroundColor(Color.GRAY);
        tr_head.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));



        TextView label_date = new TextView(this);
        // label_date.setId(20);
        label_date.setText("Fr Date");
        label_date.setTextColor(Color.WHITE);
        label_date.setPadding(10, 5, 0, 5);
        tr_head.addView(label_date);// add the column to the table row here

        TextView label_weight_kg = new TextView(this);
        // label_weight_kg.setId(21);// define id that must be unique
        label_weight_kg.setText("To Date"); // set the text for the header
        label_weight_kg.setTextColor(Color.WHITE); // set the color
        label_weight_kg.setPadding(50, 5, 0, 5); // set the padding (if required)
        tr_head.addView(label_weight_kg); // add the column to the table row here

        TextView label_weight_kg1 = new TextView(this);
        // label_weight_kg.setId(21);// define id that must be unique
        label_weight_kg1.setText("Lev Type"); // set the text for the header
        label_weight_kg1.setTextColor(Color.WHITE); // set the color
        label_weight_kg1.setPadding(0, 5, 30, 5); // set the padding (if required)
        tr_head.addView(label_weight_kg1);

        TextView label_weight_kg2 = new TextView(this);
        // label_weight_kg.setId(21);// define id that must be unique
        label_weight_kg2.setText("Status"); // set the text for the header
        label_weight_kg2.setTextColor(Color.WHITE); // set the color
        label_weight_kg2.setPadding(0, 5, 20, 5); // set the padding (if required)
        tr_head.addView(label_weight_kg2);



        t1.addView(tr_head, new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));




    }


}
