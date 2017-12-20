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

public class AllVisit_Holder extends RecyclerView.ViewHolder {

    public  LinearLayout container;
    public TextView companyname,product,contact,date,time;
    public Button viewbutton;



    public AllVisit_Holder(View itemView) {
        super(itemView);

        companyname = (TextView) itemView.findViewById(R.id.name);

        product = (TextView) itemView.findViewById(R.id.city);

        contact = (TextView) itemView.findViewById(R.id.address);

        date = (TextView) itemView.findViewById(R.id.date);

        time = (TextView) itemView.findViewById(R.id.time);

        container=(LinearLayout)itemView.findViewById(R.id.container);


        viewbutton = (Button) itemView.findViewById(R.id.bview);



    }
}
