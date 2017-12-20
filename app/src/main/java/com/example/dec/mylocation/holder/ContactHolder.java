package com.example.dec.mylocation.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dec.mylocation.R;

/**
 * Created by pritesh on 5/31/2017.
 */

public class ContactHolder extends RecyclerView.ViewHolder {

    public LinearLayout container;
    public TextView name,desig,mobile,mobile2,email;

    public ContactHolder(View itemView) {
        super(itemView);

        name = (TextView) itemView.findViewById(R.id.name);
        desig = (TextView) itemView.findViewById(R.id.desig);
        mobile = (TextView) itemView.findViewById(R.id.mobile);
        mobile2 = (TextView) itemView.findViewById(R.id.mobile2);

        email = (TextView) itemView.findViewById(R.id.email);

        container=(LinearLayout)itemView.findViewById(R.id.container);






    }
}
