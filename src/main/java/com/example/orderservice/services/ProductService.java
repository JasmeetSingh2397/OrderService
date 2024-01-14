package com.example.orderservice.services;

import com.example.orderservice.models.Product;
import com.example.orderservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> saveAllProducts(List<Product> products){
        return productRepository.saveAll(products);
    }

//    public List<Product> findAllProductsByOrderId(Long orderId){
//        productRepository.findAllbyOrderId();
//    }
}
