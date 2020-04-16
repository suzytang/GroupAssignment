package com.example.groupassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;


public class SQLiteHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "32294823482577542136976643437637955758.db";
    public static final String TABLE_NAME = "inventory_table";
    public static final String PET_TABLE = "pet_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "CATEGORY";
    public static final String COL_4 = "AMOUNT";
    public static final String ID = "ID";
    public static final String STATUS = "STATUS";
    public static final String MOOD = "MOOD";
    public static final String EXP = "EXP";
    public static final String LVL = "LVL";
    public static final String TIME = "TIME";
    public static final String APPLIED = "APPLIED";



    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,CATEGORY TEXT,AMOUNT INTEGER,APPLIED INTEGER)");
        db.execSQL("insert into "+TABLE_NAME +" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+APPLIED+") VALUES (1,'Coins','Coins',10000, 0)");
        db.execSQL("insert into "+TABLE_NAME +" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+APPLIED+") VALUES (2,'Food','Food',0, 0)");
        db.execSQL("insert into "+TABLE_NAME +" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+APPLIED+") VALUES (3,'Sunglasses','Accessories',0, 0)");
        db.execSQL("insert into "+TABLE_NAME +" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+APPLIED+") VALUES (4,'Cap','Accessories',0, 0)");
        db.execSQL("insert into "+TABLE_NAME +" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+APPLIED+") VALUES (5,'Top Hat','Accessories',0, 0)");
        db.execSQL("insert into "+TABLE_NAME +" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+APPLIED+") VALUES (6,'Glasses','Accessories',0, 0)");
        db.execSQL("insert into "+TABLE_NAME +" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+APPLIED+") VALUES (7,'Pirate Hat','Accessories',0, 0)");
        db.execSQL("insert into "+TABLE_NAME +" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+APPLIED+") VALUES (8,'Wig','Accessories',0, 0)");
        db.execSQL("insert into "+TABLE_NAME +" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+APPLIED+") VALUES (9,'Striped','Wallpapers',0, 0)");
        db.execSQL("insert into "+TABLE_NAME +" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+APPLIED+") VALUES (10,'Polka Dots','Wallpapers',0, 0)");
        db.execSQL("insert into "+TABLE_NAME +" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+APPLIED+") VALUES (11,'Pink','Wallpapers',0, 0)");
        db.execSQL("insert into "+TABLE_NAME +" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+APPLIED+") VALUES (12,'Black','Wallpapers',0, 0)");
        db.execSQL("insert into "+TABLE_NAME +" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+APPLIED+") VALUES (13,'Red','Wallpapers',0, 0)");
        db.execSQL("insert into "+TABLE_NAME +" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+APPLIED+") VALUES (14,'Green','Wallpapers',0, 0)");


        db.execSQL("create table " + PET_TABLE +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,STATUS TEXT,MOOD TEXT, " +
                "EXP INTEGER, LVL INTEGER, TIME INTEGER)");
        db.execSQL("insert into "+PET_TABLE +" ("+ID+", "+STATUS+", "+MOOD+", "+EXP+", "+LVL+", "+TIME+") " +
                "VALUES (1, 'Hungry','Happy', 0, 1, 0)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }


    public void updateData(String column, int value, int i)  {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update "+TABLE_NAME+" set "+column+"='"+value+"' where ID = "+i);
        db.close();
    }

    public void applyInventory(String name, String category)  {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update "+TABLE_NAME+" set "+APPLIED+" = " +0+ " where CATEGORY = "+category);
        db.execSQL("update "+TABLE_NAME+" set "+APPLIED+" = " +1+ " where NAME = "+name+ " AND CATEGORY = "+category);
        db.close();
    }

    public void updatePetData(String column, String value, int i)  {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update "+PET_TABLE+" set "+column+"='"+value+"' where ID = "+i);
        db.close();
    }

    public void updatePetTime(String column, long value, int i)  {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update "+PET_TABLE+" set "+column+"='"+value+"' where ID = "+i);
        db.close();
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
        Cursor csr = db.rawQuery("select "+column+" from "+TABLE_NAME+" where " +COL_1+ " = "+i,null);
        csr.moveToFirst();
        data = csr.getString(csr.getColumnIndex(column));
        return data;
    }

    public String getInventory(String column, String category){
        SQLiteDatabase db = this.getWritableDatabase();
        String data;
        Cursor csr = db.rawQuery("select " +column+ " from "+TABLE_NAME+" WHERE CATEGORY = " +category+ " AND APPLIED = "+1, null);
        csr.moveToFirst();
        data = csr.getString(csr.getColumnIndex(column));
        return data;
    }

    public int getSum(String category){
        SQLiteDatabase db = this.getWritableDatabase();
        int data;
        Cursor csr = db.rawQuery("select SUM(AMOUNT) from "+TABLE_NAME+" WHERE CATEGORY = " +category, null);
        csr.moveToFirst();
        data = csr.getInt(0);
        return data;
    }

    public String getPetData(String column) {
        SQLiteDatabase db = this.getWritableDatabase();
        String data;
        Cursor csr = db.rawQuery("select "+column+" from "+PET_TABLE, null);
        csr.moveToFirst();
        data = csr.getString(csr.getColumnIndex(column));
        return data;
    }

    public long getPetTime(String column) {
        SQLiteDatabase db = this.getWritableDatabase();
        long data;
        Cursor csr = db.rawQuery("select "+column+" from "+PET_TABLE, null);
        csr.moveToFirst();
        data = csr.getLong(csr.getColumnIndex(column));
        return data;
    }


    public int getItem(String column, String where, String string){
        SQLiteDatabase db = this.getWritableDatabase();
        int data;
        Cursor csr = db.rawQuery("select "+column+" from "+TABLE_NAME+" where " +where+ " = "+string, null);
        csr.moveToFirst();
        data = csr.getInt(csr.getColumnIndex(column));

        return data;
    }


    /*public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }


    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }

    public void reset () throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL ("drop table "+TABLE_NAME);
        db.close ();
    }*/


}