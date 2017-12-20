package com.example.dec.mylocation.BackTask;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobManager;
import com.example.dec.mylocation.LocationService;
import com.example.dec.mylocation.MainActivity;
import com.example.dec.mylocation.R;
import com.example.dec.mylocation.pojo.LocationPojo;
import com.example.dec.mylocation.rximpl.TestInterface;
import com.example.dec.mylocation.rximpl.TestPresenter;

import net.vrallev.android.cat.Cat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import rx.Observable;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by yashwant on 1/19/2016.
 */
public class Notificationservice extends Job implements TestInterface {
    //JobParameters jobpar;
    Cursor c1,c2,c11;
    int count,count1;
    SharedPreferences shred, newshred, job;

    String title,netchk,detail;
    public static  int JOB_ID = 12;

    public static final String TAG = "Notification";

    private RetrofitBuild retrofitBuild;
    private TestPresenter testPresenter;
    private Calendar calendar;
    private  String date,time;
    public boolean isGPSEnabled = false;
    protected LocationManager locationManager;
  private SharedPreferences preferences;

    String login;


    @NonNull
    @Override
    protected Result onRunJob(Params params) {

      //  final Task task = new Task(this);

        retrofitBuild = new RetrofitBuild();

        testPresenter = new TestPresenter(this);

        preferences = getContext().getSharedPreferences("Login",0);

        calendar  = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        date = df.format(calendar.getTime());

        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");

        time = dateFormat.format(calendar.getTime());

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();

        final String dayOfTheWeek = sdf.format(d);

        locationManager = (LocationManager) getContext().getSystemService(LOCATION_SERVICE);


        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);


        login = preferences.getString("log","");



        final CountDownLatch latch = new CountDownLatch(1);

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {




                System.out.println("Day:" + dayOfTheWeek);

               if(dayOfTheWeek.equals("Saturday") || dayOfTheWeek.equals("Sunday"))
               {

               }
               else {

                   if(isGPSEnabled) {

                       new Async().execute();

                       latch.countDown();

                   }
               }




            }
        });

        try{

            latch.await();


        }
        catch(InterruptedException e){
            Cat.e(e);
        }

        if (isCanceled())
        {
            return params.isPeriodic() ? Result.FAILURE : Result.RESCHEDULE;
        }
        else
        {
            return Result.SUCCESS;
        }

    }


    public void canceledjob(int JOB_ID){

        JobManager.instance().cancel(JOB_ID);

    }


    @Override
    public void onLoginComplete() {

    }

    @Override
    public void onLoginError(String s) {

    }

    @Override
    public void onLogin(LocationPojo locationPojo) {

        int i = locationPojo.getSuccess();

        if(i == 1){

            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            Notification.Builder mBuilder = new Notification.Builder(getContext())
                    .setSmallIcon(R.drawable.iclauncher)
                    .setContentTitle("ABS")
                    .setSound(alarmSound)
                    .setLights(Color.GREEN, 500, 500)
                    .setContentText(title);
            Intent resultIntent = new Intent(getContext(), MainActivity.class);

            TaskStackBuilder stackBuilder = TaskStackBuilder.create(getContext());
            stackBuilder.addParentStack(MainActivity.class);
            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent =
                    stackBuilder.getPendingIntent(
                            0,
                            PendingIntent.FLAG_UPDATE_CURRENT
                    );
            mBuilder.setAutoCancel(true);
            mBuilder.setContentIntent(resultPendingIntent);
            NotificationManager mNotificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(0, mBuilder.build());


        }




    }

    @Override
    public Observable<LocationPojo> getConf(String name,Double lat,Double lng,String date1,String login,String time) {
        return retrofitBuild.allApi().getConform(name,lat,lng,date1,login,time);
    }




    public void getaddress(Double lat1,Double lng1){

        Geocoder coder = new Geocoder(getContext());
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


            testPresenter.getData(subLocality,lat1,lng1,date,login,time);

            System.out.println("Loc:" + subLocality);


        }


    }


    private class Async extends AsyncTask<Void,Void,Void>{

        Double lat1,lng1;
        LocationService lc;

        @Override
        protected void onPreExecute() {

               lc = new LocationService(getContext());

        }

        @Override
        protected Void doInBackground(Void... params) {

            final String stringLatitude = String.valueOf(lc.getLatitude());
            final String stringLongtitude = String.valueOf(lc.getLongitude());


            lat1 = Double.valueOf(stringLatitude);

           lng1 = Double.valueOf(stringLongtitude);


            System.out.println("Loc:" + lat1 + lng1);

            getaddress(lat1, lng1);

            return null;
        }



    }

}
