package com.example.orderservice.strategies;

import com.example.orderservice.models.Product;

import java.util.List;

public interface AmountCalculationStrategy {
    Double calculateAmount(List<Product> products);
}

