package com.spring.dallija.repository.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.spring.dallija.controller.dto.OrderDto.OrderCond;
import static com.spring.dallija.controller.dto.OrderDto.OrderUserResponse;

public interface OrderRepositorySearch {

    Page<OrderUserResponse> findOrderUsers(OrderCond orderCond, Pageable pageable);
}
