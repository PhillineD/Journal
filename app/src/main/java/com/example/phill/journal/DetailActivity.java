package com.example.phill.journal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

//show the full contents of a journal entry in detail_activity
public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState );
        setContentView(R.layout.detail_activity);

        // get the info
        Intent detailintent = getIntent();
        String title = (String) detailintent.getSerializableExtra("title");
        String content = (String) detailintent.getSerializableExtra("content");
        String mood = (String) detailintent.getSerializableExtra("mood");
        String timestamp = (String) detailintent.getSerializableExtra("timestamp");

        // put info in views
        TextView viewtitle = this.findViewById(R.id.title);
        viewtitle.setText(title);

        TextView viewcontent = this.findViewById(R.id.content);
        viewcontent.setText(content);

        TextView viewmood = this.findViewById(R.id.mood);
        viewmood.setText(mood);

        TextView viewtimestamp = findViewById(R.id.timestamp);
        viewtimestamp.setText(timestamp);


    }
}


