package com.example.sspringglobalsec.service;

import com.example.sspringglobalsec.data.model.Document;
import com.example.sspringglobalsec.data.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    @PostAuthorize("hasPermission(returnObject, 'ROLE_admin')")
    public Document getDocument(String documentId){
        return documentRepository.findDocument(documentId);
    }
}
