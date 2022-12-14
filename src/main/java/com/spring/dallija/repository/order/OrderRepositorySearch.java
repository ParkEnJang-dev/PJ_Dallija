package com.spring.dallija.repository.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.spring.dallija.controller.dto.OrderDto.*;

public interface OrderRepositorySearch {

    Page<OrderUserResponse> findOrderUser(OrderCond orderCond, Pageable pageable);
}
