package com.spring.dallija.repository;


import com.spring.dallija.domain.order.Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class OrdersRepositoryImpl {

    private final EntityManager em;

    public void save(Orders order){
        em.persist(order);
    }

    public Orders findOne(Long id){
        return em.find(Orders.class, id);
    }
}
