package com.example.dec.mylocation;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dec.mylocation.BackTask.RetrofitBuild;
import com.example.dec.mylocation.custom.SweetAlertDialog;
import com.example.dec.mylocation.pojo.LocationPojo;
import com.example.dec.mylocation.pojo.NotificationPojo;
import com.example.dec.mylocation.rximpl.LeaveInterface;
import com.example.dec.mylocation.rximpl.LeavePresenter;
import com.example.dec.mylocation.rximpl.NotificationInterface;
import com.example.dec.mylocation.rximpl.NotificationPresenter;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import rx.Observable;

/**
 * Created by pritesh on 8/3/2017.
 */

public class LeaveRequest extends AppCompatActivity  implements LeaveInterface,DatePickerDialog.OnDateSetListener,NotificationInterface{


    private List<String> status = new ArrayList<>();
    private MaterialSpinner spinner1;
    private Button button,frm,to;
    private RetrofitBuild retrofitBuild;
    private LeavePresenter leavePresenter;
    private Calendar calendar;
    private SweetAlertDialog progressDialog;
    private SharedPreferences preferencesDat;
    private String leavetype,frmdat,todate,desc;
    private EditText description;

    private TextView days;

    private Context context = LeaveRequest.this;
    private String currentdate;
    private SharedPreferences loginPreferences;
    private String magr,mgrtoken;
    private String loginid;
    private String repotid,token;
    private SharedPreferences tokenPreferences;
    private NotificationPresenter notificationPresenter;


