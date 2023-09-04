package com.example.sspringglobalsec.controller;

import com.example.sspringglobalsec.data.model.Employee;
import com.example.sspringglobalsec.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/book/details/{name}")
    public Employee getBookDetails(@PathVariable String name){
        return bookService.getBookDetails(name);
    }
}
