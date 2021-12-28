package com.example.notesapp;

public class NotesEvent {
    public static class DeleteNote {
        private int noteId;
        public DeleteNote(int noteId) {
            this.noteId = noteId;
        }
        public int getNoteId() {
            return noteId;
        }
    }

    public static class GoBack {
        public GoBack() {}
    }

    public static class Bookmark {
        private int id;
        private int bMark;
        public Bookmark(int id, int bMark) {
            this.id = id;
            this.bMark = bMark;
        }
        public int getId() {
            return id;
        }
        public int getbMark() {
            return bMark;
        }
    }

    public static class AddNote {
        private NoteModel noteModel;
        public AddNote(NoteModel noteModel) {
            this.noteModel = noteModel;
        }
        public NoteModel getNoteModel() {
            return noteModel;
        }
    }

    public static class EditNote {
        private int id;
        private String title, data;
        public EditNote(int id, String title, String data) {
            this.id = id;
            this.title = title;
            this.data = data;
        }
        public int getId() {
            return id;
        }
        public String getTitle() {
            return title;
        }
        public String getData() {
            return data;
        }
    }
}
