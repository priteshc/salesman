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

public class Expense_Holder extends RecyclerView.ViewHolder {

    public  LinearLayout container;
    public TextView companyname,date,km,amt;
    public Button viewbutton;



    public Expense_Holder(View itemView) {
        super(itemView);

        companyname = (TextView) itemView.findViewById(R.id.cname);

        km = (TextView) itemView.findViewById(R.id.km);

        amt = (TextView) itemView.findViewById(R.id.amt);

        date = (TextView) itemView.findViewById(R.id.date);


        container=(LinearLayout)itemView.findViewById(R.id.container);


        viewbutton = (Button) itemView.findViewById(R.id.bview);



    }
}
