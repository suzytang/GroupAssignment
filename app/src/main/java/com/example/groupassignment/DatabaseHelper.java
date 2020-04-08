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
    public static final String DATABASE_NAME = "groupassignment.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "learn_table";
    public static final String COL_1 = "Level";
    public static final String COL_2 = "Category";
    public static final String COL_3 = "Type";
    public static final String COL_4 = "Expression";
    public static final String COL_5 = "Translation";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL ("create table "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,Level INTEGER,Category TEXT,Type INTEGER, Expression TEXT, Translation TEXT)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (1,'General',0,'Hello',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (1,'General',0,'Goodbye',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (1,'General',1,'How are you? ',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (1,'General',0,'Good',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (1,'General',0,'Bad',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (1,'General',0,'Please',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (1,'General',0,'Thank you',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (1,'General',0,'You’re Welcome',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (1,'General',0,'Excuse me',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (1,'General',0,'I’m sorry',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (2,'Conversation',0,'Yes',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (2,'Conversation',0,'No',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (2,'Conversation',1,'What’s your name?',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (2,'Conversation',1,'My name is… ',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (2,'Conversation',1,'Nice to meet you',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (2,'Conversation',1,'Where are you from?',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (2,'Conversation',1,'I’m from',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (2,'Conversation',0,'Australia',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (2,'Conversation',0,'New Zealand',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (2,'Conversation',0,'Overseas ',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (3,'Understanding',1,'Do you speak English?',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (3,'Understanding',1,'I don’t understand',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (3,'Understanding',1,'I speak a little…',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (3,'Understanding',1,'Could you translate?',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (3,'Understanding',1,'Could you please speak a little slower?',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (3,'Understanding',1,'Could you write that down?',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (3,'Understanding',1,'Could you repeat that?',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (3,'Understanding',1,'How do you say…?',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (3,'Understanding',1,'What does… mean?',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (3,'Understanding',1,'I appreciate this',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (4,'Purchase',0,'Cheap',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (4,'Purchase',0,'Expensive',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (4,'Purchase',0,'Cost',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (4,'Purchase',0,'Price',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (4,'Purchase',0,'Fee',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (4,'Purchase',1,'This is too…',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (4,'Purchase',1,'How much does this cost?',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (4,'Purchase',1,'Could I see this one?',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (4,'Purchase',1,'I’ll give you… for it',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (4,'Purchase',1,'Where can I exchange money?',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (4,'Transport',0,'Bus',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (4,'Transport',0,'Train',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (4,'Transport',0,'Plane',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (4,'Transport',0,'Taxi',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (4,'Transport',1,'What time does the…arrive?',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (4,'Transport',1,'What time does the…depart?',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (4,'Transport',1,'Is this seat taken?',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (4,'Transport',1,'When is the next…?',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (4,'Transport',1,'Could you call me a taxi?',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (5,'Transport',1,'I’d like to go to…',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (5,'Food & Drinks',0,'Restaurant',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (5,'Food & Drinks',0,'Eat ',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (5,'Food & Drinks',0,'Breakfast',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (5,'Food & Drinks',0,'Lunch',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (5,'Food & Drinks',0,'Dinner ',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (5,'Food & Drinks',0,'Drink',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (5,'Food & Drinks',1,'What would you recommend?',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (5,'Food & Drinks',1,'Could I see the menu?',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (5,'Food & Drinks',1,'Could I get the bill?',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (5,'Food & Drinks',1,'That was delicious',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (6,'Directions',0,'Left ',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (6,'Directions',0,'Right',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (6,'Directions',0,'Forward ',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (6,'Directions',0,'Backward',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (6,'Directions',1,'I am lost',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (6,'Directions',1,'How do I get to…?',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (6,'Directions',1,'How far is…?',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (6,'Directions',1,'Can you show me the way to…?',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (6,'Directions',1,'Do you have a map?',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (6,'Directions',1,'Where can I find tourist information?',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (7,'Accomodation',1,'I have a reservation.',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (7,'Accomodation',0,'Room',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (7,'Accomodation',0,'Reservation',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (7,'Accomodation',0,'Available',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (7,'Accomodation',0,'Free',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (7,'Accomodation',1,'Do you have any rooms available?',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (7,'Accomodation',1,'Could I see the room?',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (7,'Accomodation',1,'I’d like to stay for… nights.',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (7,'Accomodation',1,'Is breakfast included?',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (7,'Accomodation',1,'Could I get a different room?',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (8,'Emergency',0,'Doctor',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (8,'Emergency',0,'Hospital',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (8,'Emergency',0,'Police',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (8,'Emergency',0,'Ambulance',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (8,'Emergency',0,'Firefighter',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (8,'Emergency',1,'I need help',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (8,'Emergency',1,'I need a...',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (8,'Emergency',1,'Call the…',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (8,'Emergency',1,'Can I use your phone?',null)");
        db.execSQL("insert into "+TABLE_NAME+" ("+COL_1+", "+COL_2+", "+COL_3+", "+COL_4+", "+COL_5+") VALUES (8,'Emergency',1,'Leave me alone',null)");

        String result = "";
        String word;
        for (int i = 1; i < 91; i++) {
            TranslateRequest tR = new TranslateRequest();
            try {
                result = tR.execute(getData(db, COL_4, i)).get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String resultFormatted = result.replace("'","''");
            db.execSQL("update "+TABLE_NAME +" set " +COL_5+ " = '"+resultFormatted+"' where ID = "+i);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME+"");
        onCreate(db);
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+"",null);
        return res;
    }

    public String getData(SQLiteDatabase db, String column, int i) {
        String word;
        Cursor csr = db.rawQuery("select "+column+" from "+TABLE_NAME+" where ID = "+i,null);
        csr.moveToFirst();
        word = csr.getString(csr.getColumnIndex(column));
        return word;
    }

    public String getString(String column, int i) {
        SQLiteDatabase db = this.getWritableDatabase();
        String word;
        Cursor csr = db.rawQuery("select "+column+" from "+TABLE_NAME+" where ID = "+i,null);
        csr.moveToFirst();
        word = csr.getString(csr.getColumnIndex(column));
        return word;
    }
}