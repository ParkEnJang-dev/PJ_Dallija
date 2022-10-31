package com.spring.dallija.repository.order;

import com.spring.dallija.domain.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>, OrderItemRepositorySearch {

}