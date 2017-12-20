package com.example.dec.mylocation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.dec.mylocation.R;
import com.example.dec.mylocation.holder.ContactHolder;
import com.example.dec.mylocation.holder.Visit_Holder;
import com.example.dec.mylocation.pojo.AllData;
import com.example.dec.mylocation.pojo.Contact;

import java.util.List;

/**
 * Created by pritesh on 5/24/2017.
 */

public class Contactt_Adapter extends RecyclerView.Adapter<ContactHolder> {

    Context mContext;
    List<Contact> visitList;
    private int lastPosition = -1;

    public Contactt_Adapter(Context c, List<Contact> visits ) {

        mContext = c;
        this.visitList = visits;

    }

    @Override
    public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contactcard, parent, false);

        return new ContactHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactHolder holder, int position) {


        holder.name.setText(visitList.get(position).getPersonName());
        holder.desig.setText(visitList.get(position).getDesignation());
        holder.mobile.setText(visitList.get(position).getMobileNo1());
        holder.mobile2.setText(visitList.get(position).getMobileNo2());
        holder.email.setText(visitList.get(position).getEmailId());

        setAnimation(holder.container, position);



    }



    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slideleft);
            animation.setStartOffset(position*200);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }


    @Override
    public int getItemCount() {
        return visitList.size();
    }
}
