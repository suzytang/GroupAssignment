package com.example.groupassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

public class DatabaseManager {
    private Context context;
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;

    public DatabaseManager(Context c) {
        this.context = c;
    }

    public DatabaseManager open() throws SQLException {
        this.dbHelper = new SQLiteHelper(this.context);
        this.database = this.dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        this.dbHelper.close();
    }

    public void insert(String name,String category,int amount) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(SQLiteHelper.COL_2, name);
        contentValue.put(SQLiteHelper.COL_3, category);
        contentValue.put(SQLiteHelper.COL_4, amount);
        this.database.insert(SQLiteHelper.TABLE_NAME, null, contentValue);
    }


    public Cursor fetch() {
        Cursor cursor = this.database.query(SQLiteHelper.TABLE_NAME, new String[]{SQLiteHelper.COL_1, SQLiteHelper.COL_2, SQLiteHelper.COL_3, SQLiteHelper.COL_4}, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }




    public int update(int id, String name, String category,int amount) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLiteHelper.COL_2, name);
        contentValues.put(SQLiteHelper.COL_3, category);
        contentValues.put(SQLiteHelper.COL_4, amount);
        return this.database.update(SQLiteHelper.TABLE_NAME, contentValues, "id = " + id, null);
    }

    public void delete(int id) {
        this.database.delete(SQLiteHelper.TABLE_NAME, "id=" + id, null);
    }
}