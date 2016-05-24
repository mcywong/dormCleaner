package com.martinwong.dormcleaner;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Martin Wong on 2016-05-15.
 */
public class recyclerViewAdapter extends RecyclerViewCursorAdapter<viewHolder> {
    private static final String TAG = "recyclerViewAdapter";
    private final LayoutInflater layoutInflater;

    // Array Adapter constructor for personal reference
//    public recyclerViewAdapter(List<taskData> list, Context context) {
//        this.list = list;
//        this.context = context;
//        layoutInflater = LayoutInflater.from(context);
//    }

    // Constructor for using cursors
    public recyclerViewAdapter(Context context){
        super();
        this.layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_row_layout, parent, false);
        return new viewHolder(v);

    }

//    @Override
//    public void onBindViewHolder(viewHolder holder, int position) {
//
//        //Use the provided View Holder from the onCreateViewHolder method to populate the current row on the RecyclerView
//        holder.title.setText(list.get(position).taskTitle);
//        //holder.description.setText(list.get(position).taskDescription);
//        holder.icon.setImageResource(list.get(position).imageId);
//
//        //animate(holder);
//
//    }

    @Override
    public void onBindViewHolder(viewHolder holder, Cursor cursor) {
        cursor.moveToPosition(0);
        holder.bindData(cursor);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }



// Methods that allow users to add or remove tasks when using an array adapter
//    // Insert a new item to the RecyclerView on a predefined position
//    public void insert(int position, taskData data) {
//        list.add(position, data);
//        notifyItemInserted(position);
//    }
//
//    // Remove a RecyclerView item containing a specified Data object
//    public void remove(taskData data) {
//        int position = list.indexOf(data);
//        list.remove(position);
//        notifyItemRemoved(position);
//    }
//  @Override
//  public int getItemCount() {
//    //returns the number of elements the RecyclerView will display
//    return list.size();
//}

}
