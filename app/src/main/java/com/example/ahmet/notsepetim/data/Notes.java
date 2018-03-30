package com.example.ahmet.notsepetim.data;

/**
 * Created by ahmet on 29.03.2018.
 */

public class Notes {

    private int id;
    private String notedescription;
    private String noteDate;
    private int noteCompleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotedescription() {
        return notedescription;
    }

    public void setNotedescription(String notedescription) {
        this.notedescription = notedescription;
    }

    public String getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(String noteDate) {
        this.noteDate = noteDate;
    }

    public int getNoteCompleted() {
        return noteCompleted;
    }

    public void setNoteCompleted(int noteCompleted) {
        this.noteCompleted = noteCompleted;
    }
}
