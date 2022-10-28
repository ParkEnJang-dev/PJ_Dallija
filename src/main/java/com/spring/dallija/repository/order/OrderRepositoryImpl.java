package com.spring.dallija.repository.order;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.dallija.controller.dto.OrderDto;
import com.spring.dallija.domain.order.Order;
import com.spring.dallija.domain.order.QOrderItem;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.spring.dallija.controller.dto.OrderDto.*;
import static com.spring.dallija.domain.order.QOrder.order;
import static com.spring.dallija.domain.order.QOrderItem.*;


@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositorySearch{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Order> findOrderUsers(OrderCond orderCond) {
        List<Order> orders = queryFactory
                .selectFrom(order)
                .leftJoin(order.orderItem, orderItem).fetchJoin()
                .where(order.user.id.eq(orderCond.getId()))
                .fetch();
        return orders;
    }
}
