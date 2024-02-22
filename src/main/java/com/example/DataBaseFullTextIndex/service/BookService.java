package com.example.DataBaseFullTextIndex.service;

import com.example.DataBaseFullTextIndex.dao.BookDao;

import com.example.DataBaseFullTextIndex.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class BookService {
    private final BookDao bookDao;

    @Autowired
    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }


    public BookDto getBookByAuthor(String author) throws SQLException {
        return bookDao.getAllBookByAuthor(author);
    }

    public BookDto getBookByName(String nameOfBook) throws SQLException {
        return bookDao.getAllBookByName(nameOfBook);
    }

    public BookDto getBookByYear(String year) throws SQLException {
        return bookDao.getAllBookByYear(year);
    }

    public BookDto getBookByAnnotation(String annotation) throws SQLException {
        return bookDao.getAllBookByAnnotation(annotation);
    }
}
