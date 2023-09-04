package com.example.sspringglobalsec.controller;

import com.example.sspringglobalsec.data.model.Document;
import com.example.sspringglobalsec.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping("/documents/{code}")
    public Document getDocument(@PathVariable String code){
        return documentService.getDocument(code);
    }

}
