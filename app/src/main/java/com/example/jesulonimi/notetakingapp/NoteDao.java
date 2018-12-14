package com.example.jesulonimi.notetakingapp;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    void insert(Note note);

    @Delete
    void delete(Note note);

    @Update
    void update(Note note);

    @Query("DELETE FROM NOTE_TABLE")
    void deleteAll();

    @Query("SELECT * FROM NOTE_TABLE ORDER BY PRIORITY DESC")
    LiveData<List<Note>> getAllNotes();


}
