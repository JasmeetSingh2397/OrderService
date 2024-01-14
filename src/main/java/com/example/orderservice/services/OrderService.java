package com.example.orderservice.services;

import com.example.orderservice.exceptions.InvalidOrderException;
import com.example.orderservice.models.Orders;
import com.example.orderservice.models.Product;
import com.example.orderservice.repositories.OrderRepository;
import com.example.orderservice.strategies.AmountCalculationStrategy;
import org.hibernate.query.Order;
import org.hibernate.query.UnknownSqlResultSetMappingException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private ProductService productService;
    private AmountCalculationStrategy amountCalculationStrategy;



    public OrderService(OrderRepository orderRepository, ProductService productService, AmountCalculationStrategy amountCalculationStrategy) {

        this.orderRepository = orderRepository;
        this.productService = productService;
        this.amountCalculationStrategy = amountCalculationStrategy;
    }

    public Orders createOrder(List<Product> products, Long userId){
        productService.saveAllProducts(products);

        Orders order= new Orders();
        order.setProducts(products);
        order.setUserId(userId);
        Orders savedOrder= orderRepository.save(order);
        return savedOrder;
    }

    public Double calculateAmount(Long orderId) throws InvalidOrderException {
        Optional<Orders> optionalOrder= orderRepository.findById(orderId);
        if(optionalOrder.isEmpty()){
            throw new InvalidOrderException("Order not found");
        }

        Orders order= optionalOrder.get();
        List<Product> products = order.getProducts();
        if(products.isEmpty()){
            throw new InvalidOrderException("Order not found");
        }
        return amountCalculationStrategy.calculateAmount(products);

    }

    public Orders getOrder(Long orderId) throws InvalidOrderException {
        Optional<Orders> optionalOrder = orderRepository.findById(orderId);
        if(optionalOrder.isEmpty()){
            throw new InvalidOrderException("Order not found");
        }
        return optionalOrder.get();
    }


}
