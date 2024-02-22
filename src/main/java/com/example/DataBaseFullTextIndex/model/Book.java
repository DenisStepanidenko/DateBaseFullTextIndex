package com.example.DataBaseFullTextIndex.model;

public class Book {
    private String title;
    private String publishing;
    private String year;

    private String author;

    public Book(String title, String publishing, String year, String author) {
        this.title = title;
        this.publishing = publishing;
        this.year = year;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getPublishing() {
        return publishing;
    }

    public String getYear() {
        return year;
    }
}
