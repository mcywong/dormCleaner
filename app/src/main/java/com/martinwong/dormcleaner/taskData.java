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

    static taskData[] getSampleTasks(){
        taskData getToiletPaper = new taskData("Get Toliet Paper",
                "Restock toilet paper before we run out and are stuck in the washroom.",
                R.drawable.ic_toilet_paper);
        taskData sweep = new taskData("Sweep Floor",
                "Sweep the floors clean of food scraps, dust, and hair.",
                R.drawable.ic_sweep);

        return new taskData[] {getToiletPaper, sweep};
    }

}
