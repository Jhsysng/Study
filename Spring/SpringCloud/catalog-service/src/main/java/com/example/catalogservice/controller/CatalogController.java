package com.example.catalogservice.controller;

import com.example.catalogservice.dto.ResponseCatalog;
import com.example.catalogservice.entity.CatalogEntity;
import com.example.catalogservice.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/catalog-service")
public class CatalogController {

    private final CatalogService catalogService;
    private final Environment env;

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in User Service on PORT %s", env.getProperty("local.server.port"));
    }

    @GetMapping("/catalogs")
    public ResponseEntity<List<ResponseCatalog>> getCatalogs() {
        Iterable<CatalogEntity> catalogList = catalogService.getAllCatalogs();

        List<ResponseCatalog> result = new ArrayList<>();

        catalogList.forEach(v -> {
            result.add(new ModelMapper().map(v, ResponseCatalog.class));
        });

        return ResponseEntity.ok(result);
    }
}
