package com.martinwong.dormcleaner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Martin Wong on 2016-05-18.
 */
public class taskDatabase {
    private databaseHelper taskHelper;
    private SQLiteDatabase database;

    private static final String DATABASE_NAME = "Dorm Cleaner";
    private static final String TABLE_NAME = "Task List";
    private static final int DATABASE_VERSION = 1;
    private static final String UID = "_ID";
    private static final String TASK_TITLE = "Task Title";
    private static final String TASK_DESCRIPTION = "Task Title";
    private static final String TASK_IMAGE = "Task Image";
    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    UID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    TASK_TITLE + "text" + "," +
                    TASK_DESCRIPTION + "text" + "," +
                    TASK_IMAGE + "int" + "," +
                    " )";


    taskDatabase(Context context){
        taskHelper = new databaseHelper(context);
        database = taskHelper.getWritableDatabase();
    }

    private static class databaseHelper extends SQLiteOpenHelper {

        private Context context;

        public databaseHelper(Context context){
            super(context,DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    public void close(){
        if (taskHelper != null){
            taskHelper.close();
        }
    }
    public long addTask(taskData task){
        ContentValues newValues = new ContentValues();
        newValues.put(TASK_TITLE, task.taskTitle);
        newValues.put(TASK_DESCRIPTION, task.taskDescription);
        newValues.put(TASK_IMAGE, task.imageId);

        return database.insert(TABLE_NAME, null, newValues);
    }

    //Delete a task based on title
    public void deleteTaskByTitle(String title){
        String[] queryArgs = {title};
        database.delete(TABLE_NAME, "WHERE " + TASK_TITLE + " LIKE ? ", queryArgs);
    }

    public Cursor queryTaskByTitle(String title){
        if (title == null || title.length() == 0){
            return queryAllTasks();
        }
        Cursor cursor = database.query(TABLE_NAME, new String[] {UID, TASK_TITLE,
                        TASK_DESCRIPTION, TASK_IMAGE}, TASK_TITLE + " like '%" + title + "%'",
                null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor queryAllTasks(){
        Cursor cursor = database.query(TABLE_NAME, new String[] {UID, TASK_TITLE,
                TASK_DESCRIPTION, TASK_IMAGE}, null, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public void createDefaultTasks() {
        for (taskData task : taskData.getSampleTasks()) {
            addTask(task);
        }
    }
}
