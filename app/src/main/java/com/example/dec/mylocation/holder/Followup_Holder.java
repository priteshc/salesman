package com.example.dec.mylocation.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dec.mylocation.R;

/**
 * Created by pritesh on 5/24/2017.
 */

public class Followup_Holder extends RecyclerView.ViewHolder {

    public  LinearLayout container;
    public TextView companyname,date,time,productname,status,contaname;



    public Followup_Holder(View itemView) {
        super(itemView);

        companyname = (TextView) itemView.findViewById(R.id.custname);

        time = (TextView) itemView.findViewById(R.id.time);

        productname = (TextView) itemView.findViewById(R.id.productname);

        date = (TextView) itemView.findViewById(R.id.date);

        status = (TextView) itemView.findViewById(R.id.status);

        contaname = (TextView) itemView.findViewById(R.id.contactname);


        container=(LinearLayout)itemView.findViewById(R.id.ll1);




    }
}
