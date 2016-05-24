package com.martinwong.dormcleaner;

import android.content.ContentValues;

/**
 * Created by Martin Wong on 2016-05-15.
 */
public class taskData {
    public String taskTitle;
    public String taskDescription;
    public int imageId;

    taskData(String title, String description, int image){
        taskTitle = title;
        taskDescription = description;
        imageId = image;
    }

    public static taskData[] getSampleTasks(){
        taskData getToiletPaper = new taskData("Get Toliet Paper",
                "Restock toilet paper before we run out and are stuck in the washroom.",
                R.drawable.ic_toilet_paper);
        taskData sweep = new taskData("Sweep Floor",
                "Sweep the floors clean of food scraps, dust, and hair.",
                R.drawable.ic_sweep);

        return new taskData[] {getToiletPaper, sweep};
    }

    public ContentValues toContentValues(){
        ContentValues newValues = new ContentValues();
        newValues.put(taskDatabase.TASK_TITLE, this.taskTitle);
        newValues.put(taskDatabase.TASK_DESCRIPTION, this.taskDescription);
        newValues.put(taskDatabase.TASK_IMAGE, this.imageId);
        return newValues;
    }

}
