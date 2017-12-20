package com.example.dec.mylocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dec.mylocation.custom.SweetAlertDialog;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

/**
 * Created by pritesh on 8/17/2017.
 */

public class Followup_Filter extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{



    private TextView datetext;
    private Calendar calendar;
    private SweetAlertDialog progressDialog;

    private Button submit;
    private String cuname;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.follupf_ilter);


        datetext = (TextView) findViewById(R.id.date);

        submit = (Button) findViewById(R.id.button);

        calendar = Calendar.getInstance();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        datetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dpd = DatePickerDialog.newInstance(Followup_Filter.this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

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



                String cdate = datetext.getText().toString();


                if(cdate.equals("please select date")){


                    Toast.makeText(Followup_Filter.this,"plzz select one of the field",Toast.LENGTH_SHORT).show();


                }
                else {

                    Intent intent = new Intent(Followup_Filter.this,Followup_Data.class);
                    intent.putExtra("Date",cdate);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.left_right,R.anim.right_left);

                }






            }
        });


    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {


        int mon = monthOfYear + 1;

        String date = year + "-" + mon + "-" + dayOfMonth;


        datetext.setText(date);

    }
}
