package com.example.notesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "NotesDB";
    private static final int DB_VERSION = 3;
    private static final String TABLE_NAME = "NotesTable";

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME+";");
        String query = "CREATE TABLE "+TABLE_NAME+" ( id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, data TEXT, bookmark INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public void addNote(NoteModel noteModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title", noteModel.getTitle());
        cv.put("data", noteModel.getData());
        cv.put("bookmark", noteModel.getBookmark());

        db.insert(TABLE_NAME, null, cv);
        db.close();
    }

    public List<NoteModel> getAllNotes() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME+";";
        Cursor cursor = db.rawQuery(query, null);
        List<NoteModel> noteList = new ArrayList<>();
        if(cursor.moveToFirst()) {
            do{
                noteList.add(new NoteModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return noteList;
    }

    public void fillTable() {
        addNote(new NoteModel("First Note", "this is a first sample note"));
        addNote(new NoteModel("Microsoft", "this is a second sample note"));
        addNote(new NoteModel("Third Note", "this is a third sample note"));
        addNote(new NoteModel("MacOS", "this is a first fourth note"));
        addNote(new NoteModel("Windows", "this is a first fifth note"));
        addNote(new NoteModel("Grocerices", "this is a sixth sample note"));
        addNote(new NoteModel("HP Setup", "this is a seventh sample note"));
        addNote(new NoteModel("Android Studio", "this is a eight sample note"));
        addNote(new NoteModel("VPN Setup", "this is a first nineth note"));
        addNote(new NoteModel("Pansonic Avionocs", "this is a tenth sample note"));
        addNote(new NoteModel("Infosys", "this is a first eleventh note"));
        addNote(new NoteModel("Fourth Note", "this is a twelveth sample note"));
        addNote(new NoteModel("HyperVMware", "this is a thirteenth sample note"));
        addNote(new NoteModel("Tesla", "this is a first fourteenth note"));
        addNote(new NoteModel("Studio", "this is a first fifteenth note"));
    }

    public void deleteNote(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE id="+id);
        db.close();
    }

    public void setBookmark(int id, int bMark) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE "+TABLE_NAME+" SET bookmark="+bMark+" WHERE id="+id+";");
        db.close();
    }

    public void editNote(int id, String title, String data) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE "+TABLE_NAME+" SET title=\""+title+"\" , data=\""+data+"\" WHERE id="+id+";");
        db.close();
    }
}
