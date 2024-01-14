package com.example.orderservice.dtos;

import com.example.orderservice.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class OrderGenerateRequestDto implements Serializable {
    private List<Product> products;
    private Long userId;
}
