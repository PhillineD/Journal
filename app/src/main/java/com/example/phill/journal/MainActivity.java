package com.example.phill.journal;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private EntryDatabase db;
    private EntryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // when clicked on the pink button call newEntryOnClickListener(), to go to inputActivity
        FloatingActionButton button = findViewById(R.id.floatingbutton);
        button.setOnClickListener(new newEntryOnClickListener());

        // when just clicked call EntryClickListener to go to DetailActivity
        ListView journallist = findViewById(R.id.listviewjournal);
        journallist.setOnItemClickListener(new EntryClickListener());

        // when long  clicked call EntryLongClickListener to go to delete that row
        journallist.setOnItemLongClickListener(new EntryLongClickListener());

        //  ensure that everything is in order
        db = EntryDatabase.getInstance(getApplicationContext());

        //  a list of Cursor objects in our adapter
        Cursor cursor = EntryDatabase.selectAll(db);
        adapter = new EntryAdapter(this, R.layout.entry_row, cursor);
        journallist.setAdapter(adapter);

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
    private class newEntryOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intentInput = new Intent(MainActivity.this, InputActivity.class);
            startActivity(intentInput);
        }
    }

    // when user click to add new item to the journal
    private class EntryClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Cursor clicked = (Cursor) adapterView.getItemAtPosition(i);
            String title = clicked.getString(clicked.getColumnIndex("title"));
            String content = clicked.getString(clicked.getColumnIndex("content"));
            String mood = clicked.getString(clicked.getColumnIndex("mood"));
            String timestamp = clicked.getString(clicked.getColumnIndex("timestamp"));

            Intent intent = new Intent(MainActivity.this , DetailActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("content", content);
            intent.putExtra("timestamp", timestamp);
            intent.putExtra("mood", mood);
            startActivity(intent);
        }
    }

    // when user clicked long on the listview, delete it
    private class EntryLongClickListener implements AdapterView.OnItemLongClickListener{

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
