package com.martinwong.dormcleaner;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Martin Wong on 2016-05-15.
 */
public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    Intent detailSegue;
    CardView cv;
    TextView title;
    TextView description;
    ImageView imageView;

    viewHolder(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cardView);
        title = (TextView) itemView.findViewById(R.id.title);
        description = (TextView) itemView.findViewById(R.id.description);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);
        title.setOnClickListener(this);
        description.setOnClickListener(this);
        imageView.setOnClickListener(this);
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        detailSegue = new Intent(v.getContext(), Task_Detail_Activity.class);
        v.getContext().startActivity(detailSegue);
        Log.d("viewHolder", "onClick: tapped");
    }
}
