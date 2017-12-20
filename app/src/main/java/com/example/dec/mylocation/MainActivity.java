package com.example.dec.mylocation;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.provider.Settings;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.evernote.android.job.JobRequest;
import com.example.dec.mylocation.BackTask.Notificationservice;
import com.example.dec.mylocation.BackTask.RetrofitBuild;
import com.example.dec.mylocation.custom.SweetAlertDialog;
import com.example.dec.mylocation.pojo.ContactPojo;
import com.example.dec.mylocation.pojo.LocationPojo;
import com.example.dec.mylocation.rximpl.ContactInterface;
import com.example.dec.mylocation.rximpl.ContactPresenter;
import com.example.dec.mylocation.rximpl.TestPresenter;
import com.example.dec.mylocation.rximpl.ViewContactInterface;
import com.example.dec.mylocation.rximpl.ViewContactPresenter;
import com.example.dec.mylocation.rximpl.VisitInterface;
import com.example.dec.mylocation.rximpl.VisitPresenter;
import com.example.dec.mylocation.util.NetworkUtil;
import com.example.dec.mylocation.util.ValidationUtils;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import rx.Observable;

public class MainActivity extends AppCompatActivity implements VisitInterface,ContactInterface{


    Button save,add,voewcont;

    EditText name,address,city,pincode,mobile,offtel,email, descrpt,state;


    private RetrofitBuild retrofitBuild;
    private VisitPresenter visitPresenter;

    ContactPresenter contactPresenter;


    ProgressDialog progressDialog;
    private Toolbar toolbar;
    private String formattedDate,formattime,id;

    private  SimpleDateFormat df ,df1;
    Calendar c;
    Context context = MainActivity.this;

    TextInputLayout cname,caddr,ccity,cpincode,cmobile,cofftel,cemail,description,stated;

    SharedPreferences preferences;

    Dialog dialog;
    private ValidationUtils validationUtils;

  private  MaterialSpinner regionSpinner;

    ArrayList<String> regions = new ArrayList<>();
    private String myregion;

    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


     //   LocationService lc = new LocationService(this);


        name = (EditText) findViewById(R.id.name);
        address = (EditText) findViewById(R.id.address);
        city = (EditText) findViewById(R.id.city);
        pincode = (EditText) findViewById(R.id.pin);
        mobile = (EditText) findViewById(R.id.mobile);
        offtel = (EditText) findViewById(R.id.tel);
        email = (EditText) findViewById(R.id.emailadd);
        descrpt = (EditText) findViewById(R.id.desc);
        state = (EditText) findViewById(R.id.state);



        scrollView = (ScrollView) findViewById(R.id.scroll1);


        cname = (TextInputLayout) findViewById(R.id.cname);

        caddr = (TextInputLayout) findViewById(R.id.caddress);
        ccity = (TextInputLayout) findViewById(R.id.ccity);
        cpincode = (TextInputLayout) findViewById(R.id.cpin);
      //  cmobile = (TextInputLayout) findViewById(R.id.cmobile);
        cofftel = (TextInputLayout) findViewById(R.id.ctell);
        cemail = (TextInputLayout) findViewById(R.id.cemail);
        description = (TextInputLayout) findViewById(R.id.ddname);
        stated = (TextInputLayout) findViewById(R.id.cstate);


        regionSpinner = (MaterialSpinner) findViewById(R.id.region);

        validationUtils = new ValidationUtils(this);

        save = (Button) findViewById(R.id.nxt);

        add = (Button) findViewById(R.id.add);

        voewcont = (Button) findViewById(R.id.viewcont);

        add.setVisibility(View.GONE);

        voewcont.setVisibility(View.GONE);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                preferences.edit().clear().apply();

                Intent intent = new Intent(MainActivity.this,Visit_Details.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.left_right,R.anim.right_left);

            }
        });

        preferences = this.getSharedPreferences("VST",0);

