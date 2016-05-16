package com.martinwong.dormcleaner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Activity Code for activity task detail
 */

public class Task_Detail_Activity extends AppCompatActivity {
    ImageView icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail_);
        Intent intent = getIntent();
        setTitle(intent.getStringExtra("Title"));
    }
    private static final String TAG = "Task_Detail_Activity";

}
