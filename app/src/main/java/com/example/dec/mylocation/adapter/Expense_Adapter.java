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
import com.example.dec.mylocation.holder.Expense_Holder;
import com.example.dec.mylocation.pojo.AllData;
import com.example.dec.mylocation.pojo.Expense;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by pritesh on 5/24/2017.
 */

public class Expense_Adapter extends RecyclerView.Adapter<Expense_Holder> {

    Context mContext;
    List<Expense> visitList;

    private int lastPosition = -1;

    public Expense_Adapter(Context c, List<Expense> visits ) {

        mContext = c;
        this.visitList = visits;


    }

    @Override
    public Expense_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.expensecard, parent, false);

        return new Expense_Holder(itemView);
    }

    @Override
    public void onBindViewHolder(Expense_Holder holder, final int position) {

        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat output = new SimpleDateFormat("dd-MM-yyyy");


        try {

          Date oneWayTripDate = input.parse(visitList.get(position).getDate().toString());                 // parse input
            holder.date.setText(output.format(oneWayTripDate));    // format output
        } catch (ParseException e) {
            e.printStackTrace();
        }


       // holder.date.setText(visitList.get(position).getVdate());
        holder.companyname.setText(visitList.get(position).getCompany().trim());
        holder.km.setText(visitList.get(position).getDriveKm().trim());
        holder.amt.setText(visitList.get(position).getAmount().trim());

        setAnimation(holder.container, position);

        holder.viewbutton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {


                /* Intent intent = new Intent(mContext,AddVisit.class);

                *//* intent.putExtra("time",visitList.get(position).getVisitDate());
                 intent.putExtra("date",visitList.get(position).getVisitTime());
                 intent.putExtra("customer",visitList.get(position).getCustName());
                 intent.putExtra("contact",visitList.get(position).getContactName());
                 intent.putExtra("product",visitList.get(position).getProductName());
                 intent.putExtra("desc",visitList.get(position).getDescription());
                 intent.putExtra("status",visitList.get(position).getStatus());
                 intent.putExtra("purpose",visitList.get(position).getPurpose());
                 intent.putExtra("flwdate",visitList.get(position).getFollowupDate());
                 intent.putExtra("flwtime",visitList.get(position).getFollowupTime());
                 intent.putExtra("remark",visitList.get(position).getRemarks());
                 intent.putExtra("vid",visitList.get(position).getVisitId());
*//*

                 mContext.startActivity(intent);
*/

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
