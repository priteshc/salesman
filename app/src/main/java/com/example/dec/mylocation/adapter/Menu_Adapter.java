package com.example.dec.mylocation.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.dec.mylocation.AddVisit;
import com.example.dec.mylocation.AllVisit;
import com.example.dec.mylocation.Menu;
import com.example.dec.mylocation.R;
import com.example.dec.mylocation.ViewExpenseDetails;
import com.example.dec.mylocation.Visit_Details;
import com.example.dec.mylocation.holder.AllVisit_Holder;
import com.example.dec.mylocation.holder.MyViewHolder;
import com.example.dec.mylocation.pojo.AllData;
import com.example.dec.mylocation.pojo.Imageidpojo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by pritesh on 5/24/2017.
 */

public class Menu_Adapter extends RecyclerView.Adapter<MyViewHolder> {

    Context mContext;
    List<Imageidpojo> visitList;

    private int lastPosition = -1;

    public Menu_Adapter(Context c, List<Imageidpojo> visits) {

        mContext = c;
        visitList = visits;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        holder.img.setImageResource(visitList.get(position).getImageId());

        holder.textView.setText(visitList.get(position).getTitle());


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(position == 0){

                    Intent intent = new Intent(mContext,AllVisit.class);
                    mContext.startActivity(intent);

                    //intent.overridePendingTransition(R.anim.enter,R.anim.exit);
                }
                else {

                    if(position == 1){

                        Intent intent = new Intent(mContext,Visit_Details.class);
                        mContext.startActivity(intent);

                        //intent.overridePendingTransition(R.anim.enter,R.anim.exit);
                    }

                    else {

                        if(position == 2){

                            Intent intent = new Intent(mContext,ViewExpenseDetails.class);
                            mContext.startActivity(intent);

                            //intent.overridePendingTransition(R.anim.enter,R.anim.exit);
                        }

                    }

                }


            }
        });

    }



    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slideleft);
            animation.setStartOffset(position*100);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return visitList.size();
    }
}
