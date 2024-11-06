package com.example.noteme2;
public class ModelNote {
    int id;
    String noteTitle;
    String noteDescription;
    String noteColor;


    // Methods to view the note based on the contents
    ModelNote(){}
    public ModelNote(String noteTitle, String noteDescription, String noteColor) {
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
        this.noteColor = noteColor;
    }

    public ModelNote(int id, String noteTitle, String noteDescription, String noteColor) {
        this.id = id;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
        this.noteColor = noteColor;
    }

    // Getters and Setters for the entries in the database
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNoteTitle() {
        return noteTitle;
    }
    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }
    public String getNoteDescription() {
        return noteDescription;
    }
    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }
    public String getNoteColor() {
        return noteColor;
    }
    public void setNoteColor(String noteColor) {
        this.noteColor = noteColor;
    }
}
