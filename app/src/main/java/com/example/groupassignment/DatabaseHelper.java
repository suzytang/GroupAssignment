package com.example.groupassignment;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.concurrent.ExecutionException;

/**
 * Created by ProgrammingKnowledge on 4/3/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "46382348945390339238.db";
    private static final int DATABASE_VERSION = 1;
    public static final String COL_1 = "Level";
    public static final String COL_2 = "Position";
    public static final String COL_3 = "Expression";
    public static final String COL_4 = "Translation";
    public static final String COL_5 = "Answered";
    public static final String COL_6 = "Completed";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table learn_table (ID INTEGER PRIMARY KEY AUTOINCREMENT,Level INTEGER,Position INTEGER, Expression TEXT, Translation TEXT, Answered INT, Completed INT)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (1, 1, 'Hello', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (1, 2, 'Goodbye', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (1, 3, 'How are you?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (1, 4, 'Good', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (1, 5, 'Bad', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (1, 6, 'Please', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (1, 7, 'Thank you', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (1, 8, 'You’re welcome', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (1, 9, 'Excuse me', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (1, 10, 'I’m sorry', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (2, 1, 'Yes', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (2, 2, 'No', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (2, 3, 'What’s your name?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (2, 4, 'My name is…', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (2, 5, 'Nice to meet you', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (2, 6, 'Where are you from?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (2, 7, 'I’m from', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (2, 8, 'Australia', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (2, 9, 'New Zealand', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (2, 10, 'Overseas', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (3, 1, 'Do you speak English?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (3, 2, 'I don’t understand', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (3, 3, 'I speak a little…', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (3, 4, 'Could you translate?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (3, 5, 'Could you please speak a little slower?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (3, 6, 'Could you write that down?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (3, 7, 'Could you repeat that?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (3, 8, 'How do you say…?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (3, 9, 'What does… mean?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (3, 10, 'I appreciate this', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (4, 1, 'Cheap', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (4, 2, 'Expensive', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (4, 3, 'Cost', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (4, 4, 'Price', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (4, 5, 'Fee', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (4, 6, 'This is too…', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (4, 7, 'How much does this cost?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (4, 8, 'Could I see this one?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (4, 9, 'I’ll give you… for it', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (4, 10, 'Where can I exchange money?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (5, 1, 'Bus', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (5, 2, 'Train', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (5, 3, 'Plane', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (5, 4, 'Taxi', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (5, 5, 'What time does the…arrive?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (5, 6, 'What time does the…depart?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (5, 7, 'Is this seat taken?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (5, 8, 'When is the next…?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (5, 9, 'Could you call me a taxi?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (5, 10, 'I’d like to go to…', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (6, 1, 'Restaurant', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (6, 2, 'Eat', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (6, 3, 'Breakfast', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (6, 4, 'Lunch', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (6, 5, 'Dinner', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (6, 6, 'Drink', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (6, 7, 'What would you recommend?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (6, 8, 'Could I see the menu?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (6, 9, 'Could I get the bill?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (6, 10, 'That was delicious', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (7, 1, 'Left', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (7, 2, 'Right', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (7, 3, 'Forward', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (7, 4, 'Backward', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (7, 5, 'I am lost', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (7, 6, 'How do I get to…?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (7, 7, 'How far is…?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (7, 8, 'Can you show me the way to…?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (7, 9, 'Do you have a map?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (7, 10, 'Where can I find tourist information?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (8, 1, 'I have a reservation.', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (8, 2, 'Room', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (8, 3, 'Reservation', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (8, 4, 'Available', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (8, 5, 'Free', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (8, 6, 'Do you have any rooms available?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (8, 7, 'Could I see the room?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (8, 8, 'I’d like to stay for… nights.', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (8, 9, 'Is breakfast included?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (8, 10, 'Could I get a different room?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (9, 1, 'Doctor', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (9, 2, 'Hospital', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (9, 3, 'Police', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (9, 4, 'Ambulance', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (9, 5, 'Firefighter', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (9, 6, 'I need help', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (9, 7, 'I need a...', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (9, 8, 'Call the…', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (9, 9, 'Can I use your phone?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (9, 10, 'Leave me alone', null, 0, 0)");

        String result = "";

        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 11; j++) {
                String word = getEnglish(db, i, j);
                db.execSQL("update learn_table set " + COL_4 + " = '" + word + "' where "+COL_1+" = "+i+" AND "+COL_2+" = "+j);
//                TranslateRequest tR = new TranslateRequest();
//                try {
//                    result = tR.execute(getEnglish(db, i, j)).get();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                String resultFormatted = result.replace("'", "''");
//                db.execSQL("update learn_table set " + COL_4 + " = '" + resultFormatted + "' where "+COL_1+" = "+i+" AND "+COL_2+" = "+j);
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS learn_table");
        onCreate(db);
    }
//    //
//    public Cursor getAllData(String table) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor csr = db.rawQuery("select * from "+table,null);
//        if (csr != null) {
//            csr.moveToFirst();
//        }
//        return csr;
//    }

//    public boolean empty() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        String count = "SELECT count(*) FROM user_table";
//        Cursor csr = db.rawQuery(count, null);
//        csr.moveToFirst();
//        int i = csr.getInt(0);
//        if (i > 0) {
//            return false;
//        } else {
//            return true;
//        }
//    }
//
//    public String getData(SQLiteDatabase db, String table, String column, int i) {
//        String word;
//        Cursor csr = db.rawQuery("select "+column+" from "+table+" where ID = "+i,null);
//        csr.moveToFirst();
//        word = csr.getString(csr.getColumnIndex(column));
//        return word;
//    }

    public String getEnglish(SQLiteDatabase db, int level, int position) {
        String word;
        Cursor csr = db.rawQuery("select "+COL_3+" from learn_table where "+COL_1+" = "+level+" AND "+COL_2+" = "+position,null);
        csr.moveToFirst();
        word = csr.getString(csr.getColumnIndex(COL_3));
        return word;
    }

    public boolean answered(int level, int position) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor csr = db.rawQuery("select "+COL_5+" from learn_table where "+COL_1+" = "+level+" AND "+COL_2+" = "+position,null);
        csr.moveToFirst();
        if (csr.getInt(csr.getColumnIndex(COL_5)) == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void setAnswered(int level, int position)  {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update learn_table set "+COL_5+" = 1 where "+COL_1+" = "+level+" AND "+COL_2+" = "+position);
        db.close();
    }

    public boolean completed(int level) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor csr = db.rawQuery("select "+COL_6+" from learn_table where "+COL_1+" = "+level,null);
        csr.moveToFirst();
        if (csr.getInt(csr.getColumnIndex(COL_6)) == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void setCompleted(int level)  {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update learn_table set "+COL_6+" = 1 where "+COL_1+" = "+level);
        db.close();
    }

    public String pullData(String column, int level, int position) {
        SQLiteDatabase db = this.getWritableDatabase();
        String word;
        Cursor csr = db.rawQuery("select "+column+" from learn_table where "+COL_1+" = "+level+" AND "+COL_2+" = "+position,null);
        csr.moveToFirst();
        word = csr.getString(csr.getColumnIndex(column));
        return word;
    }

    public String pullRandom(String column, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String word;
        Cursor csr = db.rawQuery("select "+column+" from learn_table where ID = "+id,null);
        csr.moveToFirst();
        word = csr.getString(csr.getColumnIndex(column));
        return word;
    }

//    public void updateData(String column, String value, int i)  {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL("update learn_table set "+column+"='"+value+"' where ID = "+i);
//        db.close();
//    }

    public void storeUserData(String english, String translation) {
        SQLiteDatabase db = this.getWritableDatabase();
        int i = countUserData() + 1;
        String translationFormatted = translation.replace("'","''");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_5+") VALUES (0, "+i+", '"+english+"', '"+translationFormatted+"' , 0, null)");
        db.close();
    }

    public int countUserData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor csr = db.rawQuery("select * from learn_table where "+COL_1+" = 0", null);
        int rows = csr.getCount();
        return rows;
    }

    public int countData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor csr = db.rawQuery("select * from learn_table", null);
        int rows = csr.getCount();
        return rows;
    }

    public boolean dataExists(String check) {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean exists = false;
        Cursor csr = db.rawQuery("select "+COL_3+" from learn_table where Level = 0",null);
        while (csr.moveToNext()) {
            if (check.toLowerCase().equals(csr.getString(0).toLowerCase())) {
                exists = true;
            }
        }
        return exists;
    }
}