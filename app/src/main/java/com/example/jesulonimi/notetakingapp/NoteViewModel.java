package com.example.jesulonimi.notetakingapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
NoteRepository myRepository;
LiveData<List<Note>> allNotes;
    public NoteViewModel(@NonNull Application application) {
        super(application);
        myRepository=new NoteRepository(application);
        allNotes=myRepository.getAllNotes();
    }

    public void insert(Note note){
        myRepository.insert(note);
    }
    public void delete(Note note){
        myRepository.delete(note);
    }
    public void update(Note note){
        myRepository.update(note);
    }
    public void deleteAll(){
        myRepository.deleteAll();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}
