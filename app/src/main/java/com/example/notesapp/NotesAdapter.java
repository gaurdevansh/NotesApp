package com.example.notesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private List<NoteModel> noteList = new ArrayList<>();
    private NoteClickListener noteClickListener;

    public NotesAdapter(List<NoteModel> noteList, NoteClickListener noteClickListener) {
        this.noteList = noteList;
        this.noteClickListener = noteClickListener;
    }

    public void setNoteList(List<NoteModel> noteList) {
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new ViewHolder(view, noteClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
        holder.title.setText(noteList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        NoteClickListener noteClickListener;

        public ViewHolder(@NonNull View itemView, NoteClickListener noteClickListener) {
            super(itemView);
            title = itemView.findViewById(R.id.title);

            this.noteClickListener = noteClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            noteClickListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface NoteClickListener {
        void onNoteClick(int position);
    }

}
