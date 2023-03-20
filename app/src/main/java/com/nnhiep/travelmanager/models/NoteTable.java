package com.nnhiep.travelmanager.models;

/**
 * Ghi chú
 * @author ndthuan
 */
public class NoteTable {
    private int id;
    private String title;
    private String description;

    public NoteTable() {
    }

    public NoteTable(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public NoteTable(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
