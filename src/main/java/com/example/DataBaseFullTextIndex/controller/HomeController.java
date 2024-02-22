package com.example.DataBaseFullTextIndex.controller;


import com.example.DataBaseFullTextIndex.dto.BookDto;
import com.example.DataBaseFullTextIndex.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class HomeController {
    private final BookService bookService;


    @Autowired
    public HomeController(BookService bookService) {
        this.bookService = bookService;
    }


    /**
     * Метод, который возвращает начальную страницу
     */
    @GetMapping("/home")
    public String home() {
        return "home";
    }


    /**
     * Метод, который возвращает страницу, где есть все книги по введённому автору
     */
    @PostMapping("/author")
    public String getBookByAuthor(@RequestParam("nameOfAuthor") String nameOfAuthor, Model model) throws SQLException {
        BookDto bookDto = bookService.getBookByAuthor(nameOfAuthor);
        model.addAttribute("bookDto", bookDto);
        model.addAttribute("author", nameOfAuthor);
        return "showBookByAuthor";
    }



    /**
     * Метод, который возвращает страницу, где есть все книги по введённому названию
     */
    @PostMapping("/title")
    public String getBookByTitle(@RequestParam("nameOfBook") String nameOfBook, Model model) throws SQLException {
        model.addAttribute("nameOfBook", nameOfBook);
        BookDto bookDto = bookService.getBookByName(nameOfBook);
        model.addAttribute("bookDto", bookDto);
        return "showBookByTitle";
    }

    @PostMapping("/year")
    public String getBookByYear(@RequestParam("year") String year , Model model) throws SQLException {
        model.addAttribute("year" , year);
        BookDto bookDto = bookService.getBookByYear(year);
        model.addAttribute("bookDto" , bookDto);
        return "showBookByYear";
    }

    @PostMapping("/annotation")
    public String getBookByAnnotation(@RequestParam("annotation") String annotation , Model model) throws SQLException {
        model.addAttribute("annotation" , annotation);
        BookDto bookDto = bookService.getBookByAnnotation(annotation);
        model.addAttribute("bookDto" , bookDto);
        return "showBookByAnnotation";
    }
}
