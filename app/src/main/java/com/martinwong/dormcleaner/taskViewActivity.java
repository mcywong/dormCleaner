package com.martinwong.dormcleaner;

import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class taskViewActivity extends AppCompatActivity implements android.app.LoaderManager.LoaderCallbacks<Cursor> {
    private static final int URL_LOADER = 1;
    recyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_view);
        setTitle(R.string.main_title);

        //Arraylist adapter code
//        List<taskData> data = getData();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new recyclerViewAdapter(getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);

        //Cursor Loader Code
        this.getLoaderManager().initLoader(URL_LOADER, null, this);
    }

    //Cursor Loader Methods
    @Override
    public android.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if(id == URL_LOADER){
            return new CursorLoader(this, taskDatabase.CONTENT_URI, taskDatabase.PROJECTION, null, null, null);
        }
        return null;
    }

    @Override
    public void onLoadFinished(android.content.Loader<Cursor> loader, Cursor data) {
        if(loader.getId() == URL_LOADER){
            this.adapter.swapCursor(data);
        }
    }

    @Override
    public void onLoaderReset(android.content.Loader<Cursor> loader) {
        if(loader.getId() == URL_LOADER){
            this.adapter.swapCursor(null);
        }
    }


    //Methods for arrayAdapter
//    public List<taskData> getData(){
//        List<taskData> data = new ArrayList<>();
//        String[] names = getResources().getStringArray(R.array.tasks);
//        for(String title : names){
//            data.add(new taskData(title, "temporary description", R.drawable.ic_toilet_paper));
//        }
//        return data;
//    }

    public void navigateToAddTask(View view){
        Intent segue = new Intent(view.getContext(), newTask.class);
        startActivity(segue);
    }

    public void deleteSelectedTask(View view)
    {
        TextView taskNameView = null;
        ViewGroup row = (ViewGroup) view.getParent();
        for (int itemPos = 0; itemPos < row.getChildCount(); itemPos++) {
            View v = row.getChildAt(itemPos);
            if (v instanceof TextView) {
                taskNameView = (TextView) v; //Found it!
                break;
            }
        }
        if(taskNameView != null){
            String taskName = taskNameView.getText().toString();
            getContentResolver().delete(taskDatabase.CONTENT_URI, "taskTitle = ?", new String[] {taskName});
            getContentResolver().notifyChange(taskDatabase.CONTENT_URI, null);
        }
    }

}
