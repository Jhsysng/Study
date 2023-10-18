package com.example.ssecuritych6.service;

import com.example.ssecuritych6.data.entity.Product;
import com.example.ssecuritych6.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

}
