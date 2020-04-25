package com.example.groupassignment;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.concurrent.ExecutionException;

// The following code is modified from: Programming Knowledge (2015)
// Android SQLite Database Tutorial 5
// http://programmingknowledgeblog.blogspot.com/2015/05/android-sqlite-database-tutorial-5.html

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "2kal23445878567843sgjdbogw.db";
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
        db.execSQL("create table language_table (ID INTEGER PRIMARY KEY AUTOINCREMENT,Language TEXT,Code TEXT, Applied INT)");
        db.execSQL("insert into language_table ('Language', 'Code', 'Applied') VALUES ('Chinese', 'en-zh', 0)");
        db.execSQL("insert into language_table ('Language', 'Code', 'Applied') VALUES ('Korean', 'en-ko', 0)");
        db.execSQL("insert into language_table ('Language', 'Code', 'Applied') VALUES ('Japanese', 'en-ja', 0)");
        db.execSQL("insert into language_table ('Language', 'Code', 'Applied') VALUES ('German', 'en-de', 0)");
        db.execSQL("insert into language_table ('Language', 'Code', 'Applied') VALUES ('French', 'en-fr', 0)");
        db.execSQL("insert into language_table ('Language', 'Code', 'Applied') VALUES ('Italian', 'en-it', 0)");
        db.execSQL("insert into language_table ('Language', 'Code', 'Applied') VALUES ('English', 'en-en', 0)");

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
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (2, 4, 'My name is...', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (2, 5, 'Nice to meet you', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (2, 6, 'Where are you from?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (2, 7, 'I’m from', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (2, 8, 'Australia', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (2, 9, 'New Zealand', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (2, 10, 'Overseas', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (3, 1, 'Do you speak English?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (3, 2, 'I don’t understand', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (3, 3, 'I speak a little...', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (3, 4, 'Could you translate?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (3, 5, 'Could you please speak a little slower?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (3, 6, 'Could you write that down?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (3, 7, 'Could you repeat that?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (3, 8, 'How do you say...?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (3, 9, 'What does...mean?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (3, 10, 'I appreciate this', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (4, 1, 'Cheap', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (4, 2, 'Expensive', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (4, 3, 'Cost', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (4, 4, 'Price', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (4, 5, 'Fee', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (4, 6, 'This is too...', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (4, 7, 'How much does this cost?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (4, 8, 'Could I see this one?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (4, 9, 'I’ll give you...for it', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (4, 10, 'Where can I exchange money?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (5, 1, 'Bus', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (5, 2, 'Train', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (5, 3, 'Plane', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (5, 4, 'Taxi', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (5, 5, 'What time does the...arrive?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (5, 6, 'What time does the...depart?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (5, 7, 'Is this seat taken?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (5, 8, 'When is the next...?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (5, 9, 'Could you call me a taxi?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (5, 10, 'I’d like to go to...', null, 0, 0)");
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
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (7, 6, 'How do I get to...?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (7, 7, 'How far is...?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (7, 8, 'Can you show me the way to...?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (7, 9, 'Do you have a map?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (7, 10, 'Where can I find tourist information?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (8, 1, 'I have a reservation.', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (8, 2, 'Room', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (8, 3, 'Reservation', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (8, 4, 'Available', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (8, 5, 'Free', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (8, 6, 'Do you have any rooms available?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (8, 7, 'Could I see the room?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (8, 8, 'I’d like to stay for...nights.', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (8, 9, 'Is breakfast included?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (8, 10, 'Could I get a different room?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (9, 1, 'Doctor', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (9, 2, 'Hospital', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (9, 3, 'Police', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (9, 4, 'Ambulance', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (9, 5, 'Firefighter', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (9, 6, 'I need help', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (9, 7, 'I need a...', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (9, 8, 'Call the...', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (9, 9, 'Can I use your phone?', null, 0, 0)");
        db.execSQL("insert into learn_table ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+", "+COL_6+") VALUES (9, 10, 'Leave me alone', null, 0, 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS learn_table");
        db.execSQL("DROP TABLE IF EXISTS language_table");
        onCreate(db);
    }
    // Modified code stops here

    public void dbClean(){
        SQLiteDatabase db = this.getWritableDatabase();
        int ID = getID();
        switch (ID){
            // Korean
            case 2:
                db.execSQL("update learn_table set Translation = '아니' where Expression = 'No'");
                db.execSQL("update learn_table set Translation = '만나서 반갑습니다' where Expression = 'Nice to meet you'");
                db.execSQL("update learn_table set Translation = '나는 조금 말한다' where Expression = 'I speak a little...'");
                db.execSQL("update learn_table set Translation = '어떻게 가나 요' where Expression = 'How do I get to...?'");
                db.execSQL("update learn_table set Translation = '나는 머물고 싶다...박' where Expression = 'I’d like to stay for...nights.'");
                break;
            // Japanese
            case 3:
                db.execSQL("update learn_table set Translation = 'いいえ' where Expression = 'No'");
                break;
            // German
            case 4:
                db.execSQL("update learn_table set Translation = 'Auf Wiedersehen' where Expression = 'Goodbye'");
                break;
            // French
            case 5:
                db.execSQL("update learn_table set Translation = 'S''il vous plaît' where Expression = 'Please'");
                db.execSQL("update learn_table set Translation = 'Autobus' where Expression = 'Bus'");
                db.execSQL("update learn_table set Translation = 'Avion' where Expression = 'Plane'");
                break;
            // Italian
            case 6:
                db.execSQL("update learn_table set Translation = 'Piacere di conoscerti' where Expression = 'Nice to meet you'");
                db.execSQL("update learn_table set Translation = 'Lei parl inglese' where Expression = 'Do you speak English?'");
                db.execSQL("update learn_table set Translation = 'Lasciami solo' where Expression = 'Leave me alone'");
                break;
        }
    }

    public void resetLearnData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update learn_table set "+COL_6+" = " +0);
        db.execSQL("update learn_table set "+COL_5+" = " +0);
        if (countUserData() > 0) {
            db.execSQL("delete from learn_table where " + COL_1 + " = 0");
        }
        db.close();
    }

    public void setLanguage(String language)  {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update language_table set Applied = " +0);
        db.execSQL("update language_table set Applied = " +1+ " where Language = "+"'"+language+"'");
        db.close();
    }

    public boolean checkCurrent(String language) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor csr = db.rawQuery("select Language from language_table where Applied = "+1,null);
        csr.moveToFirst();
        try {
            if (csr.getString(csr.getColumnIndex("Language")).equalsIgnoreCase(language)){
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public String getCode()  {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor csr = db.rawQuery("select Code from language_table where Applied = "+1,null);
        csr.moveToFirst();
        String code = csr.getString(csr.getColumnIndex("Code"));
        return code;
    }

    public int getID()  {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor csr = db.rawQuery("select ID from language_table where Applied = "+1,null);
        csr.moveToFirst();
        int ID = csr.getInt(csr.getColumnIndex("ID"));
        return ID;
    }

    public boolean translated() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor csr = db.rawQuery("select "+COL_4+" from learn_table", null);
        csr.moveToFirst();
        try {
            if(csr.getString(csr.getColumnIndex(COL_4)).isEmpty()){
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public String getEnglish(int level, int position) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor csr = db.rawQuery("select "+COL_3+" from learn_table where "+COL_1+" = "+level+" AND "+COL_2+" = "+position,null);
        try {
            String word = "";
            if (csr.moveToFirst()) {
                word = csr.getString(csr.getColumnIndex(COL_3));
            }
            return word;
        }
        finally {
            if (csr != null) {
                csr.close();
            }
        }
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

    public void setTranslation(String translation ,int level, int position) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update learn_table set " + COL_4 + " = '" + translation + "' where "+COL_1+" = "+level+" AND "+COL_2+" = "+position);
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