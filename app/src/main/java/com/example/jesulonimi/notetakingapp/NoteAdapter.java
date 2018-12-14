package com.example.jesulonimi.notetakingapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteExample> {
    List<Note> noteList=new ArrayList<>();
    @Override
    public NoteExample onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.note_format,parent,false)
;        return new NoteExample(v);
    }

    @Override
    public void onBindViewHolder(NoteExample holder, int position) {
Note notes=noteList.get(position);
holder.priority.setText(String.valueOf(notes.getPriority()));
holder.title.setText(notes.getTitle());
holder.description.setText(notes.getDescription());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public void setNoteList(List<Note> note){
        this.noteList=note;
        notifyDataSetChanged();
    }

    public class NoteExample extends RecyclerView.ViewHolder{
        TextView priority;
        TextView title;
        TextView description;


        public NoteExample(View itemView) {
            super(itemView);
            priority=(TextView) itemView.findViewById(R.id.priority);
            title=(TextView) itemView.findViewById(R.id.title);
            description=(TextView) itemView.findViewById(R.id.description);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    listener.onItemClick(noteList.get(position));
                }
            });
        }
    }

    public Note getNoteAt(int position){
        return  noteList.get(position);
    }
    OnItemClick listener;
    public interface  OnItemClick{
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(OnItemClick listener){
        this.listener=listener;
    }
}
