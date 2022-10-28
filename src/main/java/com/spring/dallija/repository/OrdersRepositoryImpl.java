package com.spring.dallija.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.dallija.controller.dto.OrderDto;
import com.spring.dallija.controller.dto.OrdersDto;
import com.spring.dallija.domain.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@RequiredArgsConstructor
public class OrdersRepositoryImpl {

}
