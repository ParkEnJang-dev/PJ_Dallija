package com.spring.dallija.repository.order;


import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.spring.dallija.controller.dto.OrderDto.OrderCond;
import static com.spring.dallija.controller.dto.OrderDto.OrderUserResponse;
import static com.spring.dallija.domain.order.QOrder.order;


@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositorySearch {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<OrderUserResponse> findOrderUsers(OrderCond orderCond, Pageable pageable) {
        List<OrderUserResponse> contents = queryFactory.select(Projections.fields(OrderUserResponse.class,
                        order.id,
                        order.title,
                        order.delivery.id.as("delivery_id"),
                        order.status,
                        order.created))
                .from(order)
                .where(order.user.id.eq(orderCond.getId()))
                .orderBy(order.created.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(order.count())
                .from(order)
                .where(order.user.id.eq(orderCond.getId()));


        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchOne);
    }


}
