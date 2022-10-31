package com.spring.dallija.repository.order;

import com.spring.dallija.controller.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.spring.dallija.controller.dto.OrderDto.*;

public interface OrderItemRepositorySearch {
    Page<OrderItemResponse> findByOrderItems (OrderCond orderCond, Pageable pageable);
}