/*

        if(!preferences.getString("CNAM","").equals("")){

            id = getIntent().getExtras().getString("cid");
            name.setText(getIntent().getExtras().getString("cname"));
            address.setText(getIntent().getExtras().getString("cadd"));
            city.setText(getIntent().getExtras().getString("ccity"));
            pincode.setText(getIntent().getExtras().getString("cpin"));
          //  mobile.setText(getIntent().getExtras().getString("cmob"));
            offtel.setText(getIntent().getExtras().getString("cofftel"));
            email.setText(getIntent().getExtras().getString("cemail"));

            add.setVisibility(View.VISIBLE);
            voewcont.setVisibility(View.VISIBLE);

        }
*/



        regions.add(0,"Select region");

        regions.add("East");
        regions.add("West");
        regions.add("South");
        regions.add("North");

        //  ArrayAdapter purpose1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,purposestring);

        regionSpinner.setItems(regions);


        if(getIntent().getExtras()!= null){

            id = getIntent().getExtras().getString("cid");
            name.setText(getIntent().getExtras().getString("cname"));
            address.setText(getIntent().getExtras().getString("cadd"));
            city.setText(getIntent().getExtras().getString("ccity"));
            pincode.setText(getIntent().getExtras().getString("cpin"));
              state.setText(getIntent().getExtras().getString("cstate"));
            offtel.setText(getIntent().getExtras().getString("cofftel"));
            email.setText(getIntent().getExtras().getString("cemail"));
            descrpt.setText(getIntent().getExtras().getString("cdesc"));
            String regi = getIntent().getExtras().getString("cregion");

            for (int i = 0; i < regions.size(); i++) {

                if (regi.equals(regions.get(i))) ;
                {

                    regionSpinner.setSelectedIndex(i);

                }

            }


            add.setVisibility(View.VISIBLE);
            voewcont.setVisibility(View.VISIBLE);

        }



        retrofitBuild = new RetrofitBuild();

        visitPresenter = new VisitPresenter(this);

        contactPresenter = new ContactPresenter(this);


        progressDialog = new ProgressDialog(this);

        progressDialog.setTitle("Wait...");
        progressDialog.setCancelable(false);

       c = Calendar.getInstance();


        df = new SimpleDateFormat("yyyy-MM-dd");

        df1 = new SimpleDateFormat("HH:mm:ss");



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                OpenPopUp();

            }
        });


        voewcont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,ViewContact.class);
                intent.putExtra("ID",id);
                startActivity(intent);
                overridePendingTransition(R.anim.enter,R.anim.exit);

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                formattedDate = df.format(c.getTime());

                formattime = df1.format(c.getTime());

                int i1 = regionSpinner.getSelectedIndex();

                myregion = regions.get(i1).toString();


                System.out.println("Date:" +formattedDate );


                if(name.getText().toString().equals("")){

                    cname.setError("please enter customer name");
                    focusOnView(cname);
                }

                else {

                    cname.setErrorEnabled(false);

                  if(address.getText().toString().equals("")){

                      caddr.setError("please enter address ");
                      focusOnView(caddr);
                  }
                  else {

                      caddr.setErrorEnabled(false);

                      if(state.getText().toString().equals("")){

                        stated.setError("please enter state");
                          focusOnView(stated);
                      }
                      else {

                          stated.setErrorEnabled(false);

                          if (city.getText().toString().equals("")) {


                              ccity.setError("please enter city");
                              focusOnView(ccity);

                          } else {

                              ccity.setErrorEnabled(false);

                              if (myregion.equals("Select region")) {

                                  focusOnView(regionSpinner);
                                  Toast.makeText(context, "please select region", Toast.LENGTH_SHORT).show();

                              } else {


                                  if (pincode.getText().toString().equals("")) {


                                      cpincode.setError("please enter pincode");
                                      focusOnView(cpincode);

                                  } else {

                                      cpincode.setErrorEnabled(false);


                                      if (offtel.getText().toString().equals("")) {


                                          cofftel.setError("please enter mobile number");
                                          focusOnView(cofftel);

                                      } else {

                                          if (!validationUtils.isValidMobile(offtel.getText().toString())) {

                                              cofftel.setError("please enter valid mobile number");
                                              focusOnView(cofftel);

                                          } else {

                                              cofftel.setErrorEnabled(false);


                                              if (email.getText().toString().equals("")) {


                                                  cemail.setError("please enter email id");

                                                  focusOnView(cemail);

                                              } else {

                                                  if (!validationUtils.isValidEmail(email.getText().toString())) {

                                                      cemail.setError("please enter valid email id");
                                                      focusOnView(cemail);

                                                  } else {

                                                      cemail.setErrorEnabled(false);

                                                      if (descrpt.getText().toString().equals("")) {


                                                          description.setError("please enter description");
                                                          focusOnView(description);
                                                      } else {

                                                          description.setErrorEnabled(false);

                                                          if (NetworkUtil.getConnectivityStatus(MainActivity.this.getApplicationContext()) != 0) {

                                                              visitPresenter.getData(id,name.getText().toString(), address.getText().toString(),state.getText().toString(), city.getText().toString(),myregion,pincode.getText().toString(), offtel.getText().toString(),descrpt.getText().toString(),email.getText().toString(), formattedDate, formattime);

                                                              progressDialog.show();
                                                          } else {

                                                              new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE)
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





                }




            }
        });









        // scheduleAdvancedJob1();

       // schedulePeriodicJob();



 //        getaddress(lat1,lng1);



/*

        Location location = lc.getLocation();

        final String stringLatitude = String.valueOf(location.getLatitude());
        final String stringLongtitude = String.valueOf(location.getLongitude());


        final Double lat1 = Double.valueOf(stringLatitude);

        final Double lng1 = Double.valueOf(stringLongtitude);



        System.out.println("Loc:" + lat1 + lng1);


        getaddress(lat1,lng1);
*/




    }




    private void focusOnView(final View view){
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                scrollView.scrollTo(0, view.getTop());
            }
        });
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

         dialog = new Dialog(MainActivity.this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animationdialog;


        // Include dialog.xml file
        dialog.setContentView(R.layout.popup);

        final EditText cid,cname,cemail,cdesg,cmobile,cmobile2;


        cname = (EditText) dialog.findViewById(R.id.name);
        cmobile = (EditText) dialog.findViewById(R.id.mobile);
        cmobile2 = (EditText) dialog.findViewById(R.id.mobile2);
        cemail = (EditText) dialog.findViewById(R.id.email);
        cdesg = (EditText) dialog.findViewById(R.id.desig);

        Button button = (Button)dialog.findViewById(R.id.save);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(cname.getText().toString().equals("")){

                   Toast.makeText(MainActivity.this,"plz enter name",Toast.LENGTH_SHORT).show();


                }
                else {

                    if(cdesg.getText().toString().equals("")){

                        Toast.makeText(MainActivity.this,"plz enter desigination",Toast.LENGTH_SHORT).show();

                    }
                    else {

                        if(cmobile.getText().toString().equals("")){


                            Toast.makeText(MainActivity.this,"plz enter mobile number",Toast.LENGTH_SHORT).show();

                        }
                        else {

                            if (!validationUtils.isValidMobile(cmobile.getText().toString())) {

                                Toast.makeText(MainActivity.this, "Please enter valid mobile number", Toast.LENGTH_SHORT).show();


                            }
                            else {

                               if(cemail.getText().toString().equals("")){

                                   Toast.makeText(MainActivity.this, "Please enter your email id", Toast.LENGTH_SHORT).show();

                               }
                                else {

                                   if (!validationUtils.isValidEmail(cemail.getText().toString())) {

                                       Toast.makeText(MainActivity.this, "Please enter valid email id", Toast.LENGTH_SHORT).show();


                                   }
                                   else {


                                       if (NetworkUtil.getConnectivityStatus(MainActivity.this.getApplicationContext()) != 0) {


                                           contactPresenter.getData(id,cname.getText().toString(),cdesg.getText().toString(),cmobile.getText().toString(),cmobile2.getText().toString(),cemail.getText().toString());

                                           progressDialog.show();


                                       }
                                       else {

                                           new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE)
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
        });


        // Set dialog title
//            dialog.setTitle("Scan this code to redeem offer");

        // set values for custom dialog components - text, image and button
        dialog.show();


    }



    public void getaddress(Double lat1,Double lng1){

        Geocoder coder = new Geocoder(this);
        List<Address> address = null;
        
        try {
            
            address = coder.getFromLocation(lat1,lng1,1);
            
            
        }catch (Exception e){
            
            e.printStackTrace();
            
        }
        
        
        if(address!=null){



           // String address1 = address.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

            String citytext = address.get(0).getLocality();
            String state = address.get(0).getAdminArea();
            String country = address.get(0).getCountryName();
            String postalCode = address.get(0).getPostalCode();
            String knownName = address.get(0).getFeatureName();
            String subLocality = address.get(0).getSubLocality();
            

            System.out.println("Loc:" + subLocality);

            
        }

        
    }






    @Override
    public void onLoginComplete() {



    }

    @Override
    public void onLoginError(String s) {

        progressDialog.dismiss();

        Toast.makeText(MainActivity.this,"server error",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLogin(LocationPojo locationPojo) {

        progressDialog.dismiss();

        if(locationPojo.getSuccess() == 1){

            Toast.makeText(MainActivity.this,locationPojo.getMessage(),Toast.LENGTH_SHORT).show();

           Intent intent = new Intent(MainActivity.this,Visit_Details.class);
           startActivity(intent);
            finish();
            overridePendingTransition(R.anim.left_right,R.anim.right_left);

        }
        else {

            Toast.makeText(MainActivity.this,locationPojo.getMessage(),Toast.LENGTH_SHORT).show();



        }

    }

    @Override
    public Observable<LocationPojo> getConf(String cid,String name, String addr,String State1, String city,String region ,String pincode, String officetel, String email,String desc,String date,String time) {
        return retrofitBuild.allApi().getData(cid,name,addr,State1,city,region,pincode,officetel,email,desc,date,time);
    }


    @Override
    public void onCLoginComplete() {


        progressDialog.dismiss();
    }

    @Override
    public void onCLoginError(String s) {

        progressDialog.dismiss();

    }

    @Override
    public void onCLogin(LocationPojo locationPojo) {

        if(locationPojo.getSuccess() == 1){

            dialog.dismiss();

            Toast.makeText(context,"Contact save",Toast.LENGTH_SHORT).show();

        }
        else {

            dialog.dismiss();

            Toast.makeText(context,"Fail to save",Toast.LENGTH_SHORT).show();


        }


    }

    @Override
    public Observable<LocationPojo> getCont(String id, String name, String desg, String mobile,String mobile2, String email) {
        return retrofitBuild.allApi().getContact(id,name,desg,mobile,mobile2,email);
    }

    @Override
    public void onBackPressed() {


        Intent intent = new Intent(MainActivity.this,Visit_Details.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.left_right,R.anim.right_left);
        preferences.edit().clear().apply();

        finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        preferences.edit().clear().apply();


    }

}
