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
import static com.spring.dallija.controller.dto.OrderDto.OrderItemResponse;
import static com.spring.dallija.domain.item.QItem.item;
import static com.spring.dallija.domain.order.QOrderItem.orderItem;

@RequiredArgsConstructor
public class OrderItemRepositorySearchImpl implements OrderItemRepositorySearch {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<OrderItemResponse> findByOrderItems(OrderCond orderCond, Pageable pageable) {
        List<OrderItemResponse> contents = queryFactory.select(Projections.fields(OrderItemResponse.class,
                        item.id.as("itemId"),
                        item.name.as("itemTitle"),
                        orderItem.quantity.as("itemQuantity"))
                ).from(orderItem)
                .where(orderItem.order.id.eq(orderCond.getId()))
                .leftJoin(orderItem.item, item)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        JPAQuery<Long> countQuery = queryFactory
                .select(orderItem.count())
                .from(orderItem)
                .where(orderItem.order.id.eq(orderCond.getId()));

        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchOne);
    }
}
