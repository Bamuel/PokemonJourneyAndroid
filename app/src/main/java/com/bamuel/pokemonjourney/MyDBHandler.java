package com.bamuel.pokemonjourney;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "SaveFile.db";
    public static final String TABLE_SAVEFILE = "SaveFile";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USERNAME = "_username";
    public static final String COLUMN_GENDER = "_gender";
    public static final String COLUMN_STEPS = "_steps";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_SAVEFILE + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_GENDER + " TEXT, " +
                COLUMN_STEPS + " INTEGER" +
                ");";
        db.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SAVEFILE);
        onCreate(db);
    }

    //ADD NEW ROW TO DB
    public void addUSER(SaveFile saveFile){
        deleteUSER();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, saveFile.get_username());
        values.put(COLUMN_GENDER, saveFile.get_gender());
        values.put(COLUMN_STEPS, saveFile.get_steps());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_SAVEFILE, null, values);
        db.close();
    }

    //THIS IS ACTUALLY TO RESET TO LINE 1
    private void deleteUSER(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_SAVEFILE);
    }

    public String DBtoStringUSERNAME(){
        String dbString = "A FATAL ERROR HAS OCCURRED";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_SAVEFILE + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while (!c.isAfterLast()){
            if (c.getString(c.getColumnIndex("_username")) != null){
                dbString = c.getString(c.getColumnIndex("_username"));
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }
    public String DBtoStringSteps(){
        String dbString = "A FATAL ERROR HAS OCCURRED";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_SAVEFILE + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while (!c.isAfterLast()){
            if (c.getString(c.getColumnIndex("_steps")) != null){
                dbString = c.getString(c.getColumnIndex("_steps"));
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }
    public String DBtoStringGender(){
        String dbString = "A FATAL ERROR HAS OCCURRED";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_SAVEFILE + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while (!c.isAfterLast()){
            if (c.getString(c.getColumnIndex("_gender")) != null){
                dbString = c.getString(c.getColumnIndex("_gender"));
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }
}
