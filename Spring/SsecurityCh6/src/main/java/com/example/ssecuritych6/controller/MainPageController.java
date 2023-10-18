package com.example.ssecuritych6.controller;

import com.example.ssecuritych6.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class MainPageController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String main(Authentication a, Model model) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("username", a.getName());
        return "main.html";
    }

}
