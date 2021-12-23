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
}
