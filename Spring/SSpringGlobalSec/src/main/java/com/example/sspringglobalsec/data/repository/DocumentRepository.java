package com.example.sspringglobalsec.data.repository;

import com.example.sspringglobalsec.data.model.Document;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class DocumentRepository {
    private Map<String, Document> documents =
            Map.of("abc123", new Document("emma"),
                    "xyz123", new Document("natalie"));

    public Document findDocument(String documentId){
        return documents.get(documentId);
    }
}
