package com.example.notesapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.greenrobot.eventbus.EventBus;

public class NewNoteFrag extends Fragment {

    private View mView;
    private NoteModel noteModel;
    private int bMark = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = getView();
        ImageView back = mView.findViewById(R.id.ivBackNewNote);
        ImageView bookmark = mView.findViewById(R.id.ivBookmarkNewNote);
        Button save = mView.findViewById(R.id.newNoteSave);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new NotesEvent.GoBack());
            }
        });

        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bMark == 0) {
                    bMark = 1;
                    bookmark.setImageResource(R.drawable.bookmarked);
                } else {
                    bMark = 0;
                    bookmark.setImageResource(R.drawable.bookmark);
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
    }

    public void saveNote() {
        EditText newNoteTitle = mView.findViewById(R.id.newNoteTitle);
        String title = newNoteTitle.getText().toString().trim();

        EditText newNoteData = mView.findViewById(R.id.newNoteData);
        String data = newNoteData.getText().toString();

        if(title.equals("")) {
            Toast.makeText(getActivity(), "Title can not be empty", Toast.LENGTH_SHORT).show();
            return;
        } else {
            NoteModel noteModel = new NoteModel(title, data, bMark);
            EventBus.getDefault().post(new NotesEvent.AddNote(noteModel));
            EventBus.getDefault().post(new NotesEvent.GoBack());
        }
    }
}
