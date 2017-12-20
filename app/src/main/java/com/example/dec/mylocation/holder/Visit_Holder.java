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

public class Visit_Holder extends RecyclerView.ViewHolder {

    public LinearLayout container;
    public TextView name,city,address;
    public Button viewbutton;

    public Visit_Holder(View itemView) {
        super(itemView);

        name = (TextView) itemView.findViewById(R.id.name);
        city = (TextView) itemView.findViewById(R.id.city);
        address = (TextView) itemView.findViewById(R.id.address);
        container=(LinearLayout)itemView.findViewById(R.id.container);


        viewbutton = (Button) itemView.findViewById(R.id.bview);



    }
}
