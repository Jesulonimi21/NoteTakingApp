package com.example.jesulonimi.notetakingapp;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class NoteRepository {
NoteDao noteDao;
LiveData<List<Note>> allNotes;

public NoteRepository(Application application){
    NoteDatabase noteDatabase=NoteDatabase.getInstance(application);
    noteDao=noteDatabase.noteDao();
    allNotes=noteDao.getAllNotes();
}
public void insert(Note note){
new insertAsyncTask(noteDao).execute(note);
}

public void delete(Note note){
new deleteAsyncTask(noteDao).execute(note);
}
public void update(Note note){
new updateAsyncTask(noteDao).execute(note);
}
public void deleteAll(){
    new deleteAllAsyncTask(noteDao).execute();

}

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public static class insertAsyncTask extends AsyncTask<Note,Void,Void> {
NoteDao noteDao;
    public insertAsyncTask(NoteDao noteDao) {
        super();
        this.noteDao=noteDao;

    }

    @Override
    protected Void doInBackground(Note... notes) {
        noteDao.insert(notes[0]);
        return null;
    }}


        public static class deleteAsyncTask extends AsyncTask<Note,Void,Void> {
            NoteDao noteDao;

            public deleteAsyncTask(NoteDao noteDao) {
                super();
                this.noteDao = noteDao;

            }

            @Override
            protected Void doInBackground(Note... notes) {
                noteDao.delete(notes[0]);
                return null;
            }
        }
            public static class updateAsyncTask extends AsyncTask<Note,Void,Void> {
                NoteDao noteDao;
                public updateAsyncTask(NoteDao noteDao) {
                    super();
                    this.noteDao=noteDao;

                }

                @Override
                protected Void doInBackground(Note... notes) {
                    noteDao.update(notes[0]);
                    return null;
                }


}

public static class deleteAllAsyncTask extends AsyncTask<Void,Void,Void>{
NoteDao noteDao;

    public deleteAllAsyncTask(NoteDao noteDao) {
        super();
        this.noteDao=noteDao;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        noteDao.deleteAll();
        return null;
    }
}



}




