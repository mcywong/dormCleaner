package com.martinwong.dormcleaner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class taskViewActivity extends AppCompatActivity{

    Intent detailSegue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_view);
        setTitle(R.string.main_title);
        List<taskData> data = getData();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerViewAdapter adapter = new recyclerViewAdapter(data, getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public List<taskData> getData(){
        List<taskData> data = new ArrayList<>();
        String[] names = getResources().getStringArray(R.array.tasks);
        for(String title : names){
            data.add(new taskData(title, "temporary description", R.drawable.ic_toilet_paper));
        }
        return data;
    }

    public void addTask(View view){
        Intent segue = new Intent(view.getContext(), newTask.class);
        startActivity(segue);
    }

}
