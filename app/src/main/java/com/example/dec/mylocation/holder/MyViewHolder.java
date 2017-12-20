package com.example.dec.mylocation.holder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dec.mylocation.R;

/**
 * Created by yashwant on 2/8/2016.
 */
public class MyViewHolder extends RecyclerView.ViewHolder {
   // public TextView pos,titl,yer,locat;
    public RelativeLayout my;
   public ImageView img;
 public TextView textView;

    public CardView cardView;
    public MyViewHolder(View view) {
        super(view);

        img = (ImageView)view.findViewById(R.id.grid_image);
        my = (RelativeLayout)view.findViewById(R.id.myy);

        textView = (TextView) view.findViewById(R.id.text);

        cardView = (CardView) view.findViewById(R.id.card_view);

    }



}
