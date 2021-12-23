package com.example.notesapp;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.greenrobot.eventbus.EventBus;

public class NoteFragment extends Fragment {

    private NoteModel noteModel;
    private  View mView;
    private ImageView backButton, deleteButton;
    private TextView noteTitle, noteData;

    public NoteFragment(NoteModel noteModel) {
        this.noteModel = noteModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.note_frag, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = getView();

        backButton = mView.findViewById(R.id.ivBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new NotesEvent.GoBack());
            }
        });

        deleteButton = mView.findViewById(R.id.ivDelete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new NotesEvent.DeleteNote(noteModel.getId()));
            }
        });

        noteTitle = mView.findViewById(R.id.noteTitle);
        noteTitle.setText(noteModel.getTitle());
        noteData = mView.findViewById(R.id.noteData);
        noteData.setText(noteModel.getData());
    }
}
