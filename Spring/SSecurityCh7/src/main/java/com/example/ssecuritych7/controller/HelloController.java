package com.example.ssecuritych7.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Get Hello!";
    }

    @PostMapping("/hello")
    public String postHello() {
        return "Post Hello!";
    }

    @GetMapping("/ciao")
    public String ciao() {
        return "Ciao, Spring Security!";
    }

    @GetMapping("/hola")
    public String hola() {
        return "Hola, Spring Security!";
    }
}
