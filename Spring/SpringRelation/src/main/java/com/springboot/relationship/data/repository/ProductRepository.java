package com.springboot.relationship.data.repository;

import com.springboot.relationship.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product AS p WHERE p.name=?1")
    List<Product> findByName(String name);

    @Query("SELECT p FROM Product p WHERE p.name=:name")
    List<Product> findByNameParam(@Param("name")String name);

    @Query("SELECT p.name, p.price, p.stock FROM Product p WHERE p.name=:name")
    List<Object[]> findByNameParam2(@Param("name") String name);

}
