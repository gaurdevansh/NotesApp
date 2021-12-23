package com.example.notesapp;

public class NoteModel {

    private int id;
    private String title;
    private String data;

    public NoteModel(String title, String data) {
        this.title = title;
        this.data = data;
    }

    public NoteModel(int id, String title, String data) {
        this.id = id;
        this.title = title;
        this.data = data;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
