package com.example.dec.mylocation;

import android.app.Dialog;
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
import com.example.dec.mylocation.pojo.VisitDetailsPojo;
import com.example.dec.mylocation.rximpl.VisitDetailInterface;
import com.example.dec.mylocation.rximpl.VisitDetailsPresenter;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

import rx.Observable;

/**
 * Created by pritesh on 8/5/2017.
 */

public class Customer_search extends AppCompatActivity implements VisitDetailInterface{


    private RetrofitBuild retrofitBuild;


    ArrayList<String> strings = new ArrayList<>();

    MaterialSpinner  customer;

    private TextView customertext;
    private Calendar calendar;
    private SweetAlertDialog progressDialog;

    private Button submit;
    private String cuname;
    private Dialog dialog;
    private ListView listView;
    private ImageButton close;
    private ArrayAdapter lis;
    private EditText serchtext;
    private VisitDetailsPresenter detailsPresenter;

    Button  closedialog;
    private Toolbar toolbar;
    private SharedPreferences loginPreferences;
    private String region;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.customer_search);


        loginPreferences = this.getSharedPreferences("Login",0);

        region = loginPreferences.getString("Region","");


        customertext = (TextView) findViewById(R.id.date);

        submit = (Button) findViewById(R.id.button);

        calendar = Calendar.getInstance();


        retrofitBuild = new RetrofitBuild();
        detailsPresenter = new VisitDetailsPresenter(this);


        progressDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        progressDialog.getProgressHelper().setBarColor(Color.parseColor("#2289DC"));
        progressDialog.setTitleText("Please  wait...");
        progressDialog.setCancelable(false);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        detailsPresenter.getData(region);

        progressDialog.show();



        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Customer_search.this,Visit_Details.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.left_right,R.anim.right_left);

            }
        });


        customertext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OpenPopUp();

            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String cdate = customertext.getText().toString();


                if(cdate.equals("select customer")){


                    Toast.makeText(Customer_search.this,"plzz select one of the field",Toast.LENGTH_SHORT).show();


                }
                else {

                    Intent intent = new Intent(Customer_search.this,Visit_Details.class);
                    intent.putExtra("Date",cdate);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.left_right,R.anim.right_left);

                }






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

        dialog = new Dialog(Customer_search.this);
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

                customertext.setText(selectedFromList);



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
    public void onLoginComplete() {

        lis = new ArrayAdapter(this, android.R.layout.simple_list_item_1, strings);

    }

    @Override
    public void onLoginError(String s) {

        progressDialog.dismiss();

        Toast.makeText(this, "server error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLogin(VisitDetailsPojo visitDetailsPojo) {

        progressDialog.dismiss();

        if (visitDetailsPojo.getSuccess() == 1) {


            for (int i = 0; i < visitDetailsPojo.getData().size(); i++) {


                strings.add(visitDetailsPojo.getData().get(i).getCustName());

            }

        }

    }

    @Override
    public Observable<VisitDetailsPojo> getVisitData(String reg) {
        return retrofitBuild.allApi().getVisitdata(reg);
    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Customer_search.this,Visit_Details.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.left_right,R.anim.right_left);

    }
}
