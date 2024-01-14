package com.example.orderservice.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter

@Entity
public class Product {
    @Id
    private Long id;
    private String title;
    private double price;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    private String imageUrl;
    private String category;


}