package com.example.jesulonimi.notetakingapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
NoteViewModel myViewModel;
RecyclerView recyclerView;
NoteAdapter noteAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView) findViewById(R.id.myRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        FloatingActionButton floatingActionButton=(FloatingActionButton) findViewById(R.id.floatButt);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,AddNote.class);
                startActivityForResult(i,1);
            }
        });


        noteAdapter=new NoteAdapter();
        recyclerView.setAdapter(noteAdapter);



        myViewModel= ViewModelProviders.of(this).get(NoteViewModel.class);
        myViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
              noteAdapter.setNoteList(notes);
            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            myViewModel.delete(noteAdapter.getNoteAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);



        noteAdapter.setOnItemClickListener(new NoteAdapter.OnItemClick() {
            @Override
            public void onItemClick(Note note) {
                Intent i=new Intent(MainActivity.this,AddNote.class);
                i.putExtra("title",note.getTitle());
                i.putExtra("description",note.getDescription());
                i.putExtra("priority",note.getPriority());
                i.putExtra("id",note.getId());
                startActivityForResult(i,2);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==RESULT_OK&&data!=null){
            String title=data.getStringExtra("title");
            String description=data.getStringExtra("description");
            int value=data.getIntExtra("priority",0);

            Note note=new Note(title,description,value);
            myViewModel.insert(note);

        }else if(requestCode==2&&resultCode==RESULT_OK&&data!=null){
            String title=data.getStringExtra("title");
            String description=data.getStringExtra("description");
            int value=data.getIntExtra("priority",0);

            Note note=new Note(title,description,value);
            note.setId(data.getIntExtra("id",-1));
            myViewModel.update(note);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.all_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.delete_all: myViewModel.deleteAll();
            return true;
            default:
        return super.onOptionsItemSelected(item);
    }}
}
