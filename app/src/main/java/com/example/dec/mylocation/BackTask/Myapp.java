package com.example.dec.mylocation.BackTask;

import android.app.Application;
import android.content.Context;

import com.evernote.android.job.JobManager;

/**
 * Created by yashwant on 6/17/2016.
 */
public class Myapp extends Application {

    private static Myapp instance;
   /* RequestQueue request;
    public  static   EtrackDatabase proj;*/
    public static String NOTIFICATION_URL ="http://www.magicdil.com";
    public static final String TAG = Myapp.class.getSimpleName();
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        JobManager.create(this).addJobCreator(new Notificationcreator());

    }

    public static Myapp getInstance()
    {

        return instance;
    }



    public static Context getapContext(){


        return instance.getApplicationContext();
    }

   /* public RequestQueue getRequestQueue(){

        if(request == null){

            request = Volley.newRequestQueue(getApplicationContext());
        }

        return request;

    }

    public <T> void addToRequestQueue(Request<T> req, String tag){

        req.setTag(TextUtils.isEmpty(tag)? TAG : tag);
        getRequestQueue().add(req);

    }

    public <T> void addToRequestQueue(Request<T> req){

        req.setTag(TAG);
        getRequestQueue().add(req);

    }
    public void canclependingreq(Object tag){

        if(request!= null ){

            request.cancelAll(tag);

        }

    }
    public synchronized static EtrackDatabase getWritabledatabase(){

        if (proj == null) {
            proj = new EtrackDatabase(getapContext());
        }

        return  proj;
    }*/

}