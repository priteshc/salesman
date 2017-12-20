package com.example.dec.mylocation.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.dec.mylocation.MainActivity;
import com.example.dec.mylocation.R;
import com.example.dec.mylocation.holder.Visit_Holder;
import com.example.dec.mylocation.pojo.Visit;

import java.util.List;

/**
 * Created by pritesh on 5/24/2017.
 */

public class Visit_Adapter extends RecyclerView.Adapter<Visit_Holder> {

    Context mContext;
    List<Visit> visitList;

    SharedPreferences  preferences;
    private int lastPosition = -1;



    public Visit_Adapter( Context c, List<Visit> visits ) {

        mContext = c;
        this.visitList = visits;

        preferences = mContext.getSharedPreferences("VST",0);

    }

    @Override
    public Visit_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.visitcard, parent, false);

        return new Visit_Holder(itemView);
    }

    @Override
    public void onBindViewHolder(Visit_Holder holder, final int position) {


        holder.name.setText(visitList.get(position).getCustName());
        holder.city.setText(visitList.get(position).getCustCity().trim());
        holder.address.setText(visitList.get(position).getCustAdd());
        setAnimation(holder.container, position);


        holder.viewbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MainActivity.class);
                intent.putExtra("cid",visitList.get(position).getCustId());
                intent.putExtra("cname",visitList.get(position).getCustName());
                intent.putExtra("cadd",visitList.get(position).getCustAdd());
                intent.putExtra("ccity",visitList.get(position).getCustCity().trim());
                intent.putExtra("cpin",visitList.get(position).getPinCode());
                intent.putExtra("cstate",visitList.get(position).getCustState());
                intent.putExtra("cofftel",visitList.get(position).getOffTel());
                intent.putExtra("cemail",visitList.get(position).getEmail());
                intent.putExtra("cregion",visitList.get(position).getRegion());
                intent.putExtra("cdesc",visitList.get(position).getDescription());



                preferences.edit().putString("CNAM",visitList.get(position).getCustName()).apply();

                mContext.startActivity(intent);


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
