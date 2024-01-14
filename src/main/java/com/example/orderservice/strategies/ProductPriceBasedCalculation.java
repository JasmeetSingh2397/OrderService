package com.example.orderservice.strategies;

import com.example.orderservice.models.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductPriceBasedCalculation implements AmountCalculationStrategy {


    @Override
    public Double calculateAmount(List<Product> products) {
        Double amount= 0.0;
        for(Product product:products){
            amount+=product.getPrice();
        }
        return amount;
    }
}
