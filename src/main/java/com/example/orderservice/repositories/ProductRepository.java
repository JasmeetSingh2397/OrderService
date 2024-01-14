package com.example.orderservice.repositories;

import com.example.orderservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

//    List<Product> saveAllProducts(Iterable<Product> products);



}
