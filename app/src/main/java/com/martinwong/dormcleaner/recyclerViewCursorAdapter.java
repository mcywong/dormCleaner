package com.martinwong.dormcleaner;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Martin Wong on 2016-05-22.\
 * Exposes db dataset to viewholder for inflation into the recycler view
 */
public abstract class RecyclerViewCursorAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private Cursor cursor;

    // Provides adapter with dataset
    public void swapCursor(final Cursor cursor){
        this.cursor = cursor;
        this.notifyDataSetChanged();
    }

    public int getItemCount(){
        return this.cursor!=null
                ? this.cursor.getCount()
                : 0;
    }
    public Cursor getItem(final int position){
        if (this.cursor != null && !this.cursor.isClosed()){
            this.cursor.moveToPosition(position);
        }
        return this.cursor;
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(true);
    }

    public Cursor getCursor(){
        return this.cursor;
    }
    //Transfer to call with cursor
    @Override
    public final void onBindViewHolder(final VH holder, final int position){
        final Cursor cursor = this.getItem(position);
        this.onBindViewHolder(holder, cursor);
    }
    //Called when data is requested to bind to view holder for inflation
    public abstract void onBindViewHolder(final VH holder, final Cursor cursor);
}
