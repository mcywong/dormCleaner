package com.martinwong.dormcleaner;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class newTask extends AppCompatActivity {

    EditText name;
    EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        name = (EditText) this.findViewById(R.id.inputTaskTitle);
        description = (EditText) this.findViewById(R.id.inputTaskDescription);
    }

    public void addTask(View view)
    {
        String title = name.getText().toString();
        String taskDescription = description.getText().toString();
        taskData data = new taskData(title, taskDescription, R.drawable.ic_sweep);
        Uri newUri = getContentResolver().insert(taskDatabase.CONTENT_URI, data.toContentValues());
        Intent returnToList = new Intent(view.getContext(), taskViewActivity.class);
        startActivity(returnToList);
    }
}
