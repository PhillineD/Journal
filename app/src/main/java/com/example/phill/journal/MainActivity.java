package com.example.phill.journal;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private EntryDatabase db;
    private EntryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView journallist = findViewById(R.id.listviewjournal);
        FloatingActionButton button = findViewById(R.id.floatingbutton);

        //  ensure that everything is in order
        db = EntryDatabase.getInstance(getApplicationContext());

        // when clicked on the pink button call class PinkClick(), to go to inputActivity
        button.setOnClickListener(new PinkClick());

        // when long clicked call class LongClick to go to delete that row
        journallist.setOnItemLongClickListener(new LongClick());

        // when just clicked call class ShortClick to go to DetailActivity
        journallist.setOnItemClickListener(new ShortClick());


        //  a list of Cursor objects in our adapter
        Cursor cursor = EntryDatabase.selectAll(db);
        adapter = new EntryAdapter(this, R.layout.entry_row, cursor);
        journallist.setAdapter(adapter);

        // add example
//        Journal_Entry example = new Journal_Entry("AppStudio","Pset6","Happy");
//        db.getInstance(this).insert(example);

    }

    // method to update every time we save something
    private  void updateData(){

        // get new cursor
        Cursor ncursor = EntryDatabase.selectAll(db);

        //  put in a new cursor for the updated data
        adapter.swapCursor(ncursor);
    }

    protected void onResume(){
        super.onResume();
        updateData();
    }

    // when clicked on the pink button go to inputActivity
    private class PinkClick implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intentInput = new Intent(MainActivity.this, InputActivity.class);
            startActivity(intentInput);
        }
    }

    // when user clicked short to go to the details
    private class ShortClick implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Cursor clicked = (Cursor) adapterView.getItemAtPosition(i);
            Intent intent = new Intent(MainActivity.this , DetailActivity.class);

            String title = clicked.getString(clicked.getColumnIndex("title"));
            intent.putExtra("title", title);

            String content = clicked.getString(clicked.getColumnIndex("content"));
            intent.putExtra("content", content);

            String mood = clicked.getString(clicked.getColumnIndex("mood"));
            intent.putExtra("mood", mood);

            String timestamp = clicked.getString(clicked.getColumnIndex("timestamp"));
            intent.putExtra("timestamp", timestamp);

            startActivity(intent);
        }
    }

    // when user clicked long on the listview, delete it
    private class LongClick implements AdapterView.OnItemLongClickListener{

        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            Cursor clicked = (Cursor) adapterView.getItemAtPosition(i);

            // get id form clicked data
            final long colomnindex = clicked.getLong(clicked.getColumnIndex("_id"));

            // delete that row from database
            EntryDatabase.delete(colomnindex);

            // database is updated
            updateData();
            return false;
        }
    }

}
