package com.spring.dallija.repository.order;

import com.spring.dallija.controller.dto.OrderDto;
import com.spring.dallija.domain.order.Order;


import java.util.List;

import static com.spring.dallija.controller.dto.OrderDto.*;

public interface OrderRepositorySearch {

    List<Order> findOrderUsers(OrderCond id);
}
