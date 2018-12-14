package com.example.jesulonimi.notetakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;

public class AddNote extends AppCompatActivity {
EditText titleEdit;
EditText descEdit;
NumberPicker numberPicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        titleEdit=(EditText) findViewById(R.id.add_title);
        descEdit=(EditText) findViewById(R.id.add_description);
        numberPicker=(NumberPicker) findViewById(R.id.numberPicker);
        numberPicker.setMaxValue(10);
        numberPicker.setMinValue(1);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        if(getIntent().hasExtra("id")){
            titleEdit.setText(getIntent().getStringExtra("title"));
            descEdit.setText(getIntent().getStringExtra("description"));
            numberPicker.setValue(getIntent().getIntExtra("priority",1));
            setTitle("Edit Note");
        }else{
        setTitle("Add Note");
        }
    }
    public void saveNote(){
        String title=titleEdit.getText().toString();
        String description=descEdit.getText().toString();
        int priority=numberPicker.getValue();
        if(title.trim()!=""||description.trim()!=""){
            Intent i=new Intent();
            i.putExtra("title",title);
            i.putExtra("description",description);
            i.putExtra("priority",priority);

            if(getIntent().hasExtra("id")){
                i.putExtra("id",getIntent().getIntExtra("id",-1));
            }

            setResult(RESULT_OK,i);
            finish();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.save_icon:
                saveNote();
        }
        return true;
    }
}
