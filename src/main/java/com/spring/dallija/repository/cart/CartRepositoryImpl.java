package com.spring.dallija.repository.cart;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.spring.dallija.controller.dto.CartDto.CartResponse;
import static com.spring.dallija.domain.cart.QCart.cart;
import static com.spring.dallija.domain.item.QItem.item;

@RequiredArgsConstructor
public class CartRepositoryImpl implements CartRepositorySearch {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<CartResponse> findUserCarts(Long id, Pageable pageable) {

        List<CartResponse> contents = queryFactory.select(Projections.fields(CartResponse.class,
                        cart.id,
                        cart.item.id.as("itemId"),
                        cart.item.name.as("itemName"),
                        cart.quantity.as("itemQuantity"),
                        cart.item.price.as("itemPrice"))
                ).from(cart)
                .where(cart.user.id.eq(id))
                .orderBy(cart.id.desc())
                .leftJoin(cart.item, item)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory.select(cart.count())
                .from(cart)
                .where(cart.user.id.eq(id));

        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchOne);
    }
}