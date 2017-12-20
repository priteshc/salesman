package com.example.dec.mylocation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dec.mylocation.BackTask.RetrofitBuild;
import com.example.dec.mylocation.custom.SweetAlertDialog;
import com.example.dec.mylocation.pojo.AquLoginPojo;
import com.example.dec.mylocation.pojo.Contact;
import com.example.dec.mylocation.pojo.LocationPojo;
import com.example.dec.mylocation.rximpl.AquTokenInterface;
import com.example.dec.mylocation.rximpl.AquTokenPresenter;
import com.example.dec.mylocation.rximpl.LoginInterface;
import com.example.dec.mylocation.rximpl.LoginPresenter;

import rx.Observable;

/**
 * Created by pritesh on 6/16/2017.
 */

public class Login extends AppCompatActivity implements LoginInterface,AquTokenInterface {

    Button login;
    TextInputLayout cname,cpassword;
    EditText name,password;

    Context context = Login.this;

    private RetrofitBuild retrofitBuild;
    private LoginPresenter loginPresenter;
    private SweetAlertDialog progressDialog;
    private AquTokenPresenter aquTokenPresenter;

    private SharedPreferences loginPreferences;
    private SharedPreferences tokenPreferences;
    private String token;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);


        progressDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        progressDialog.getProgressHelper().setBarColor(Color.parseColor("#2289DC"));
        progressDialog.setTitleText("Please  wait...");
        progressDialog.setCancelable(false);

        loginPreferences = this.getSharedPreferences("Login",0);

        tokenPreferences = this.getSharedPreferences("TOK",0);


        token = tokenPreferences.getString("Token","");

        login = (Button) findViewById(R.id.log);

        cname = (TextInputLayout) findViewById(R.id.cname);
        cpassword = (TextInputLayout) findViewById(R.id.cpassword);

        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);


        retrofitBuild = new RetrofitBuild();

        loginPresenter = new LoginPresenter(this);

        aquTokenPresenter = new AquTokenPresenter(this);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginPresenter.getData(name.getText().toString(),password.getText().toString());

                progressDialog.show();

            }
        });

    }

    @Override
    public void onLoginComplete() {

    //   progressDialog.dismiss();
    }

    @Override
    public void onLoginError(String s) {

        progressDialog.dismiss();

        Toast.makeText(context,"server error",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLogin(AquLoginPojo aquLoginPojo) {

    //    progressDialog.dismiss();

        if(aquLoginPojo.getSuccess() == 1){

            loginPreferences.edit().putString("log",aquLoginPojo.getData().get(0).getLoginName()).putString("Id",aquLoginPojo.getData().get(0).getLoginId()).putString("ReptId",aquLoginPojo.getData().get(0).getRepMgrId()).putString("Region",aquLoginPojo.getData().get(0).getRegion()).apply();


            aquTokenPresenter.getData(aquLoginPojo.getData().get(0).getLoginId(),token);


            /*Intent intent = new Intent(Login.this,Menu.class);
            startActivity(intent);
            finish();
*/
        }
        else {

            new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Login")
                    .setContentText("Invalid login")
                    .setConfirmText("Ok")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {

                            sweetAlertDialog.dismiss();

                        }
                    }).show();

            progressDialog.dismiss();

            Toast.makeText(context,"Invalid login",Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    public Observable<AquLoginPojo> getLogin(String email, String password) {
        return retrofitBuild.allApi().getLogin(email,password);
    }

    @Override
    public void onTLoginComplete() {

        progressDialog.dismiss();


    }

    @Override
    public void onTLoginError(String s) {


        progressDialog.dismiss();

        Toast.makeText(context,"server error",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onTLogin(AquLoginPojo locationPojo) {


        if(locationPojo.getSuccess() == 1){


            loginPreferences.edit().putString("Mgrtoken",locationPojo.getData().get(0).getMagrToken()).putString("UserToken",locationPojo.getData().get(0).getUserToken()).apply();

          //  Toast.makeText(context,"successfully login",Toast.LENGTH_SHORT).show();

            new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Login")
                    .setContentText("you have been successfully login")
                    .setConfirmText("Ok")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {

                            Intent intent = new Intent(Login.this,Menu.class);
                            startActivity(intent);
                            finish();

                            sweetAlertDialog.dismiss();

                        }
                    }).show();

        }
        else {

            new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Login")
                    .setContentText("Invalid login")
                    .setConfirmText("Ok")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {

                            sweetAlertDialog.dismiss();

                        }
                    }).show();

     //       Toast.makeText(context,"Invalid login",Toast.LENGTH_SHORT).show();


        }



    }

    @Override
    public Observable<AquLoginPojo> getTdetail(String id, String token) {
        return retrofitBuild.allApi().getTDetail(id,token);
    }
}
