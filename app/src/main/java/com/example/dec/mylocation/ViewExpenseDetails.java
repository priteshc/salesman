package com.example.dec.mylocation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dec.mylocation.BackTask.RetrofitBuild;
import com.example.dec.mylocation.adapter.Expense_Adapter;
import com.example.dec.mylocation.custom.SweetAlertDialog;
import com.example.dec.mylocation.pojo.Expense;
import com.example.dec.mylocation.pojo.ExpenseAllpojo;
import com.example.dec.mylocation.rximpl.ExpensePresenter;
import com.example.dec.mylocation.rximpl.ViewExpenseInterface;
import com.example.dec.mylocation.rximpl.ViewExpensePresenter;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by pritesh on 8/5/2017.
 */

public class ViewExpenseDetails extends AppCompatActivity implements ViewExpenseInterface {


    List<Expense> expenses = new ArrayList<>();

    private Expense_Adapter expense_adapter;

    RecyclerView recyclerView;


    private SweetAlertDialog progressDialog;
    private TextView add,msgtext;
    private RetrofitBuild retrofitBuild;

    private ViewExpensePresenter expensePresenter;
    private Toolbar toolbar;
    private SharedPreferences loginPreferences;
    private String loginid;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.viewexpense);



        progressDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        progressDialog.getProgressHelper().setBarColor(Color.parseColor("#2289DC"));
        progressDialog.setTitleText("Please  wait...");
        progressDialog.setCancelable(false);



        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        LinearLayoutManager llm = new LinearLayoutManager(ViewExpenseDetails.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(llm);


        add = (TextView) findViewById(R.id.add);

        msgtext = (TextView) findViewById(R.id.msg);

        retrofitBuild = new RetrofitBuild();

        expensePresenter = new ViewExpensePresenter(this);


        loginPreferences = this.getSharedPreferences("Login",0);


        loginid = loginPreferences.getString("Id","");



        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(ViewExpenseDetails.this,ExpenseDetail.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.left_right,R.anim.right_left);

            }
        });



        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ViewExpenseDetails.this,Menu.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.left_right,R.anim.right_left);



            }
        });


        expensePresenter.getData(loginid);

        progressDialog.show();




    }

    @Override
    public void onLoginComplete() {

    }

    @Override
    public void onLoginError(String s) {

        progressDialog.dismiss();


        Toast.makeText(ViewExpenseDetails.this,"server error",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLogin(ExpenseAllpojo allVisitDetailsPojo) {


        progressDialog.dismiss();

        if(allVisitDetailsPojo.getSuccess() == 1){


           expenses = allVisitDetailsPojo.getData();

            expense_adapter = new Expense_Adapter(ViewExpenseDetails.this,expenses);

            recyclerView.setAdapter(expense_adapter);

        }
        else {


            if(allVisitDetailsPojo.getSuccess() == 0){


                msgtext.setVisibility(View.VISIBLE);

                recyclerView.setVisibility(View.GONE);

            }
            else {

                if (expenses.size() <= 0) {

                    msgtext.setVisibility(View.VISIBLE);

                    recyclerView.setVisibility(View.GONE);

                }

            }


            }


    }

    @Override
    public Observable<ExpenseAllpojo> getAllVisitData(String id) {
        return retrofitBuild.allApi().getAllExpense(id);
    }
}
