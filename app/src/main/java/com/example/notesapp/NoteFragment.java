package com.example.notesapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.greenrobot.eventbus.EventBus;

public class NoteFragment extends Fragment {

    private NoteModel noteModel;
    private  View mView;
    private EditText noteTitle, noteData;

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

        ImageView back = mView.findViewById(R.id.ivBack);
        ImageView delete = mView.findViewById(R.id.ivDelete);
        ImageView save = mView.findViewById(R.id.ivSave);
        ImageView bookmark = mView.findViewById(R.id.ivBookmark);
        ImageView edit = mView.findViewById(R.id.ivEdit);

        save.setVisibility(View.GONE);

        noteTitle = mView.findViewById(R.id.noteTitle);
        noteData = mView.findViewById(R.id.noteData);
        noteTitle.setEnabled(false);
        noteTitle.setText(noteModel.getTitle());
        noteData.setEnabled(false);
        noteData.setText(noteModel.getData());

        if(noteModel.getBookmark() == 1) {
            bookmark.setImageResource(R.drawable.bookmarked);
        }

        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(noteModel.getBookmark() == 0) {
                    bookmark.setImageResource(R.drawable.bookmarked);
                    EventBus.getDefault().post(new NotesEvent.Bookmark(noteModel.getId(), 1));
                    noteModel.setBookmark(1);
                } else {
                    bookmark.setImageResource(R.drawable.bookmark);
                    EventBus.getDefault().post(new NotesEvent.Bookmark(noteModel.getId(), 0));
                    noteModel.setBookmark(0);
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new NotesEvent.GoBack());
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Delete Note")
                        .setMessage("Do you want to delete the note?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EventBus.getDefault().post(new NotesEvent.DeleteNote(noteModel.getId()));
                            }
                        })
                        .setNegativeButton("No", null).show();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteTitle.setEnabled(true);
                noteData.setEnabled(true);
                save.setVisibility(View.VISIBLE);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = noteTitle.getText().toString().trim();
                String data = noteData.getText().toString();
                if(title.equals("")) {
                    Toast.makeText(getActivity(), "Title can not be empty", Toast.LENGTH_SHORT).show();
                } else {
                    save.setVisibility(View.GONE);
                    noteData.setEnabled(false);
                    noteTitle.setEnabled(false);
                    EventBus.getDefault().post(new NotesEvent.EditNote(noteModel.getId(), title, data));
                    noteModel.setTitle(title);
                    noteModel.setData(data);
                }
            }
        });
    }
}
