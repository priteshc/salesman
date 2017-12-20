package com.example.dec.mylocation;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dec.mylocation.BackTask.RetrofitBuild;
import com.example.dec.mylocation.Interface.ActionComplete;
import com.example.dec.mylocation.custom.SweetAlertDialog;
import com.example.dec.mylocation.pojo.ContactPersonPojo;
import com.example.dec.mylocation.pojo.LocationPojo;
import com.example.dec.mylocation.pojo.ProductDetailsPojo;
import com.example.dec.mylocation.pojo.PurposePojo;
import com.example.dec.mylocation.pojo.StatusPojo;
import com.example.dec.mylocation.pojo.VisitDetailsPojo;
import com.example.dec.mylocation.rximpl.AddVisitInterface;
import com.example.dec.mylocation.rximpl.AddVisitPresenter;
import com.example.dec.mylocation.rximpl.ContactDetailInterface;
import com.example.dec.mylocation.rximpl.ContactDetailsPresenter;
import com.example.dec.mylocation.rximpl.ProductInterface;
import com.example.dec.mylocation.rximpl.ProductPresenter;
import com.example.dec.mylocation.rximpl.PurposeDetailInterface;
import com.example.dec.mylocation.rximpl.PurposeDetailsPresenter;
import com.example.dec.mylocation.rximpl.StatusDetailInterface;
import com.example.dec.mylocation.rximpl.StatusDetailsPresenter;
import com.example.dec.mylocation.rximpl.VisitDetailInterface;
import com.example.dec.mylocation.rximpl.VisitDetailsPresenter;
import com.example.dec.mylocation.util.NetworkUtil;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.StreamHandler;

import rx.Observable;

/**
 * Created by pritesh on 5/26/2017.
 */

public class AddVisit extends AppCompatActivity  implements DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener,VisitDetailInterface,AddVisitInterface,ContactDetailInterface,PurposeDetailInterface,StatusDetailInterface,ProductInterface {

    TextView tim, dat;

    Calendar calendar;

    MaterialSpinner spinner, spinner1, contactper, pronname, purpose1;

    private RetrofitBuild retrofitBuild;
    private VisitDetailsPresenter detailsPresenter;
    private AddVisitPresenter addVisitPresenter;
    private ContactDetailsPresenter contactDetailsPresenter;

    private PurposeDetailsPresenter purposeDetailsPresenter;

    private StatusDetailsPresenter statusDetailsPresenter;

    private ProductPresenter productPresenter;

    AqunomicDatabase database;
    Cursor cursor1,cursor2;

    Toolbar toolbar;
    private SweetAlertDialog progressDialog;

    ArrayList<String> strings = new ArrayList<>();

    ArrayList<String> status = new ArrayList<>();

    ArrayList<String> statusid = new ArrayList<>();


    ArrayList<String> purposestring = new ArrayList<>();

    ArrayList<String> purposeid = new ArrayList<>();


    ArrayList<String> cname = new ArrayList<>();

    ArrayList<String> pname = new ArrayList<>();


    EditText desc, remark;

    Button next, closedialog, flwdate, flwtime,fromtime,totime;

    MaterialSpinner materialSpinner;


    String mystatus, customer, contact, prodname, purpose,visitid;

    private TextInputLayout dest;

    Context context = AddVisit.this;

    TextView customertext;
    private Dialog dialog;
    private ListView listView;
    private ImageButton close;
    private ArrayAdapter lis;
    private EditText serchtext,offdesc;

    private String vdate, vtime, vpurpose, vcustomer, vcontact, vproduct, vdescription, vstatus, vremarks,cid;

    private SharedPreferences preferencesDat,preferencesTime,officetime;
    private SharedPreferences loginPreferences;
    private String magr,loginid;

    private LinearLayout ll1,ll2;
    private String diff;

