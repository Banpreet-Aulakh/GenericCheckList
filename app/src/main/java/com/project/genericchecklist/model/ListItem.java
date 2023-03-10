package com.project.genericchecklist.model;

import java.text.SimpleDateFormat;

public class ListItem {
    private String title;
    private int id;
    private boolean done;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isDone() {
        return done;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
