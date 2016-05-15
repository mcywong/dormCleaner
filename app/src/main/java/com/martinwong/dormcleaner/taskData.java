package com.martinwong.dormcleaner;

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


}