    int pos;
    private String dat1;
    private String datt;
    private String time2;
    private String datt1;
    private String myhours;
    private String region;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.addvisit);

        preferencesDat = this.getSharedPreferences("DAT",0);

        preferencesTime = this.getSharedPreferences("TIM",0);


        loginPreferences = this.getSharedPreferences("Login",0);

        officetime = this.getSharedPreferences("Office",0);

    //    officetime = this.getSharedPreferences("Office",0);


        ll1 = (LinearLayout) findViewById(R.id.ll1);

        ll2 = (LinearLayout) findViewById(R.id.ll2);


        magr = loginPreferences.getString("log","");

        loginid = loginPreferences.getString("Id","");

        region = loginPreferences.getString("Region","");



        tim = (TextView) findViewById(R.id.tim);

        dat = (TextView) findViewById(R.id.dat);

        customertext = (TextView) findViewById(R.id.customer);

        desc = (EditText) findViewById(R.id.desc);

        remark = (EditText) findViewById(R.id.remark1);

        dest = (TextInputLayout) findViewById(R.id.ddname);

        spinner1 = (MaterialSpinner) findViewById(R.id.spinner2);

        purpose1 = (MaterialSpinner) findViewById(R.id.purpose);


        contactper = (MaterialSpinner) findViewById(R.id.contactper);

        pronname = (MaterialSpinner) findViewById(R.id.productname);


        //   actionComplete = new AddVisit();


        database = new AqunomicDatabase(this);


        next = (Button) findViewById(R.id.nxt);

        flwdate = (Button) findViewById(R.id.flwdate);

        flwtime = (Button) findViewById(R.id.flwtime);


        fromtime = (Button) findViewById(R.id.fromtime);

        totime = (Button) findViewById(R.id.totime);

        offdesc = (EditText) findViewById(R.id.desc1);


        calendar = Calendar.getInstance();


        retrofitBuild = new RetrofitBuild();

        detailsPresenter = new VisitDetailsPresenter(this);

        addVisitPresenter = new AddVisitPresenter(this);

        contactDetailsPresenter = new ContactDetailsPresenter(this);

        purposeDetailsPresenter = new PurposeDetailsPresenter(this);

        statusDetailsPresenter = new StatusDetailsPresenter(this);

        productPresenter = new ProductPresenter(this);

        if (getIntent().getExtras() != null) {


            dat.setText(getIntent().getExtras().getString("date"));
            tim.setText(getIntent().getExtras().getString("time"));
            vpurpose = getIntent().getExtras().getString("purpose");
            customertext.setText(getIntent().getExtras().getString("customer"));
            vcontact = getIntent().getExtras().getString("contact");
            vproduct = getIntent().getExtras().getString("product");
            desc.setText(getIntent().getExtras().getString("desc"));
            vstatus = getIntent().getExtras().getString("status");
            remark.setText(getIntent().getExtras().getString("remark"));
            flwdate.setText(getIntent().getExtras().getString("flwdate"));
            flwtime.setText(getIntent().getExtras().getString("flwtime"));
            visitid = getIntent().getExtras().getString("vid");


        }


        progressDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        progressDialog.getProgressHelper().setBarColor(Color.parseColor("#2289DC"));
        progressDialog.setTitleText("Please  wait...");
        progressDialog.setCancelable(false);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        purpose1.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {


             Toast.makeText(AddVisit.this,String.valueOf(position),Toast.LENGTH_SHORT).show();

                if(position == 4){

                  ll1.setVisibility(View.GONE);
                   ll2.setVisibility(View.VISIBLE);
                    tim.setVisibility(View.GONE);

                    tim.setText(String.valueOf(position));

                    pos = position;


                }
                else {

                    ll1.setVisibility(View.VISIBLE);
                    ll2.setVisibility(View.GONE);

                    tim.setVisibility(View.VISIBLE);

                    tim.setText("select time");

                    pos= position;

                }


            }
        });

      /*  purposestring.add(0,"purpose of visit");

        purposestring.add("Sales");
        purposestring.add("Marketing");
        purposestring.add("Collection");*/

        //  ArrayAdapter purpose1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,purposestring);

        // purpose1.setItems(purposestring);


       /* status.add(0,"Select status");

        status.add("Completed");
        status.add("Pending");
        status.add("Other");

        ArrayAdapter yeradapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,status);

        spinner1.setItems(status);
*/
/*
        pname.add(0, "Select product");

        pname.add("Crm");
        pname.add("taxx");
        pname.add("tally");

        ArrayAdapter yeradapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pname);

        pronname.setItems(pname);*/


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  Intent intent = new Intent(AddVisit.this,AllVisit.class);
                startActivity(intent);*/
                finish();
             //   overridePendingTransition(R.anim.left_right,R.anim.right_left);

            }
        });


        if (getIntent().getExtras() != null) {


           /* for (int i = 0; i < purposestring.size(); i++) {

                if (vpurpose.equals(purposestring.get(i))) ;
                {

                    purpose1.setSelectedIndex(i);

                }


            }
*/

            /*for (int i = 0; i < status.size(); i++) {

                if (vstatus.equals(status.get(i))) ;
                {

                    spinner1.setSelectedIndex(i);

                }


            }*/


          /*  for (int i = 0; i < pname.size(); i++) {

                if (vproduct.equals(pname.get(i))) ;
                {

                    pronname.setSelectedIndex(i);

                }


            }*/


        }



