package com.example.ssecuritych6.data.repository;

import com.example.ssecuritych6.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
