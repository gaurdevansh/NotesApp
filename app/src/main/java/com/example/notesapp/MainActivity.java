package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NotesAdapter.NoteClickListener{

    private RecyclerView recyclerView;
    private Button addButton, exitButton;
    private List<NoteModel> noteList = new ArrayList<>();
    private NotesAdapter adapter;
    private Fragment currentFragment;
    private DbHelper dbHelper;

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DbHelper(this);
        dbHelper.fillTable();

        exitButton = findViewById(R.id.exitBtn);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        noteList = dbHelper.getAllNotes();

        recyclerView = findViewById(R.id.recyclerView);
        addButton = findViewById(R.id.addNote);

        adapter = new NotesAdapter(noteList, this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onNoteClick(int position) {
        currentFragment = new NoteFragment(noteList.get(position));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragContainer, currentFragment, "fragment");
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void deleteNote(NotesEvent.DeleteNote event) {
        dbHelper.deleteNote(event.getNoteId());
        noteList = dbHelper.getAllNotes();
        EventBus.getDefault().post(new NotesEvent.GoBack());
        adapter.notifyDataSetChanged();
        adapter.setNoteList(noteList);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void goBack(NotesEvent.GoBack event) {
        getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
    }
}