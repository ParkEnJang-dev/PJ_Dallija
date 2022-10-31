package com.spring.dallija.repository.order;

import com.spring.dallija.domain.order.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> , OrderRepositorySearch{

    @Override
    @EntityGraph(attributePaths = {"delivery","user"})
    List<Order> findAll();

}
