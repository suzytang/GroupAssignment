package com.example.groupassignment;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.groupassignment.ui.shop.Shop;

import java.util.ArrayList;


public class SQLiteHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "Inventory.db";
    public static final String TABLE_NAME = "inventory_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "CATEGORY";
    public static final String COL_4 = "AMOUNT";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    final Shop shop = new Shop();
    ArrayList<Shop> accessories = new ArrayList<>();

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,CATEGORY TEXT,AMOUNT INTEGER)");
        db.execSQL("insert into "+TABLE_NAME +" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (1,'Coins','Coins',100)");
        db.execSQL("insert into "+TABLE_NAME +" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (2,'Food','Food',0)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public void insert(String name,String category,int amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(SQLiteHelper.COL_2, name);
        contentValue.put(SQLiteHelper.COL_3, category);
        contentValue.put(SQLiteHelper.COL_4, amount);
        db.insert(SQLiteHelper.TABLE_NAME, null, contentValue);
    }


    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }


    public int update(int id, String name, String category,int amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLiteHelper.COL_2, name);
        contentValues.put(SQLiteHelper.COL_3, category);
        contentValues.put(SQLiteHelper.COL_4, amount);
        return db.update(SQLiteHelper.TABLE_NAME, contentValues, "id = " + id, null);
    }


    public String getData(String column, int i) {
        SQLiteDatabase db = this.getWritableDatabase();
        String data;
        Cursor csr = db.rawQuery("select "+column+" from "+SQLiteHelper.TABLE_NAME+" where " +SQLiteHelper.COL_1+ " = "+i,null);
        csr.moveToFirst();
        data = csr.getString(csr.getColumnIndex(column));
        return data;
    }

    public int getItem(String column, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        int data;
        Cursor csr = db.rawQuery("select "+column+" from "+SQLiteHelper.TABLE_NAME+" where " +SQLiteHelper.COL_2+ " = "+name,null);
        csr.moveToFirst();
        data = csr.getInt(csr.getColumnIndex(column));
        return data;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }

    public String getTableAsString(SQLiteDatabase db, String tableName) {
        String tableString = String.format("Table %s:\n", tableName);
        Cursor allRows  = db.rawQuery("SELECT * FROM " + tableName, null);
        if (allRows.moveToFirst() ){
            String[] columnNames = allRows.getColumnNames();
            do {
                for (String name: columnNames) {
                    tableString += String.format("%s: %s\n", name,
                            allRows.getString(allRows.getColumnIndex(name)));
                }
                tableString += "\n";

            } while (allRows.moveToNext());
        }

        return tableString;
    }
}
