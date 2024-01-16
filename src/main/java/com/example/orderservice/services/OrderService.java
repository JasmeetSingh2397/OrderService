package com.example.orderservice.services;

import com.example.orderservice.dtos.OrderEvent;
import com.example.orderservice.exceptions.InvalidOrderException;
import com.example.orderservice.models.Orders;
import com.example.orderservice.models.Product;
import com.example.orderservice.models.User;
import com.example.orderservice.repositories.OrderRepository;
import com.example.orderservice.strategies.AmountCalculationStrategy;
import org.hibernate.query.Order;
import org.hibernate.query.UnknownSqlResultSetMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private ProductService productService;
    private AmountCalculationStrategy amountCalculationStrategy;

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    private RestTemplate restTemplate;

    @Value("${UserService.endpoint}")
    private String userServiceEndpoint;


    @Autowired
    public OrderService(OrderRepository orderRepository, ProductService productService, AmountCalculationStrategy amountCalculationStrategy, KafkaTemplate<String, OrderEvent> kafkaTemplate, RestTemplate restTemplate) {

        this.orderRepository = orderRepository;
        this.productService = productService;
        this.amountCalculationStrategy = amountCalculationStrategy;
        this.kafkaTemplate = kafkaTemplate;
        this.restTemplate= restTemplate;
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







    public void placeOrder(Long orderId) {
        Optional<Orders> optionalOrder= orderRepository.findById(orderId);
        Orders order= optionalOrder.get();
        Long userId= order.getUserId();
        Map<String, Long> userUriVariables = new HashMap<>();
        userUriVariables.put("id", userId);
        ResponseEntity<User> userResponseEntity = restTemplate.getForEntity(userServiceEndpoint, User.class, userUriVariables);
        User user= userResponseEntity.getBody();
        String emailOfUser= user.getEmail();
        // Logic to place the order

        // Publish a Kafka message for successful order placement
        OrderEvent orderEvent = new OrderEvent(order.getId(), emailOfUser, "orderPlaced");
        kafkaTemplate.send("successful-orders", orderEvent);
    }


}