    private static final String TAG = "LeaveRequest";
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.leaverequest);


        preferencesDat = this.getSharedPreferences("FRDAT",0);

        tokenPreferences = this.getSharedPreferences("TOK",0);


        loginPreferences = this.getSharedPreferences("Login",0);



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


        magr = loginPreferences.getString("log","");


        mgrtoken = loginPreferences.getString("Mgrtoken","");


        loginid = loginPreferences.getString("Id","");

        repotid = loginPreferences.getString("ReptId","");

        token = tokenPreferences.getString("Token","");

        spinner1 = (MaterialSpinner) findViewById(R.id.spinner2);

        button = (Button) findViewById(R.id.button2);

        frm = (Button) findViewById(R.id.from);

        to = (Button) findViewById(R.id.upto);

        days = (TextView) findViewById(R.id.day);

        description = (EditText) findViewById(R.id.desc);


        retrofitBuild = new RetrofitBuild();

       leavePresenter = new LeavePresenter(this);

        notificationPresenter = new NotificationPresenter(this);

        calendar = Calendar.getInstance();


        status.add(0,"Leave Type");

        status.add("CL");
        status.add("PL");
        status.add("SL");


        spinner1.setItems(status);

        Calendar c = Calendar.getInstance();


        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        currentdate = df.format(c.getTime());


        progressDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        progressDialog.getProgressHelper().setBarColor(Color.parseColor("#2289DC"));
        progressDialog.setTitleText("Please  wait...");
        progressDialog.setCancelable(false);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int i1 = spinner1.getSelectedIndex();

                leavetype = status.get(i1).toString();

                frmdat = frm.getText().toString();

                todate = to.getText().toString();


                if(leavetype.equals("Leave Type")){

                    Toast.makeText(context,"please select leave type",Toast.LENGTH_SHORT).show();

                }
                else {

                    if(frmdat.equals("frm date")){


                        Toast.makeText(context,"please select from date",Toast.LENGTH_SHORT).show();

                    }
                    else {

                        if(todate.equals("to date")){

                            Toast.makeText(context,"please select to date",Toast.LENGTH_SHORT).show();


                        }
                        else {

                       if(description.getText().toString().equals("")){

                           Toast.makeText(context,"please enter resone",Toast.LENGTH_SHORT).show();

                       }


                       else {


                          leavePresenter.getData(currentdate,loginid,leavetype,frmdat,todate,days.getText().toString(),description.getText().toString(),repotid,"pending","pending",token,token);

                          progressDialog.show();


                       }


                        }




                    }

                }






            }
        });

        frm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dpd1 = DatePickerDialog.newInstance(LeaveRequest.this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

                dpd1.show(getFragmentManager(), "datePicker");
                dpd1.setAccentColor(getResources().getColor(R.color.DeepSkyBlue));

                preferencesDat.edit().clear().commit();

                preferencesDat.edit().putString("DATE","frdt").apply();


            }
        });

        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dpd1 = DatePickerDialog.newInstance(LeaveRequest.this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

                dpd1.show(getFragmentManager(), "datePicker");
                dpd1.setAccentColor(getResources().getColor(R.color.DeepSkyBlue));

                preferencesDat.edit().clear().commit();

                preferencesDat.edit().putString("DATE","todt").apply();


            }
        });




    }

    @Override
    public void onLoginComp() {

    }

    @Override
    public void onLoginErr(String s) {


        Toast.makeText(context,"server error",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLogin(LocationPojo locationPojo) {

      //  progressDialog.dismiss();

        if(locationPojo.getSuccess() == 1){


       //    Toast.makeText(context,"your request sent succcessfully",Toast.LENGTH_SHORT).show();

        notificationPresenter.getData(description.getText().toString(),mgrtoken,repotid,token,magr,leavetype,loginid);


        }
        else {


            Toast.makeText(context,"please try again later",Toast.LENGTH_SHORT).show();



        }


    }

    @Override
    public Observable<LocationPojo> getleave(String date, String logid, String leavetype, String lfrom, String lupto, String nofday, String levresone, String reptmgrid, String status, String remark,String usertoken,String mgrtoken) {
        return retrofitBuild.allApi().getleave(date,logid,leavetype,lfrom,lupto,nofday,levresone,reptmgrid,status,remark,usertoken,mgrtoken);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {


        int mon = monthOfYear + 1;

        String date = year + "/" + mon + "/" + dayOfMonth;

        String dat = preferencesDat.getString("DATE","");

        if(dat.equals("frdt")){

            frm.setText(date);

        }
        else {

            to.setText(date);

        }

        if(!frm.getText().toString().equals("frm date") && !to.getText().toString().equals("to date")){


            SimpleDateFormat dates = new SimpleDateFormat("MM/dd/yyyy");

            SimpleDateFormat dates1 = new SimpleDateFormat("yyyy/MM/dd");


            try {

                Date d1 = dates1.parse(frm.getText().toString());
                Date d2 = dates1.parse(to.getText().toString());


                String formattedDate = dates.format(d1);
                String toDate = dates.format(d2);


                Date date1 = dates.parse(formattedDate);
                Date date2 = dates.parse(toDate);


                long difference = Math.abs(date1.getTime() - date2.getTime());
                long differenceDates = difference / (24 * 60 * 60 * 1000);

                //Convert long to String
                String dayDifference = Long.toString(differenceDates);

                Log.d("HERE","HERE: " + dayDifference);

                days.setText(dayDifference);

              //  printDifference(date1, date2);

            } catch (ParseException e) {
                e.printStackTrace();
            }




        }


    }





    @Override
    public void onNLoginComplete() {

    }

    @Override
    public void onNLoginError(String s) {


        progressDialog.dismiss();
        Toast.makeText(context,"your request sent succcessfully",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNLogin(NotificationPojo notificationPojo) {

        progressDialog.dismiss();


        String mg = notificationPojo.getSuccess().toString();


        Log.d(TAG, "MSG: " + notificationPojo.getSuccess());


        if(mg.equals("1")){


            Toast.makeText(context,"your request sent succcessfully",Toast.LENGTH_SHORT).show();


        }
        else {


            Toast.makeText(context,"please try again later",Toast.LENGTH_SHORT).show();


        }


    }

    @Override
    public Observable<NotificationPojo> getConf(String desc, String token,String id,String utoken,String name,String type,String uid) {
        return retrofitBuild.allApi().getNotification(desc,token,id,utoken,name,type,uid);
    }
}
