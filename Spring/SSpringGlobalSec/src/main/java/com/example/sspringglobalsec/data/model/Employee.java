package com.example.sspringglobalsec.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class Employee {

    private String name;
    private List<String> books;
    private List<String> roles;
}
