package com.example.notesapp;

public class NoteModel {

    private int id;
    private String title,data;
    private int bookmark;

    public NoteModel(String title, String data) {
        this.title = title;
        this.data = data;
        bookmark = 0;
    }

    public NoteModel(String title, String data, int bookmark) {
        this.title = title;
        this.data = data;
        this.bookmark = bookmark;
    }

    public NoteModel(int id, String title, String data, int bookmark) {
        this.id = id;
        this.title = title;
        this.data = data;
        this.bookmark = bookmark;
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

    public int getBookmark() {
        return bookmark;
    }

    public void setBookmark(int bookmark) {
        this.bookmark = bookmark;
    }
}
