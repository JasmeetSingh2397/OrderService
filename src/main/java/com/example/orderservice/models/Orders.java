package com.example.orderservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Orders extends BaseModel {
    @ManyToMany
    private List<Product> products;
    private Long userId;
    private OrderStatus orderStatus;


}
