package com.example.orderservice.controllers;

import com.example.orderservice.dtos.OrderCheckoutDto;
import com.example.orderservice.dtos.OrderDto;
import com.example.orderservice.dtos.OrderGenerateRequestDto;
import com.example.orderservice.exceptions.InvalidOrderException;
import com.example.orderservice.models.Orders;
import com.example.orderservice.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/generate")
    public ResponseEntity<OrderDto> generateOrder(@RequestBody OrderGenerateRequestDto generateRequestDto){
        Orders order= orderService.createOrder(generateRequestDto.getProducts(), generateRequestDto.getUserId());

        return new ResponseEntity<>(OrderDto.from(order), HttpStatus.OK);
    }

    @GetMapping("/amount/{id}")
    public ResponseEntity<OrderCheckoutDto> calculateAmount(@PathVariable("id") Long orderId) throws InvalidOrderException {
        Double amount= orderService.calculateAmount(orderId);
        Orders order= orderService.getOrder(orderId);
        return new ResponseEntity<>(OrderCheckoutDto.from(orderId,amount, order.getUserId(), order.getOrderStatus()),HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable("id") Long orderId) throws InvalidOrderException {
        Orders order= orderService.getOrder(orderId);
        return new ResponseEntity<>(OrderDto.from(order),HttpStatus.OK);

    }

    @PostMapping("/{id}")
    public ResponseEntity<OrderDto> placeOrder(@PathVariable("id") Long orderId) throws InvalidOrderException {
        orderService.placeOrder(orderId);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
