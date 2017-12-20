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
import com.example.dec.mylocation.R;
import com.example.dec.mylocation.holder.AllVisit_Holder;
import com.example.dec.mylocation.holder.Followup_Holder;
import com.example.dec.mylocation.pojo.AllData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by pritesh on 5/24/2017.
 */

public class Followup_Adapter extends RecyclerView.Adapter<Followup_Holder> {

    Context mContext;
    List<AllData> visitList;

    private int lastPosition = -1;

    public Followup_Adapter(Context c, List<AllData> visits ) {

        mContext = c;
        this.visitList = visits;


    }

    @Override
    public Followup_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.followupcard, parent, false);

        return new Followup_Holder(itemView);
    }

    @Override
    public void onBindViewHolder(Followup_Holder holder, final int position) {

        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat output = new SimpleDateFormat("dd-MM-yyyy");


        try {

          Date oneWayTripDate = input.parse(visitList.get(position).getVisitDate().toString());                 // parse input
            holder.date.setText(output.format(oneWayTripDate));    // format output
        } catch (ParseException e) {
            e.printStackTrace();
        }


       // holder.date.setText(visitList.get(position).getVdate());
        holder.time.setText(visitList.get(position).getVisitTime());
        holder.companyname.setText(visitList.get(position).getCustName().trim());
        holder.productname.setText(visitList.get(position).getProductName().trim());
        holder.contaname.setText(visitList.get(position).getContactName().trim());
        holder.status.setText(visitList.get(position).getStatus().trim());


        setAnimation(holder.container, position);


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
