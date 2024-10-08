package com.example.sspringglobalsec.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NameService {
    @PreAuthorize("hasAuthority('write')")
    public String getName() {
        return "Fantastico";
    }

    private Map<String, List<String>> secretNames = Map.of(
            "natalie", List.of("Energico", "Perfecto"),
            "john", List.of("Fantastico"));

    @PreAuthorize("#name == authentication.principal.username")
    public List<String> getSecretNames(String name){
        return secretNames.get(name);
    }
}
