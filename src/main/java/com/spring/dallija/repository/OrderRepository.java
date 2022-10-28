package com.spring.dallija.repository;

import com.spring.dallija.controller.dto.OrderDto;
import com.spring.dallija.domain.order.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    public List<Order> findAllByUser(OrderDto.OrderSearch orderSearch);

    @Override
    @EntityGraph(attributePaths = {"delivery","user"})
    List<Order> findAll();

}
