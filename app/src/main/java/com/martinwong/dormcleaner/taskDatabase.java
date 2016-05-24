package com.martinwong.dormcleaner;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by Martin Wong on 2016-05-18.
 */
public class taskDatabase extends ContentProvider{
    private databaseHelper taskHelper;
    private SQLiteDatabase database;
    //DB Constants
    private static final String DATABASE_NAME = "Dorm Cleaner";
    private static final String TABLE_NAME = "Task_List";
    private static final int DATABASE_VERSION = 1;
    //Column Fields
    private static final String UID = "_ID";
    public static final String TASK_TITLE = "TaskTitle";
    public static final String TASK_DESCRIPTION = "TaskDescription";
    public static final String TASK_IMAGE = "TaskImage";

    private static final int TASKS = 1;
    private static final int TASKS_ID = 2;

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    UID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    TASK_TITLE + " text" + ", " +
                    TASK_DESCRIPTION + " text" + ", " +
                    TASK_IMAGE + " int" +
                    " )";

    //Content Provider Constants
    public static final String AUTHORITY = "com.martinwong.dromcleaner.provider";
    public static final String[] PROJECTION = {UID,TASK_TITLE,TASK_DESCRIPTION,TASK_IMAGE};
    public static final Uri CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + TASK_TITLE);
    public static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, TABLE_NAME, TASKS);
        uriMatcher.addURI(AUTHORITY, TABLE_NAME + "/#", TASKS_ID);
    }

//    taskDatabase(Context context){
//        taskHelper = new databaseHelper(context);
//        database = taskHelper.getWritableDatabase();
//    }

    @Override
    public boolean onCreate() {
        taskHelper = new databaseHelper(getContext());
        database = taskHelper.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(TABLE_NAME);
//        switch (uriMatcher.match(uri)) {
//            case TASKS:
//                break;
//            default:
//                throw new IllegalArgumentException("Unknown URI " + uri);
//        }

        database = taskHelper.getReadableDatabase();
        Cursor c = qb.query(database, projection, selection, selectionArgs, null, null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues newValues) {
//        if (uriMatcher.match(uri) != TASKS) {
//            throw new IllegalArgumentException("Unknown URI " + uri);
//        }

        ContentValues values;
        if (newValues != null) {
            values = new ContentValues(newValues);
        } else {
            values = new ContentValues();
        }
        long rowId = database.insert(TABLE_NAME, null, newValues);
        if (rowId > 0) {
            Uri taskUri = ContentUris.withAppendedId(CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(taskUri, null);
            return taskUri;
        }

        throw new SQLException("Failed to insert row into" + uri);
    }

    @Override
    public int delete(Uri uri, String where, String[] whereArgs) {
//        switch (uriMatcher.match(uri)) {
//            case TASKS:
//                break;
//            case TASKS_ID:
//                where = where + "_id = " + uri.getLastPathSegment();
//                break;
//            default:
//                throw new IllegalArgumentException("Unknown URI " + uri);
//        }

        int count = database.delete(TABLE_NAME, where, whereArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    //TODO: allow editing of tasks
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
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

    //Old queries that did not use content providers
//    public long navigateToAddTask(taskData task){
//        ContentValues newValues = new ContentValues();
//        newValues.put(TASK_TITLE, task.taskTitle);
//        newValues.put(TASK_DESCRIPTION, task.taskDescription);
//        newValues.put(TASK_IMAGE, task.imageId);
//
//        return database.insert(TABLE_NAME, null, newValues);
//    }
//
//    //Delete a task based on title
//    public void deleteTaskByTitle(String title){
//        String[] queryArgs = {title};
//        database.delete(TABLE_NAME, "WHERE " + TASK_TITLE + " LIKE ? ", queryArgs);
//    }
//
//    public Cursor queryTaskByTitle(String title){
//        if (title == null || title.length() == 0){
//            return queryAllTasks();
//        }
//        Cursor cursor = database.query(TABLE_NAME, new String[] {UID, TASK_TITLE,
//                        TASK_DESCRIPTION, TASK_IMAGE}, TASK_TITLE + " like '%" + title + "%'",
//                null, null, null, null);
//        if (cursor != null){
//            cursor.moveToFirst();
//        }
//        return cursor;
//    }
//
//    public Cursor queryAllTasks(){
//        Cursor cursor = database.query(TABLE_NAME, PROJECTION, null, null, null, null, null);
//        if (cursor != null){
//            cursor.moveToFirst();
//        }
//        return cursor;
//    }
//
//    public void createDefaultTasks() {
//        for (taskData task : taskData.getSampleTasks()) {
//            navigateToAddTask(task);
//        }
//    }
}
