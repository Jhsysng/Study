package com.example.sspringglobalsec.service;

import com.example.sspringglobalsec.data.model.Employee;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookService {

    private Map<String, Employee> records = Map.of(
            "emma", new Employee("Emma", List.of("The Alchemist"), List.of("reader")),
            "natilie", new Employee("Natalie", List.of("The Alchemist", "The Notebook"), List.of("researcher"))
    );

    @PostAuthorize("returnObject.roles.contains('reader')")
    public Employee getBookDetails(String name){
        return records.get(name);
    }
}
