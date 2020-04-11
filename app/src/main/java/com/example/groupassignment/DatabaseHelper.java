package com.example.groupassignment;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.concurrent.ExecutionException;

/**
 * Created by ProgrammingKnowledge on 4/3/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "463232243324322242387238348792389323423423223498323487239842234287329823892379238347928379238.db";
    private static final int DATABASE_VERSION = 1;
    public static final String COL_1 = "Level";
    public static final String COL_2 = "Position";
    public static final String COL_3 = "Expression";
    public static final String COL_4 = "Translation";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table learn_table (ID INTEGER PRIMARY KEY AUTOINCREMENT,Level INTEGER,Position INTEGER, Expression TEXT, Translation TEXT)");
        db.execSQL("create table user_table (ID INTEGER PRIMARY KEY AUTOINCREMENT,Expression TEXT, Translation TEXT)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (1, 1, 'Hello', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (1, 2, 'Goodbye', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (1, 3, 'How are you?', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (1, 4, 'Good', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (1, 5, 'Bad', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (1, 6, 'Please', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (1, 7, 'Thank you', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (1, 8, 'You’re welcome', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (1, 9, 'Excuse me', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (1, 10, 'I’m sorry', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (2, 1, 'Yes', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (2, 2, 'No', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (2, 3, 'What’s your name?', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (2, 4, 'My name is…', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (2, 5, 'Nice to meet you', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (2, 6, 'Where are you from?', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (2, 7, 'I’m from', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (2, 8, 'Australia', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (2, 9, 'New Zealand', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (2, 10, 'Overseas', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (2, 1, 'Do you speak English?', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (3, 2, 'I don’t understand', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (3, 3, 'I speak a little…', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (3, 4, 'Could you translate?', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (3, 5, 'Could you please speak a little slower?', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (3, 6, 'Could you write that down?', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (3, 7, 'Could you repeat that?', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (3, 8, 'How do you say…?', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (3, 9, 'What does… mean?', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (3, 10, 'I appreciate this', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (3, 1, 'Cheap', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (3, 2, 'Expensive', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (4, 3, 'Cost', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (4, 4, 'Price', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (4, 5, 'Fee', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (4, 6, 'This is too…', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (4, 7, 'How much does this cost?', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (4, 8, 'Could I see this one?', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (4, 9, 'I’ll give you… for it', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (4, 10, 'Where can I exchange money?', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (4, 1, 'Bus', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (4, 2, 'Train', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (4, 3, 'Plane', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (5, 4, 'Taxi', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (5, 5, 'What time does the…arrive?', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (5, 6, 'What time does the…depart?', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (5, 7, 'Is this seat taken?', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (5, 8, 'When is the next…?', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (5, 9, 'Could you call me a taxi?', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (5, 10, 'I’d like to go to…', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (5, 1, 'Restaurant', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (5, 2, 'Eat', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (5, 3, 'Breakfast', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (5, 4, 'Lunch', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (6, 5, 'Dinner', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (6, 6, 'Drink', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (6, 7, 'What would you recommend?', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (6, 8, 'Could I see the menu?', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (6, 9, 'Could I get the bill?', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (6, 10, 'That was delicious', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (6, 1, 'Left', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (6, 2, 'Right', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (6, 3, 'Forward', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (6, 4, 'Backward', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (6, 5, 'I am lost', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (7, 6, 'How do I get to…?', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (7, 7, 'How far is…?', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (7, 8, 'Can you show me the way to…?', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (7, 9, 'Do you have a map?', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (7, 10, 'Where can I find tourist information?', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (7, 1, 'I have a reservation.', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (7, 2, 'Room', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (7, 3, 'Reservation', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (7, 4, 'Available', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (7, 5, 'Free', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (7, 6, 'Do you have any rooms available?', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (8, 7, 'Could I see the room?', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (8, 8, 'I’d like to stay for… nights.', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (8, 9, 'Is breakfast included?', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (8, 10, 'Could I get a different room?', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (8, 1, 'Doctor', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (8, 2, 'Hospital', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (8, 3, 'Police', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (8, 4, 'Ambulance', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (8, 5, 'Firefighter', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (8, 6, 'I need help', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (8, 7, 'I need a...', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (9, 8, 'Call the…', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (9, 9, 'Can I use your phone?', null)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+") VALUES (9, 10, 'Leave me alone', null)");

        String result = "";
        for (int i = 1; i < 91; i++) {
            TranslateRequest tR = new TranslateRequest();
            try {
                result = tR.execute(getData(db, "learn_table", COL_3, i)).get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String resultFormatted = result.replace("'","''");
            db.execSQL("update learn_table set " +COL_4+ " = '"+resultFormatted+"' where ID = "+i);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS learn_table");
        db.execSQL("DROP TABLE IF EXISTS user_table");
        onCreate(db);
    }
//
//    public Cursor getAllData() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor res = db.rawQuery("select * from learn_table",null);
//        if (res != null) {
//            res.moveToFirst();
//        }
//        return res;
//    }

    public String getData(SQLiteDatabase db, String table, String column, int i) {
        String word;
        Cursor csr = db.rawQuery("select "+column+" from "+table+" where ID = "+i,null);
        csr.moveToFirst();
        word = csr.getString(csr.getColumnIndex(column));
        return word;
    }

    public String pullData(String table, String column, int level, int position) {
        SQLiteDatabase db = this.getWritableDatabase();
        String word;
        Cursor csr = db.rawQuery("select "+column+" from "+table+" where "+COL_1+" = "+level+" AND "+COL_2+" = "+position,null);
        csr.moveToFirst();
        word = csr.getString(csr.getColumnIndex(column));
        return word;
    }

    public String pullAllData(String table, String column, int position) {
        SQLiteDatabase db = this.getWritableDatabase();
        String word;
        Cursor csr = db.rawQuery("select "+column+" from "+table+" where ID = "+position,null);
        csr.moveToFirst();
        word = csr.getString(csr.getColumnIndex(column));
        return word;
    }

    public void updateData(String table, String column, String value, int i)  {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update "+table+" set "+column+"='"+value+"' where ID = "+i);
        db.close();
    }
}