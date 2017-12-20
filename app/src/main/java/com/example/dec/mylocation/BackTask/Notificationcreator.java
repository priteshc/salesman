package com.example.dec.mylocation.BackTask;


import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

/**
 * Created by yashwant on 1/27/2016.
 */
public class Notificationcreator implements JobCreator {
    @Override
    public Job create(String tag) {

        switch (tag){
            case Notificationservice.TAG:

                return new Notificationservice();


            default:

                return null;

        }

    }
}
