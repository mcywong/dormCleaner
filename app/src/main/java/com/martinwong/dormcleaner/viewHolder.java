package com.martinwong.dormcleaner;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Martin Wong on 2016-05-15.
 */
public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private Intent detailSegue;
    private RecyclerView recyclerView;
    private TextView emptyView;
    private CardView cv;
    private TextView title;
    private ImageView icon;
    private ImageButton deleteTask;

    viewHolder(View itemView) {
        super(itemView);
        recyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerview);
        emptyView = (TextView) itemView.findViewById(R.id.empty_view);
        cv = (CardView) itemView.findViewById(R.id.cardView);
        title = (TextView) itemView.findViewById(R.id.title);
        icon = (ImageView) itemView.findViewById(R.id.taskIcon);
        deleteTask = (ImageButton) itemView.findViewById(R.id.deleteTask);
        title.setOnClickListener(this);
        icon.setOnClickListener(this);
        itemView.setOnClickListener(this);
//        deleteTask.setOnClickListener(this);
    }

    public void bindData(final Cursor cursor){
        if(cursor!= null){
            cursor.moveToFirst();
            final String name = cursor.getString(cursor.getColumnIndex("TaskTitle"));
            final int iconId = cursor.getInt(cursor.getColumnIndex("TaskImage"));
//            recyclerView.setVisibility(View.VISIBLE);
//            emptyView.setVisibility(View.GONE);
            title.setText(name);
            icon.setImageResource(iconId);
        }
//        else{
//            recyclerView.setVisibility(View.GONE);
//            emptyView.setVisibility(View.VISIBLE);
//        }

    }
    @Override
    public void onClick(View v) {
            detailSegue = new Intent(v.getContext(), Task_Detail_Activity.class);
            detailSegue.putExtra("Title", title.getText().toString());
            v.getContext().startActivity(detailSegue);

    }

}
