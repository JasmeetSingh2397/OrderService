package com.example.orderservice.dtos;

import com.example.orderservice.models.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCheckoutDto {
    private Long orderId;
    private Double amount;
    private Long userId;
    private OrderStatus orderStatus;

    public static OrderCheckoutDto from(Long orderId, Double amount,Long userId, OrderStatus orderStatus){
        OrderCheckoutDto orderCheckoutDto= new OrderCheckoutDto();
        orderCheckoutDto.setOrderId(orderId);
        orderCheckoutDto.setAmount(amount);
        orderCheckoutDto.setUserId(userId);
        orderCheckoutDto.setOrderStatus(orderStatus);
        return orderCheckoutDto;
    }

}
