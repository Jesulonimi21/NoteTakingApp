package com.example.jesulonimi.notetakingapp;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {
    private String title;
    private String description;
    private int Priority;
    @PrimaryKey(autoGenerate = true)
    private int id;

    public Note() {

    }

    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        Priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {

        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return Priority;
    }

    public void setPriority(int priority) {
        Priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
