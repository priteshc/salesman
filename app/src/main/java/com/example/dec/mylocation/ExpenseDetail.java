package com.example.dec.mylocation;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dec.mylocation.BackTask.RetrofitBuild;
import com.example.dec.mylocation.custom.SweetAlertDialog;
import com.example.dec.mylocation.pojo.LocationPojo;
import com.example.dec.mylocation.pojo.VisitDetailsPojo;
import com.example.dec.mylocation.rximpl.ExpenseInterface;
import com.example.dec.mylocation.rximpl.ExpensePresenter;
import com.example.dec.mylocation.rximpl.VisitDetailInterface;
import com.example.dec.mylocation.rximpl.VisitDetailsPresenter;
import com.example.dec.mylocation.util.NetworkUtil;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

import rx.Observable;

/**
 * Created by pritesh on 6/17/2017.
 */

public class ExpenseDetail extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,VisitDetailInterface,ExpenseInterface {

 private TextView date,companyname;

    private EditText distance,amount,description;

    private Button save,closedialog;

    private MaterialSpinner conmtactperson;

    Calendar calendar;
    private RetrofitBuild retrofitBuild;
    private VisitDetailsPresenter detailsPresenter;
    private SweetAlertDialog progressDialog;
    private Toolbar toolbar;
    private ExpensePresenter expensePresenter;
    private ListView listView;
    private ImageButton close;
    private ArrayAdapter lis;
    private EditText serchtext;
    private Dialog dialog;


   Context context = ExpenseDetail.this;
    ArrayList<String> companynamelist = new ArrayList<>();
    private SharedPreferences loginPreferences;
    private String loginid;
    private String region;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.expense);

        date = (TextView) findViewById(R.id.date);

        companyname = (TextView) findViewById(R.id.company);

      //  conmtactperson = (MaterialSpinner) findViewById(R.id.contactperson);

          distance = (EditText) findViewById(R.id.dist);

        description = (EditText) findViewById(R.id.desc);


        save = (Button) findViewById(R.id.nxt);


        amount = (EditText) findViewById(R.id.amount);

      //  uplod = (Button) findViewById(R.id.uplod);

        loginPreferences = this.getSharedPreferences("Login",0);


        loginid = loginPreferences.getString("Id","");

        region = loginPreferences.getString("Region","");



        retrofitBuild = new RetrofitBuild();

        detailsPresenter = new VisitDetailsPresenter(this);

        expensePresenter = new ExpensePresenter(this);

        progressDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        progressDialog.getProgressHelper().setBarColor(Color.parseColor("#2289DC"));
        progressDialog.setTitleText("Please  wait...");
        progressDialog.setCancelable(false);


        calendar = Calendar.getInstance();


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ExpenseDetail.this,ViewExpenseDetails.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.left_right,R.anim.right_left);

            }
        });



        detailsPresenter.getData(region);

        progressDialog.show();


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dpd = DatePickerDialog.newInstance(ExpenseDetail.this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

                dpd.show(getFragmentManager(), "datePicker");
                dpd.setAccentColor(getResources().getColor(R.color.DeepSkyBlue));
            }
        });



        companyname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OpenPopUp();
            }
        });



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(date.getText().toString().equals("")){

                    Toast.makeText(context,"please select date",Toast.LENGTH_SHORT).show();
                }
                else {

                    if(companyname.getText().toString().equals("")){


                        Toast.makeText(context,"please select company",Toast.LENGTH_SHORT).show();

                    }
                    else {

                        if(description.getText().toString().equals("")){


                            Toast.makeText(context,"please enter description",Toast.LENGTH_SHORT).show();

                        }
                        else {

                            if(distance.getText().toString().equals("")){


                                Toast.makeText(context,"please enter taveling distance",Toast.LENGTH_SHORT).show();

                            }

                            else {

                                if(amount.getText().toString().equals("")){


                                    Toast.makeText(context,"please enter amount",Toast.LENGTH_SHORT).show();

                                }
                                else {



                                    if (NetworkUtil.getConnectivityStatus(ExpenseDetail.this.getApplicationContext()) != 0) {

                                       expensePresenter.getData(loginid,date.getText().toString(),companyname.getText().toString(),description.getText().toString(),distance.getText().toString(),amount.getText().toString());

                                        progressDialog.show();


                                    } else {

                                        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
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
        });



    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

        int mon = monthOfYear + 1;

        String date1 = year + "-" + mon + "-" + dayOfMonth;

        date.setText(date1);

    }

    @Override
    public void onLoginComplete() {


        lis =  new ArrayAdapter(this, android.R.layout.simple_list_item_1, companynamelist);

        progressDialog.dismiss();

    }

    @Override
    public void onLoginError(String s) {

        progressDialog.dismiss();

    }

    @Override
    public void onLogin(VisitDetailsPojo visitDetailsPojo) {


        for(int i = 0; i< visitDetailsPojo.getData().size();i++){


            companynamelist.add(visitDetailsPojo.getData().get(i).getCustName());

        }

    }

    @Override
    public Observable<VisitDetailsPojo> getVisitData(String reg) {
        return retrofitBuild.allApi().getVisitdata(reg);
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

        dialog = new Dialog(ExpenseDetail.this);
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

                companyname.setText(selectedFromList);

                ((ArrayAdapter) listView.getAdapter()).getFilter().filter("");

                Toast.makeText(ExpenseDetail.this,selectedFromList,Toast.LENGTH_SHORT).show();

              /*  cname.clear();


                database.open();

                cursor1 = database.getdata(selectedFromList);

                int i = 0;


                for(cursor1.moveToFirst();!cursor1.isAfterLast();cursor1.moveToNext()){


                    i = cursor1.getCount();

                    System.out.println("CT:" + i);

                    cname.add(cursor1.getString(cursor1.getColumnIndex(database.KEY_CONTACT)));

                }


                database.close();


                cname.add(0,"Select contact");


                contactper.setItems(cname);
*/


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
    public void onELoginComp() {

        progressDialog.dismiss();

    }

    @Override
    public void onELoginErr(String s) {

        progressDialog.dismiss();

        Toast.makeText(context,"server error",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onELogin(LocationPojo locationPojo) {

        progressDialog.dismiss();

        if(locationPojo.getSuccess() == 1){


          Toast.makeText(context,"Expense Details save successfull",Toast.LENGTH_SHORT).show();

            Toast.makeText(this, "save successfully", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(ExpenseDetail.this, ViewExpenseDetails.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.left_right, R.anim.right_left);


        }
        else{

            Toast.makeText(context,"please try again",Toast.LENGTH_SHORT).show();

        }



    }

    @Override
    public Observable<LocationPojo> getExpense(String id, String date, String company, String description, String km, String amount) {
        return retrofitBuild.allApi().getExpense(id,date,company,description,km,amount);

    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(ExpenseDetail.this,ViewExpenseDetails.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.left_right,R.anim.right_left);
    }
}
