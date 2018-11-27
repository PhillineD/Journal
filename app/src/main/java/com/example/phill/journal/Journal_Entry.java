package com.example.phill.journal;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

public class Journal_Entry implements Serializable {

    private  String title, content, mood , Id;
    private Timestamp Timestamp;

    //  hold the data of our journal entries
    public Journal_Entry( String Title, String Content, String Mood){
        title = Title;
        content = Content;
        mood = Mood;
    }

    // getter for all
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getMood() {
        return mood;
    }

    public String getId() {
        return Id;
    }

    public java.sql.Timestamp getTimestamp() {
        return Timestamp;
    }


    // setter for all
    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setTimestamp(java.sql.Timestamp timestamp) {
        Timestamp = timestamp;
    }
}
