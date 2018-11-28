package com.example.phill.journal;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class EntryAdapter extends ResourceCursorAdapter {

    //    a constructor
    public EntryAdapter(Context context, int layout, Cursor cursor) {
        super(context, layout, cursor);
    }

//   Takes a View and fills the right elements with data from the cursor
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // Views and fills the right elements with data from the cursor for title
        TextView title = view.findViewById(R.id.Title);
        int indextitle = cursor.getColumnIndex("title");
        String texttitle = cursor.getString(indextitle);
        title.setText(texttitle);

        // Views and fills the right elements with data from the cursor for mood
        TextView mood = view.findViewById(R.id.mood);
        int indexmood = cursor.getColumnIndex("mood");
        String textmood = cursor.getString(indexmood);
        mood.setText(textmood);

        // Views and fills the right elements with data from the cursor for date
        TextView date = view.findViewById(R.id.date);
        int indexdate = cursor.getColumnIndex("timestamp");
        String textdate = cursor.getString(indexdate);
        date.setText(textdate);

    }
}
