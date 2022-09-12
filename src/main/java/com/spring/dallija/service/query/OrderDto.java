package com.spring.dallija.service.query;

import com.spring.dallija.api.OrderApiController;
import com.spring.dallija.domain.Address;
import com.spring.dallija.domain.Order;
import com.spring.dallija.domain.OrderItem;
import com.spring.dallija.domain.OrderStatus;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
public class OrderDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;
    private List<OrderItemDto> orderItems;

    public OrderDto(Order order) {
        orderId = order.getId();
        name = order.getMember().getName(); //LAZY 초기화
        orderDate = order.getOrderDate();
        orderStatus = order.getStatus();
        address = order.getDelivery().getAddress(); //LAZY 초기화
        orderItems = order.getOrderItems().stream()
                .map(orderItem -> new OrderItemDto(orderItem))
                .collect(toList());

    }
}

