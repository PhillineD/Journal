package com.example.phill.journal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_activity);

        Button ok = findViewById(R.id.ok);

        // when clicked on OK, call class OkClicked
        ok.setOnClickListener(new OkClicked());

    }


    private class OkClicked implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            EditText Etitle = findViewById(R.id.nieuw);
            EditText Emood = findViewById(R.id.mood);
            EditText Econtent = findViewById(R.id.content);

            //  new instance is filled
            addEntry((Etitle.getText().toString()), Emood.getText().toString(), Econtent.getText().toString());

            // go back to MainActivity
            Intent intent = new Intent(InputActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    // when clicked on ok, make a new instance en insert it to database
    public void addEntry(String title, String mood, String content) {
        Journal_Entry entry = new Journal_Entry(title, content, mood);
        EntryDatabase.getInstance(this).insert(entry);
    }







}

//  should allow the user to input the contents of the journal entry