/*

        if( getIntent().getExtras() != null)
        {

            tim.setText(getIntent().getExtras().getString("time"));

            //do here
        }
*/


        database.open();

        database.deleteitm();
        database.deleteitm1();

        database.close();


        detailsPresenter.getData(region);

        progressDialog.show();


        customertext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OpenPopUp();
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                customer = customertext.getText().toString();


                int i1 = spinner1.getSelectedIndex();

                mystatus = status.get(i1).toString();


                int i2 = contactper.getSelectedIndex();

                contact = cname.get(i2).toString();


                int i3 = pronname.getSelectedIndex();

                prodname = pname.get(i3).toString();


                int i4 = purpose1.getSelectedIndex();

                purpose = purposestring.get(i4).toString();



                if(pos == 4){


                    if (dat.getText().toString().equals("select date")) {

                        Toast.makeText(context, "please select date", Toast.LENGTH_SHORT).show();

                    }
                    else {

                        if (fromtime.getText().toString().equals("From time")) {

                            Toast.makeText(context, "please select from time", Toast.LENGTH_SHORT).show();

                        }
                        else {

                            if (totime.getText().toString().equals("To time")) {

                                Toast.makeText(context, "please select to time", Toast.LENGTH_SHORT).show();

                            }

                            else {

                                if (offdesc.getText().toString().equals("")) {

                                    Toast.makeText(context, "please enter description", Toast.LENGTH_SHORT).show();

                                }

                                else {


                                    dat1 = dat.getText().toString();

                                    String   time1 = fromtime.getText().toString();

                                    String[] separated = time1.split(":");

                                    int i = Integer.parseInt(separated[0]);

                                    if(i> 12){

                                        try {

                                            DateFormat f1 = new SimpleDateFormat("HH:mm:ss"); //HH for hour of the day (0 - 23)
                                            Date d = f1.parse(time1);
                                            DateFormat f2 = new SimpleDateFormat("hh:mm:ss");
                                        String  dd  =  f2.format(d);

                                            datt = dat1 + " " + dd;


                                        }
                                        catch (Exception e){

                                            e.printStackTrace();
                                        }
                                    }

                                    else {



                                        datt = dat1 + " " + time1;

                                    }


                                       time2 = totime.getText().toString();

                                    String[] separated1 = time2.split(":");

                                    int ii = Integer.parseInt(separated1[0]);

                                    if(ii>12){


                                        try {

                                            DateFormat f1 = new SimpleDateFormat("HH:mm:ss"); //HH for hour of the day (0 - 23)
                                            Date d = f1.parse(time2);
                                            DateFormat f2 = new SimpleDateFormat("hh:mm:ss");
                                            String  dd  =  f2.format(d);

                                            datt1 = dat1 + " " + dd;



                                        }
                                        catch (Exception e){

                                            e.printStackTrace();
                                        }

                                    }

                                    else {

                                        datt1 = dat1 + " " + time2;


                                    }
                                    Log.d("Data:", ""+datt1);


                                    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


                                    try
                                    {



                                        Date Date1 = format1.parse(datt);

                                        Date Date2 = format1.parse(datt1);


                                        long diff = Date2.getTime() - Date1.getTime();
                                        long diffSeconds = diff / 1000 % 60;
                                        long diffMinutes = diff / (60 * 1000) % 60;
                                        long diffHours = diff / (60 * 60 * 1000);
                                        int diffInDays = (int) ((Date2.getTime() - Date1.getTime()) / (1000 * 60 * 60 * 24));


                                        myhours = Long.toString(diffHours);

                                      String  mymin = Long.toString(diffMinutes);


                                        Log.d("Data1:", ""+myhours);


                                    }
                                    catch (Exception e)
                                    {
                                        e.printStackTrace();
                                    }



                                    if (NetworkUtil.getConnectivityStatus(AddVisit.this.getApplicationContext()) != 0) {


                                       addVisitPresenter.getData(visitid, dat.getText().toString(), "",String.valueOf(i4),  "",  "", "", "", "", offdesc.getText().toString(), "", "", magr, loginid,fromtime.getText().toString(),totime.getText().toString(),myhours);

                                        progressDialog.show();


                                    } else {

                                        new SweetAlertDialog(AddVisit.this, SweetAlertDialog.WARNING_TYPE)
                                                .setTitleText("Network")
                                                .setContentText("Please check internet connection")
                                                .setConfirmText("Ok")
                                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                    @Override
                                                    public void onClick(SweetAlertDialog sweetAlertDialog) {

                                                        sweetAlertDialog.dismiss();

                                                    }
                                                }).show();


                                    }





                                }




                            }



                        }



                    }




                }

                else
                    {

                if (dat.getText().toString().equals("select date")) {

                    Toast.makeText(context, "please select date", Toast.LENGTH_SHORT).show();

                } else {

                    if (tim.getText().toString().equals("select time")) {

                        Toast.makeText(context, "please select time", Toast.LENGTH_SHORT).show();

                    } else {


                        if (purpose.equals("purpose of visit")) {


                            Toast.makeText(context, "please select visit purpose", Toast.LENGTH_SHORT).show();


                        } else {


                            if (customer.equals("Select customer")) {

                                Toast.makeText(context, "please select customer", Toast.LENGTH_SHORT).show();

                            } else {


                                if (contact.equals("Select contact")) {

                                    Toast.makeText(context, "please select contact person", Toast.LENGTH_SHORT).show();

                                } else {

                                    if (prodname.equals("Select product")) {

                                        Toast.makeText(context, "please select product name", Toast.LENGTH_SHORT).show();

                                    } else {


                                        if (desc.getText().toString().equals("")) {

                                            dest.setError("please enter description");


                                        } else {

                                            dest.setErrorEnabled(false);

                                            if (mystatus.equals("Select status")) {

                                                Toast.makeText(context, "please select status", Toast.LENGTH_SHORT).show();

                                            } else {

                                                //  customer = strings.get(i).toString();


                                                if (NetworkUtil.getConnectivityStatus(AddVisit.this.getApplicationContext()) != 0) {


                                                    addVisitPresenter.getData(visitid, dat.getText().toString(), tim.getText().toString(), String.valueOf(i4), String.valueOf(i1), String.valueOf(i3), desc.getText().toString(), cid, contact, remark.getText().toString(), flwdate.getText().toString(), flwtime.getText().toString(), magr, loginid,"","","");

                                                    progressDialog.show();


                                                } else {

                                                    new SweetAlertDialog(AddVisit.this, SweetAlertDialog.WARNING_TYPE)
                                                            .setTitleText("Network")
                                                            .setContentText("Please check internet connection")
                                                            .setConfirmText("Ok")
                                                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                                @Override
                                                                public void onClick(SweetAlertDialog sweetAlertDialog) {

                                                                    sweetAlertDialog.dismiss();

                                                                }
                                                            }).show();


                                                }

                                            }

                                        }

                                    }
                                }

                            }

                        }


                    }

                }
                }

            }
        });


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, AllVisit.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.left_right, R.anim.right_left);

            }
        });


        tim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TimePickerDialog tpd = TimePickerDialog.newInstance(
                        AddVisit.this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND), true
                );

                tpd.show(getFragmentManager(), "timePicker");

                tpd.setAccentColor(getResources().getColor(R.color.DeepSkyBlue));

                preferencesTime.edit().clear().commit();

            }
        });


        flwtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TimePickerDialog tpd1 = TimePickerDialog.newInstance(
                        AddVisit.this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND), true
                );

                tpd1.show(getFragmentManager(), "timePicker");

                tpd1.setAccentColor(getResources().getColor(R.color.DeepSkyBlue));

                preferencesTime.edit().putString("TIME","tp").apply();


            }
        });


        fromtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TimePickerDialog tpd2 = TimePickerDialog.newInstance(
                        AddVisit.this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND), true
                );

                tpd2.show(getFragmentManager(), "timePicker");
                tpd2.setAccentColor(getResources().getColor(R.color.DeepSkyBlue));



                officetime.edit().clear().commit();


            }
        });

        totime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TimePickerDialog tpd3 = TimePickerDialog.newInstance(
                        AddVisit.this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND), true
                );

                tpd3.show(getFragmentManager(), "timePicker");
                tpd3.setAccentColor(getResources().getColor(R.color.DeepSkyBlue));


                officetime.edit().putString("OFFTIME","off").apply();

            }
        });




        dat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DatePickerDialog dpd = DatePickerDialog.newInstance(AddVisit.this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

                dpd.show(getFragmentManager(), "datePicker");
                dpd.setAccentColor(getResources().getColor(R.color.DeepSkyBlue));

                preferencesDat.edit().clear().commit();



            }
        });


        flwdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DatePickerDialog dpd1 = DatePickerDialog.newInstance(AddVisit.this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

                dpd1.show(getFragmentManager(), "datePicker");
                dpd1.setAccentColor(getResources().getColor(R.color.DeepSkyBlue));

                preferencesDat.edit().putString("DATE","dt").apply();


            }
        });


    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {


        int mon = monthOfYear + 1;

        String date = year + "-" + mon + "-" + dayOfMonth;


        if(preferencesDat.contains("DATE")){


            flwdate.setText(date);
        }
        else {

            dat.setText(date);

        }
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {


        String time = hourOfDay + ":" + minute + ":" + second;

        if(preferencesTime.contains("TIME")){


            flwtime.setText(time);
        }
        else {

            tim.setText(time);

        }


        if(officetime.contains("OFFTIME")){


            totime.setText(time);
        }
        else {

            fromtime.setText(time);

        }






    }

    @Override
    public void onLoginComplete() {

        // strings.add(0,"Select customer");

        ArrayAdapter yeradapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings);

        //  spinner.setAdapter(yeradapter);

        //  materialSpinner.setItems(strings);

        //  searchableSpinner.setAdapter(yeradapter);

        lis = new ArrayAdapter(this, android.R.layout.simple_list_item_1, strings);

        contactDetailsPresenter.getData();

    }

    @Override
    public void onLoginError(String s) {

        progressDialog.dismiss();


    }

    @Override
    public void onLoginComp() {

    }

    @Override
    public void onLoginErr(String s) {

        progressDialog.dismiss();

        Toast.makeText(this, "server error", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onLogin(LocationPojo locationPojo) {

        progressDialog.dismiss();

        if (locationPojo.getSuccess() == 1) {


            Toast.makeText(this, "save successfully", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(AddVisit.this, AllVisit.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.left_right, R.anim.right_left);


        } else {


            Toast.makeText(this, "plz check your network connection and try again", Toast.LENGTH_SHORT).show();


        }

    }

    @Override
    public Observable<LocationPojo> getvisit(String visitid,String date, String time, String purposeid,String statusid, String prodid, String desc, String custid, String contname, String remark, String fdate,String ftime,String logname,String logid,String frmtime,String ttime,String hrs) {
        return retrofitBuild.allApi().getVisit(visitid,date, time, purposeid, statusid, prodid, desc,custid,contname, remark,fdate,ftime,logname,logid,frmtime,ttime,hrs);
    }

    @Override
    public void onLogin(VisitDetailsPojo visitDetailsPojo) {


        if (visitDetailsPojo.getSuccess() == 1) {

            database.open();

            for (int i = 0; i < visitDetailsPojo.getData().size(); i++) {


                strings.add(visitDetailsPojo.getData().get(i).getCustName());

                database.createentry(visitDetailsPojo.getData().get(i).getCustName(),visitDetailsPojo.getData().get(i).getCustId());


            }

        }

        database.close();

    }



    @Override
    public Observable<VisitDetailsPojo> getVisitData(String reg) {
        return retrofitBuild.allApi().getVisitdata(reg);
    }

    @Override
    public void onCLoginComplete() {



     //   progressDialog.dismiss();


      //7  ArrayAdapter yeradapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,cname);

      //  contactper.setItems(cname);


      /*  if(vcustomer != null && !vcustomer.isEmpty()){

            customertext.setText(vcustomer);

        }

        if(vcontact != null && !vcontact.isEmpty()) {

            for(int i =0;i<cname.size();i++){


                if(vcontact.equals(cname.get(i)))
                {


                    contactper.setSelectedIndex(i);

                }

            }




        }
*/

        if(cname.size() > 0){


            purposeDetailsPresenter.getData();

        }

        }

    @Override
    public void onCLoginError(String s) {


        progressDialog.dismiss();

    }

    @Override
    public void onCLogin(ContactPersonPojo contactPersonPojo) {

        database.open();

        if(contactPersonPojo.getSuccess() == 1) {


           // database.deleteitm1();


            for (int i = 0; i < contactPersonPojo.getData().size(); i++) {


                cname.add(contactPersonPojo.getData().get(i).getPersonName());

                database.contactentry(contactPersonPojo.getData().get(i).getPersonName(), contactPersonPojo.getData().get(i).getCustId());

            }

            database.close();

            cname.add(0,"Select contact");
            contactper.setItems(cname);


        }
        }

    @Override
    public Observable<ContactPersonPojo> getAllContact() {
        return retrofitBuild.allApi().getAllContact();
    }


    private void OpenPopUp() {
        //Find screen size
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        int smallerDimension = width < height ? width : height;
        smallerDimension = smallerDimension * 3 / 4;

        dialog = new Dialog(AddVisit.this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //  dialog.getWindow().getAttributes().windowAnimations = R.style.animationdialog;

        dialog.setContentView(R.layout.customer_popup);


        listView = (ListView) dialog.findViewById(R.id.listItems);

        close = (ImageButton) dialog.findViewById(R.id.action_empty_btn);

        closedialog = (Button) dialog.findViewById(R.id.close);


        listView.setAdapter(lis);


        serchtext = (EditText) dialog.findViewById(R.id.searchTextView);


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                serchtext.getText().clear();
                ((ArrayAdapter) listView.getAdapter()).getFilter().filter("");


            }
        });


        closedialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

            }
        });

        serchtext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                ((ArrayAdapter) listView.getAdapter()).getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {



            }
        });


        //   searchView = (SearchView) dialog.findViewById(R.id.search);

        //




        //  searchView.setOnQueryTextListener(this);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedFromList =(String) (listView.getItemAtPosition(position));

                ((ArrayAdapter) listView.getAdapter()).getFilter().filter("");


                customertext.setText(selectedFromList);

                Toast.makeText(AddVisit.this,selectedFromList,Toast.LENGTH_SHORT).show();

                cname.clear();


                database.open();

                cursor1 = database.getdata(selectedFromList);

                int i = 0;




                for(cursor1.moveToFirst();!cursor1.isAfterLast();cursor1.moveToNext()){


                     i = cursor1.getCount();

                    System.out.println("CT:" + i);

                    if(i> 0) {

                        cid = cursor1.getString(cursor1.getColumnIndex(database.KEY_CUSTID));
                    }
                  // cname.add(cursor1.getString(cursor1.getColumnIndex(database.KEY_CONTACT)));

                }

                cursor2 = database.getcontact(cid);


                for(cursor2.moveToFirst();!cursor2.isAfterLast();cursor2.moveToNext()){


                    i = cursor2.getCount();

                    System.out.println("CT:" + i);

                    if(i> 0) {

                        cname.add(cursor2.getString(cursor2.getColumnIndex(database.KEY_NAME)));
                    }

                }

                database.close();


                cname.add(0,"Select contact");


                contactper.setItems(cname);


                dialog.dismiss();


            }
        });


        // Include dialog.xml file




        // Set dialog title
