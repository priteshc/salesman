package com.example.dec.mylocation;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.evernote.android.job.JobRequest;
import com.example.dec.mylocation.BackTask.MyReceiver;
import com.example.dec.mylocation.BackTask.Notificationservice;
import com.example.dec.mylocation.adapter.Menu_Adapter;
import com.example.dec.mylocation.pojo.Imageidpojo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by pritesh on 5/19/2017.
 */

public class Menu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    CardView cardView,cardView1,cardView2,cardView4;

    AlertDialog.Builder alertDialog;

    protected LocationManager locationManager;

    public boolean isGPSEnabled = false;
    private SharedPreferences loginPreferences;


    String magr;
    private Toolbar toolbar;
    private String repotid;

    public RecyclerView recyclerView1;

    private TextView uname;

    public static final   String[] titles = new String[] { "Visit detail",
            "Customer Master", "Expense", "Setting" };

    public static final  Integer[] images = { R.drawable.viewdet,
            R.drawable.custicon, R.drawable.expenseicon, R.drawable.setticon };

    List<Imageidpojo> rowItems = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.menu);

        loginPreferences = this.getSharedPreferences("Login",0);

        repotid = loginPreferences.getString("ReptId","");

        magr = loginPreferences.getString("log","");


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        uname = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tvBranchName);


        if(repotid.equals("")){

            navigationView.inflateMenu(R.menu.activity_home_drawer_magr);

        }
        else {

            navigationView.inflateMenu(R.menu.activity_home_drawer);

        }

        cardView = (CardView) findViewById(R.id.card1);

        cardView1 = (CardView) findViewById(R.id.card2);

        cardView2 = (CardView) findViewById(R.id.card3);

        cardView4 = (CardView) findViewById(R.id.card4);


        recyclerView1 = (RecyclerView)findViewById(R.id.recycler_view1);

        recyclerView1.setVisibility(View.VISIBLE);


        for (int i = 0; i < titles.length; i++) {
            Imageidpojo item = new Imageidpojo(images[i], titles[i]);
            rowItems.add(item);
        }


        Menu_Adapter adapter = new Menu_Adapter(this,rowItems);


        recyclerView1.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView1.setItemAnimator(new DefaultItemAnimator());

        recyclerView1.setAdapter(adapter);




        alertDialog = new AlertDialog.Builder(this);



        Calendar calendar = Calendar.getInstance();

// we can set time by open date and time picker dialog

        calendar.set(Calendar.HOUR_OF_DAY, 16);
        calendar.set(Calendar.MINUTE, 43);
        calendar.set(Calendar.SECOND, 0);

        Intent intent1 = new Intent(Menu.this, MyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                Menu.this, 0, intent1,
                PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) Menu.this
                .getSystemService(Menu.this.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);






        uname.setText(magr);

        if(magr.equals("Mngr")){

            cardView4.setVisibility(View.VISIBLE);
        }
        else {

            cardView4.setVisibility(View.GONE);


        }


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent intent = new Intent(Menu.this,AllVisit.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter,R.anim.exit);

            }
        });



        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Menu.this,Visit_Details.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter,R.anim.exit);
            }
        });


        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Menu.this,ViewExpenseDetails.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter,R.anim.exit);
            }
        });


        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Menu.this,Manager.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter,R.anim.exit);
            }
        });


        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);



        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);



        if(!isGPSEnabled){


            showSettingsAlert();


        }
        else {

            scheduleAdvancedJob1();

            schedulePeriodicJob();

        }



    }


    public void showSettingsAlert() {

        //Setting Dialog Title
        alertDialog.setTitle("Gps is not enabled");

        //Setting Dialog Message
        alertDialog.setMessage("Gps is not enable. Do you want to go to the settings menu to enable Gps ? After enabling location please relaunch the app manually");

        //On Pressing Setting button
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
                finish();
            }
        });

        //On pressing cancel button
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });

        alertDialog.show();
    }




    private void scheduleAdvancedJob1() {

        Notificationservice.JOB_ID = new JobRequest.Builder(Notificationservice.TAG)
                .setExact(5_000L)
                .setPersisted(true)
                .build()
                .schedule();

    }

    private void schedulePeriodicJob() {
        Notificationservice.JOB_ID = new JobRequest.Builder(Notificationservice.TAG)
                .setPeriodic(9_00000L)
                .setRequiredNetworkType(JobRequest.NetworkType.UNMETERED)
                .setPersisted(true)
                .build()
                .schedule();

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.request){

            Intent intent = new Intent(Menu.this,LeaveRequest.class);
            startActivity(intent);
        }

        else {

            if(id == R.id.report){

                Intent intent = new Intent(Menu.this,User_LeaveReport.class);
                startActivity(intent);
            }

            else {


                if(id == R.id.mreport){

                    Intent intent = new Intent(Menu.this,Magr_LeaveReport.class);
                    startActivity(intent);
                }

                else {


                    if (id == R.id.followupuser) {

                        Intent intent = new Intent(Menu.this, Followup_Data.class);
                        startActivity(intent);
                    } else {


                        if (id == R.id.track) {

                            Intent intent = new Intent(Menu.this, Manager.class);
                            startActivity(intent);
                        } else
                            {


                            if (id == R.id.followup) {

                                Intent intent = new Intent(Menu.this, Followup_Data.class);
                                startActivity(intent);
                            }


                        }

                    }
                }


            }



        }




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
