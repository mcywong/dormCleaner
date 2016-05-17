package com.martinwong.dormcleaner;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Martin Wong on 2016-05-16.
 */
public class databaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Dorm Cleaner";
    private static final String TABLE_NAME = "Task List";
    private static final int DATABASE_VERSION = 1;
    private static final String UID = "_ID";
    private static final String TASK_TITLE = "Task Title";
    private static final String TASK_DESCRIPTION = "Task Title";
    private static final String TASK_IMAGE = "Task Image";
    private Context context;

    public databaseHelper(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
