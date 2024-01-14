package com.example.orderservice.dtos;

import com.example.orderservice.models.OrderStatus;
import com.example.orderservice.models.Orders;
import com.example.orderservice.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderDto {

    private Long orderId;
    private List<ProductDto> productDtos;
    private Long userId;
    private OrderStatus orderStatus;


    public static OrderDto from(Orders order){

        List<Product> products= order.getProducts();
        List<ProductDto> productDtoList= new ArrayList<>();
        for (Product product:products){
            productDtoList.add(ProductDto.from(product));
        }
        OrderDto orderDto= new OrderDto();
        orderDto.setOrderId(order.getId());
        orderDto.setUserId(order.getUserId());
        orderDto.setOrderStatus(order.getOrderStatus());

        orderDto.setProductDtos(productDtoList);
        return orderDto;


    }

}
