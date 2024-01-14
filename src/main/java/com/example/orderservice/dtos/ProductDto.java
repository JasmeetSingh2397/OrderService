package com.example.orderservice.dtos;

import com.example.orderservice.models.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto {

    private Long id;
    private String title;
    private double price;
    @Column(length = 5000)
    private String description;
    private String imageUrl;
    private String category;

    public static ProductDto from(Product product){
        ProductDto productDto= new ProductDto();
        productDto.setId(product.getId());
        productDto.setCategory(product.getCategory());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setTitle(product.getTitle());
        productDto.setImageUrl(product.getImageUrl());
        return productDto;
    }
}