//            dialog.setTitle("Scan this code to redeem offer");

        // set values for custom dialog components - text, image and button
        dialog.show();


    }


    @Override
    public void onPComplete() {


        if(purposestring.size() > 0){


            statusDetailsPresenter.getData();
        }


    }

    @Override
    public void onPError(String s) {

        progressDialog.dismiss();

    }

    @Override
    public void onPurpose(PurposePojo visitDetailsPojo) {

      //  progressDialog.dismiss();

        if(visitDetailsPojo.getSuccess() == 1){



          for(int i = 0 ;i < visitDetailsPojo.getData().size();i++){

              purposestring.add(visitDetailsPojo.getData().get(i).getName());
              purposeid.add(visitDetailsPojo.getData().get(i).getPurposeId());
          }

            purposestring.add(0,"purpose of visit");

            purpose1.setItems(purposestring);

        }


    }

    @Override
    public Observable<PurposePojo> getPurposeData() {
        return retrofitBuild.allApi().getAllPurpose();
    }

    @Override
    public void onSComplete() {


        if(status.size()> 0 ){

            productPresenter.getData("Water treatment");

        }

    }

    @Override
    public void onSError(String s) {

        progressDialog.dismiss();
    }

    @Override
    public void onStatus(StatusPojo visitDetailsPojo) {


     //   progressDialog.dismiss();

        if(visitDetailsPojo.getSuccess() == 1){

            for(int i = 0 ;i < visitDetailsPojo.getData().size();i++){

                status.add(visitDetailsPojo.getData().get(i).getName());
                statusid.add(visitDetailsPojo.getData().get(i).getStatusId());
            }

            status.add(0,"Select status");

            spinner1.setItems(status);
        }

        }


    @Override
    public Observable<StatusPojo> getStatusData() {
        return retrofitBuild.allApi().getAllStatus();
    }

    @Override
    public void onProdComplete() {

    }

    @Override
    public void onProdError(String s) {


        progressDialog.dismiss();
    }

    @Override
    public void onProduct(ProductDetailsPojo aquLoginPojo) {

        progressDialog.dismiss();

        if(aquLoginPojo.getSuccess() == 1){


         for(int i= 0; i< aquLoginPojo.getData().size();i++){

           pname.add(aquLoginPojo.getData().get(i).getProductName());

         }

            pname.add(0, "Select product");

            pronname.setItems(pname);

        }


        for (int i = 0; i < purposestring.size(); i++) {

            if (vpurpose.equals(purposestring.get(i))) ;
            {

                purpose1.setSelectedIndex(i);

            }
        }

        for (int i = 0; i < status.size(); i++) {

            if (vstatus.equals(status.get(i))) ;
            {

                spinner1.setSelectedIndex(i);

            }
        }

        for (int i = 0; i < pname.size(); i++) {

            if (vproduct.equals(pname.get(i))) ;
            {

                pronname.setSelectedIndex(i);

            }

        }

        for(int i =0;i<cname.size();i++){

            if(vcontact.equals(cname.get(i)))
            {

                contactper.setSelectedIndex(i);
            }

        }

    }

    @Override
    public Observable<ProductDetailsPojo> getProduct(String cate) {
        return retrofitBuild.allApi().getAllproduct(cate);
    }
}
