package com.spring.dallija.repository;

import com.spring.dallija.controller.dto.OrderDto;
import com.spring.dallija.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    public List<Order> findAllByUser(OrderDto.OrderSearch orderSearch);

    @Override
    @Query("select o from Order o" +
            " left join fetch o.delivery" +
            " left join fetch o.user")
    List<Order> findAll();
}
