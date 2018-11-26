package com.example.phill.journal;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class EntryDatabase extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "entries";

    // contructor for class
    private EntryDatabase() {
        super();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //  creates a table
        String  message = "CREATE TABLE " + TABLE_NAME + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT, mood TEXT , " +
                "timestamp DATETIME default current_timestamp) ";
        db.execSQL(message);

//        creates sample items
        db.execSQL("INSERT INTO " + TABLE_NAME + "(title, content, mood)VALUES (\"1\",\"1\",\"1\" )");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//          drops the entries table
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);

//        recreates it by calling
        onCreate(db );
    }
}
