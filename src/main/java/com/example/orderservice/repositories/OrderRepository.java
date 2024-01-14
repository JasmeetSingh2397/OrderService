package com.example.orderservice.repositories;

import com.example.orderservice.models.Orders;
import com.example.orderservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {

//    @Query("SELECT p FROM Product p WHERE  = :orderId")
//    List<Product> findAllByOrderId(@Param("orderId") Long orderId);
}
