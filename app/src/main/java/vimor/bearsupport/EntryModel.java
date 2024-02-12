package vimor.bearsupport;

import java.time.LocalDate;

public class EntryModel {
    private int id;
    private String title;
    private LocalDate date;
    private String entry;

    public EntryModel(int id, String title, LocalDate date, String entry) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.entry = entry;
    }

    public EntryModel() {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }
}
