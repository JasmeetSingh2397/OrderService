package com.example.orderservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderEvent {

    private Long orderId;
    private String userEmail;
    private String eventType;

    public OrderEvent(Long orderId, String userEmail, String eventType) {
        this.orderId = orderId;
        this.userEmail = userEmail;
        this.eventType= eventType;
    }

    // Constructors, getters, setters
}
