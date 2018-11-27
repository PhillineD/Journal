package com.example.phill.journal;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class EntryDatabase extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "entries";

    //    the unique instance of the class is stored
    private static EntryDatabase instance;

    // contructor for class
    private EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name , factory, version );
    }

    public static EntryDatabase getInstance(Context context){

        //if available
        if(EntryDatabase.instance == null){
             EntryDatabase.instance = new EntryDatabase(context,TABLE_NAME, null ,1);
        }
        return EntryDatabase.instance;

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //  creates a table
        String  message = "CREATE TABLE " + TABLE_NAME + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT, mood TEXT , " +
                "timestamp DATETIME default (datetime('now','localtime'))) ";
        db.execSQL(message);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//          drops the entries table
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);

//        recreates it by calling
        onCreate(db );
    }

    // select all info from database
    public static Cursor selectAll(EntryDatabase instance){
        SQLiteDatabase database = instance.getWritableDatabase();
        return database.rawQuery("SELECT * FROM "+ TABLE_NAME, null );
    }


    public void insert(Journal_Entry newjournal){

        // open a connection to the database
        SQLiteDatabase database = instance.getWritableDatabase();

        // create a new ContentValues object
        ContentValues contentvalue = new ContentValues();

        // add values for title, content and mood.
        contentvalue.put("title", newjournal.getTitle());
        contentvalue.put("content", newjournal.getContent());
        contentvalue.put("mood", newjournal.getMood());

        // call insert and add the right parameters
        database.insert(TABLE_NAME, null, contentvalue);

    }

    // detele a part of database with specific id
    public static void delete(long id){
        SQLiteDatabase db = instance.getWritableDatabase();
        db.delete(TABLE_NAME, "_id= " + id, null);
    }
}
