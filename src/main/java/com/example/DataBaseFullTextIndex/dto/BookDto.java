package com.example.DataBaseFullTextIndex.dto;

import com.example.model.Book;

import java.util.List;

public class BookDto {
    private final List<Book> books;

    public BookDto(List<Book> books) {
        this.books = books;
    }


    public List<Book> getBooks() {
        return books;
    }
}
