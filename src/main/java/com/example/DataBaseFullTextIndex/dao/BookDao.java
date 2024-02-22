package com.example.DataBaseFullTextIndex.dao;


import com.example.DataBaseFullTextIndex.dto.BookDto;
import com.example.DataBaseFullTextIndex.model.Book;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookDao {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "123";
    private static final Connection connection;

    public static final String SQL_FOR_GETTING_ALL_BOOKS_BY_AUTHOR = "SELECT title,publishing,year from book where author = ?";
    public static final String SQL_FOR_GETTING_ALL_BOOKS_BY_TITLE = "SELECT title,publishing,year,author from book where title = ?";
    public static final String SQL_FOR_GETTING_ALL_BOOKS_BY_YEAR = "SELECT title,publishing,year,author from book where year = ?";
    public static final String SQL_FOR_GETTING_ALL_BOOKS_BY_ANNOTATION = "SELECT title,publishing,year,author from book where annotation ilike ?";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public BookDto getAllBookByAuthor(String author) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(SQL_FOR_GETTING_ALL_BOOKS_BY_AUTHOR);
        preparedStatement.setString(1, author);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Book> books = new ArrayList<>();
        while (resultSet.next()) {
            String title = resultSet.getString("title");
            String publishing = resultSet.getString("publishing");
            String year = resultSet.getString("year");
            Book book = new Book(title, publishing, year, author);
            books.add(book);
        }
        return new BookDto(books);
    }

    public BookDto getAllBookByName(String nameOfBook) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_FOR_GETTING_ALL_BOOKS_BY_TITLE);
        preparedStatement.setString(1, nameOfBook);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Book> books = new ArrayList<>();
        while (resultSet.next()) {
            String publishing = resultSet.getString("publishing");
            String year = resultSet.getString("year");
            String author = resultSet.getString("author");
            Book book = new Book(nameOfBook, publishing, year, author);
            books.add(book);
        }
        return new BookDto(books);
    }

    public BookDto getAllBookByYear(String year) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_FOR_GETTING_ALL_BOOKS_BY_YEAR);
        preparedStatement.setString(1, year);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Book> books = new ArrayList<>();
        while (resultSet.next()) {
            String publishing = resultSet.getString("publishing");
            String author = resultSet.getString("author");
            String title = resultSet.getString("title");
            Book book = new Book(title, publishing, year, author);
            books.add(book);
        }
        return new BookDto(books);
    }

    public BookDto getAllBookByAnnotation(String annotation) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_FOR_GETTING_ALL_BOOKS_BY_ANNOTATION);
        annotation = "%" + annotation + "%";
        preparedStatement.setString(1, annotation);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Book> books = new ArrayList<>();
        while (resultSet.next()) {
            String publishing = resultSet.getString("publishing");
            String author = resultSet.getString("author");
            String title = resultSet.getString("title");
            String year = resultSet.getString("year");
            Book book = new Book(title, publishing, year, author);
            books.add(book);
        }

        return new BookDto(books);
    }
}
