package com.spring.dallija.api.dto;

import com.spring.dallija.domain.Address;
import com.spring.dallija.domain.order.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SimpleOrdersDto{
    private Long orderId;
    private String name;
    private LocalDateTime orderDate; //주문시간
    private OrderStatus orderStatus;
    private Address address;

    public SimpleOrdersDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address) {
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
    }
}